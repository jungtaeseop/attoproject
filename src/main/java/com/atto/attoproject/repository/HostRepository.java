package com.atto.attoproject.repository;

import com.atto.attoproject.domain.Host;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HostRepository extends JpaRepository<Host, Long> , HostRepositoryCustom{
}
