package com.atto.attoproject.service;

import com.atto.attoproject.config.exception.error.CustomException;
import com.atto.attoproject.config.security.jwt.JwtUtils;
import com.atto.attoproject.domain.AuditLog;
import com.atto.attoproject.domain.User;
import com.atto.attoproject.repository.AuditLogRepository;
import com.atto.attoproject.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuditLogServiceImpl implements AuditLogService{
    private final AuditLogRepository auditLogRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public void addLogoutSuccessAuditLog() {
        User user = getUser();
        AuditLog auditLog = AuditLog.of(LocalDateTime.now(),"LOGOUT","SUCCESS", user);
        auditLogRepository.save(auditLog);
    }

    @Transactional
    @Override
    public void addLogoutFailureAuditLog() {
        User user = getUser();
        AuditLog auditLog = AuditLog.of(LocalDateTime.now(),"LOGOUT","FAILURE", user);
        auditLogRepository.save(auditLog);
    }

    private User getUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetailsImpl userDetails = (UserDetailsImpl) principal;
        return userRepository.findByUserId(userDetails.getUserId())
                .orElseThrow(() -> CustomException.of("400", "인증된 UserId를 찾을수 없습니다.", HttpStatus.BAD_REQUEST));
    }
}
