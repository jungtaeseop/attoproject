package com.atto.attoproject.controller;

import com.atto.attoproject.config.basedto.BaseResponse;
import com.atto.attoproject.data.AuditLogDto;
import com.atto.attoproject.service.AuditLogService;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/api/audit-log")
public class AuditLogController {

    private final AuditLogService auditLogService;

    @GetMapping("/list")
    public BaseResponse<List<AuditLogDto>> getAllAuditlog() {
        List<AuditLogDto> auditLogDtos = auditLogService.getAllAuditlog();
        return BaseResponse.success(auditLogDtos);
    }
}
