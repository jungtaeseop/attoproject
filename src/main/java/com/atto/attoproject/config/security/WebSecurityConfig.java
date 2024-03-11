package com.atto.attoproject.config.security;


import com.atto.attoproject.config.security.jwt.AuthEntryPointJwt;
import com.atto.attoproject.config.security.jwt.AuthTokenFilter;
import com.atto.attoproject.config.security.jwt.CustomAuthenticationFilter;
import com.atto.attoproject.config.security.jwt.JwtUtils;
import com.atto.attoproject.service.TokenBlacklistService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableMethodSecurity
@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig {
    private final JwtUtils jwtUtils;
    private final TokenBlacklistService tokenBlacklistService;
    private final AuthenticationConfiguration authenticationConfiguration;
    private final AuthEntryPointJwt unauthorizedHandler;
    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    private final AuthenticationFailureHandler authenticationFailureHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter(jwtUtils, tokenBlacklistService);
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))  //인증 실패 처리 핸들러 설정
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션 비활성화
                .authorizeHttpRequests(auth ->  // 인증 권한 설정
                                auth.requestMatchers("/api/auth/**").permitAll()
                                        .requestMatchers("/api/test/**").permitAll()
                                        .anyRequest().authenticated()
                        ///api/auth/** 및 /api/test/** 경로는 인증 없이 허용 그 외 모든 요청은 인증 필요
                );

        http.addFilterBefore(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); //인증 공급자 설정
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationConfiguration.getAuthenticationManager());

        customAuthenticationFilter.setFilterProcessesUrl("/api/auth/login");
        customAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);      // '인증' 성공 시 해당 핸들러로 처리를 전가한다.
        customAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);      // '인증' 실패 시 해당 핸들러로 처리를 전가한다.
        customAuthenticationFilter.afterPropertiesSet();
        return customAuthenticationFilter;
    }
}
