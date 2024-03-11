package com.atto.attoproject.config.security.jwt;

import com.atto.attoproject.config.exception.error.CustomException;
import com.atto.attoproject.data.request.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        final UsernamePasswordAuthenticationToken authRequest;
        final LoginRequest loginRequest;

        try {
            loginRequest = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);
            authRequest = new UsernamePasswordAuthenticationToken(loginRequest.getUserId(), loginRequest.getPassword());
        } catch (Exception e) {
            throw CustomException.of("400", "로그인 인증 필터 오류", HttpStatus.BAD_REQUEST);
        }

        // Authentication 객체 반환
        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
