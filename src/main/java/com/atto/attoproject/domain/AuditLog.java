package com.atto.attoproject.domain;

import com.atto.attoproject.config.basedomain.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "audit_logs")
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime eventDateTime; // 사건 발생 일시
    private String eventType; // 사건 유형
    private String eventOutcome; // 사건의 결과

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public AuditLog(LocalDateTime eventDateTime, String eventType, String eventOutcome, User user) {
        this.eventDateTime = eventDateTime;
        this.eventType = eventType;
        this.eventOutcome = eventOutcome;
        this.user = user;
    }

    public static AuditLog of(LocalDateTime eventDateTime, String eventType, String eventOutcome, User user) {
        return new AuditLog(eventDateTime, eventType, eventOutcome, user);
    }
}
