package com.atto.attoproject.service;

import jakarta.servlet.http.HttpServletRequest;

public interface AuditLogService {
    void addLogoutSuccessAuditLog();

    void addLogoutFailureAuditLog();
}
