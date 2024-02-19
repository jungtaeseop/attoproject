package com.atto.attoproject.config.service;

import com.atto.attoproject.config.payload.request.LoginRequest;
import com.atto.attoproject.config.payload.request.SignupRequest;
import com.atto.attoproject.config.payload.response.JwtResponse;

public interface AuthService {
    JwtResponse authenticateUserJwtResponse(LoginRequest loginRequest);
    void registerUser(SignupRequest signUpRequest);
}
