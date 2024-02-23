package com.atto.attoproject.service;

import com.atto.attoproject.data.AuditLogDto;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

public interface AuditLogService {
    void addLogoutSuccessAuditLog();
    void addLogoutFailureAuditLog();
    void addLoginSuccessAuditLog();
    void addLoginFailureAuditLog();

    List<AuditLogDto> getAllAuditlog();
}
