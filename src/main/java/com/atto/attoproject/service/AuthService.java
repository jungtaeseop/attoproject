package com.atto.attoproject.service;

import com.atto.attoproject.data.request.LoginRequest;
import com.atto.attoproject.data.request.SignupRequest;
import com.atto.attoproject.data.response.JwtResponse;

public interface AuthService {
    JwtResponse authenticateUserJwtResponse(LoginRequest loginRequest);
    void registerUser(SignupRequest signUpRequest);
}
