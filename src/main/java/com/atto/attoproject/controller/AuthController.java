package com.atto.attoproject.controller;

import com.atto.attoproject.data.request.LoginRequest;
import com.atto.attoproject.data.request.SignupRequest;
import com.atto.attoproject.data.response.JwtResponse;
import com.atto.attoproject.data.response.MessageResponse;
import com.atto.attoproject.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
