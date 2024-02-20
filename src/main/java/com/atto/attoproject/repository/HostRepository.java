package com.atto.attoproject.repository;

import com.atto.attoproject.domain.Host;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HostRepository extends JpaRepository<Host, Long> , HostRepositoryCustom{
    boolean existsByName(String name);
    boolean existsByIp(String ip);
}
