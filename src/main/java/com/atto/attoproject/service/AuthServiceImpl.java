package com.atto.attoproject.service;

import com.atto.attoproject.config.exception.error.CustomException;
import com.atto.attoproject.domain.enums.ERole;
import com.atto.attoproject.domain.Role;
import com.atto.attoproject.domain.User;
import com.atto.attoproject.data.request.LoginRequest;
import com.atto.attoproject.data.request.SignupRequest;
import com.atto.attoproject.data.response.JwtResponse;
import com.atto.attoproject.repository.RoleRepository;
import com.atto.attoproject.repository.UserRepository;
import com.atto.attoproject.config.security.jwt.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final TokenBlacklistService tokenBlacklistService;

    @Override
    public JwtResponse authenticateUserJwtResponse(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserId(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.createToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUserId(),
                userDetails.getUsername(),
                roles);
    }

    @Override
    public void registerUser(SignupRequest signUpRequest) {
        validateUniqueUsernameAndId(signUpRequest);
        User user = createUserFromRequest(signUpRequest);
        userRepository.save(user);
    }

    @Override
    public String logoutUser(HttpServletRequest request) {
        String token = jwtUtils.resolveToken(request);
        tokenBlacklistService.addToBlacklist(token);
        return "Logged out successfully";
    }


    private User createUserFromRequest(SignupRequest signUpRequest) {
        Set<Role> roles = determineUserRoles(signUpRequest);
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getUserId(),
                encoder.encode(signUpRequest.getPassword()), roles);

        return user;
    }

    private Set<Role> determineUserRoles(SignupRequest signUpRequest) {
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles != null || !strRoles.isEmpty()) {
            for (String role : strRoles) {
                switch (role) {
                    case "admin":
                        roles.add(roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> CustomException.of("400", "Error: Role을 찾을 수 없습니다.", HttpStatus.BAD_REQUEST)));
                        break;
                    case "user":
                        roles.add(roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> CustomException.of("400", "Error: Role을 찾을 수 없습니다.", HttpStatus.BAD_REQUEST)));
                        break;
                    default:
                        roles.add(roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> CustomException.of("400", "Error: Role을 찾을 수 없습니다.", HttpStatus.BAD_REQUEST)));
                        break;
                }
            }
        }

        if (strRoles.isEmpty()) {
            roles.add(roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> CustomException.of("400", "Error: Role을 찾을 수 없습니다.", HttpStatus.BAD_REQUEST)));
        }

        return roles;
    }

    private void validateUniqueUsernameAndId(SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw CustomException.of("400", "Error: 사용자 이름이 이미 사용중입니다!", HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByUserId(signUpRequest.getUserId())) {
            throw CustomException.of("400", "Error: 사용자 ID가 이미 사용중입니다!", HttpStatus.BAD_REQUEST);
        }
    }
}
