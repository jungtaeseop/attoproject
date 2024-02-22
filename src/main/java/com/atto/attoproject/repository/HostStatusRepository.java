package com.atto.attoproject.repository;

import com.atto.attoproject.domain.Host;
import com.atto.attoproject.domain.HostStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HostStatusRepository extends JpaRepository<HostStatus, Long> , HostStatusRepositoryCustom{
}
