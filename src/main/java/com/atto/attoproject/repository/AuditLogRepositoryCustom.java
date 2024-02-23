package com.atto.attoproject.repository;

import com.atto.attoproject.data.AuditLogDto;
import java.util.List;

public interface AuditLogRepositoryCustom {
    List<AuditLogDto> findAllAuditLogDto();
}
