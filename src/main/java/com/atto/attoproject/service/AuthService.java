package com.atto.attoproject.service;

import com.atto.attoproject.data.request.LoginRequest;
import com.atto.attoproject.data.request.SignupRequest;
import com.atto.attoproject.data.response.JwtResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

public interface AuthService {
    JwtResponse authenticateUserJwtResponse(LoginRequest loginRequest);
    void registerUser(SignupRequest signUpRequest);
    String logoutUser(HttpServletRequest request);
}
