package com.atto.attoproject.repository;

import com.atto.attoproject.data.AuditLogDto;
import com.atto.attoproject.domain.AuditLog;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long>,  AuditLogRepositoryCustom{
}
