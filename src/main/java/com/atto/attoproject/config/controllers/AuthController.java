package com.atto.attoproject.config.controllers;

import com.atto.attoproject.config.models.ERole;
import com.atto.attoproject.config.models.Role;
import com.atto.attoproject.config.models.User;
import com.atto.attoproject.config.payload.request.LoginRequest;
import com.atto.attoproject.config.payload.request.SignupRequest;
import com.atto.attoproject.config.payload.response.JwtResponse;
import com.atto.attoproject.config.payload.response.MessageResponse;
import com.atto.attoproject.config.security.jwt.JwtUtils;
import com.atto.attoproject.config.repository.RoleRepository;
import com.atto.attoproject.config.repository.UserRepository;
import com.atto.attoproject.config.service.AuthService;
import com.atto.attoproject.config.service.UserDetailsImpl;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        JwtResponse jwtResponse = authService.authenticateUserJwtResponse(loginRequest);
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        authService.registerUser(signUpRequest);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
