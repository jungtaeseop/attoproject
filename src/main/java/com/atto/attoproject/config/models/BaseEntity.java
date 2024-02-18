package com.atto.attoproject.config.models;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
    @CreatedDate
    @Column(updatable = false)
    protected LocalDateTime createdDate;

    @CreatedBy
    @Column(updatable = false)
    protected String createdBy;

    @LastModifiedDate
    @Column(updatable = true)
    protected LocalDateTime lastModifiedDate;

    @LastModifiedBy
    @Column(updatable = true)
    protected String lastModifiedBy;
}
