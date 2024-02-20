package com.atto.attoproject.domain;

import com.atto.attoproject.config.basedomain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "audit_logs")
public class AuditLog extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String principal;

    private String action;

    private String entity;

    private String data;

}
