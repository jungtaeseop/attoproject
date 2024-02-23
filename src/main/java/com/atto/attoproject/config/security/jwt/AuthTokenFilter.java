package com.atto.attoproject.config.security.jwt;


import com.atto.attoproject.service.TokenBlacklistService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;


@RequiredArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final TokenBlacklistService tokenBlacklistService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            // 토큰 추출
            String token = jwtUtils.resolveToken(request);

            // JWT 토큰이 유효한지 체크 && 만료되지 않았으면 true && 로그아웃된 토큰 체크
            if (StringUtils.hasText(token) && jwtUtils.validateToken(token) && !tokenBlacklistService.isBlacklisted(token)) {
                // 토큰으로 Authentication 생성
                Authentication authentication = jwtUtils.getAuthentication(token);
                // Authentication 저장
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        } catch (Exception e) {
            logger.error("Cannot set user authentication: {}", e);
        }

        filterChain.doFilter(request, response);
    }
}
