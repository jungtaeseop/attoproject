package com.atto.attoproject.controller;

import com.atto.attoproject.config.basedto.BaseResponse;
import com.atto.attoproject.config.exception.error.CustomException;
import com.atto.attoproject.data.request.LoginRequest;
import com.atto.attoproject.data.request.SignupRequest;
import com.atto.attoproject.data.response.JwtResponse;
import com.atto.attoproject.data.response.MessageResponse;
import com.atto.attoproject.service.AuditLogService;
import com.atto.attoproject.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
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
    private final AuditLogService auditLogService;

    @PostMapping("/signin")
    public BaseResponse<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        JwtResponse jwtResponse = authService.authenticateUserJwtResponse(loginRequest);
        return BaseResponse.success(jwtResponse);
    }

    @PostMapping("/signup")
    public BaseResponse<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        authService.registerUser(signUpRequest);
        return BaseResponse.successMessage("사용자가 성공적으로 등록되었습니다.");
    }

    @PostMapping("/logout")
    public BaseResponse<?> logout(HttpServletRequest request) {
        try {
            String message = authService.logoutUser(request);
            auditLogService.addLogoutSuccessAuditLog();
            return BaseResponse.successMessage(message);
        } catch (IllegalStateException e) {
            auditLogService.addLogoutFailureAuditLog();
            throw CustomException.of("500", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
