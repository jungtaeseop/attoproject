package com.atto.attoproject.data;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AuditLogDto {
    private Long auditlog_id;
    private String userId;
    private String username;
    private LocalDateTime eventDateTime;
    private String eventType;
    private String eventOutcome;
}
