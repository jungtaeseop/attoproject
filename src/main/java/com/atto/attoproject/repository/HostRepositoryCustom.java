package com.atto.attoproject.repository;

import com.atto.attoproject.data.HostStatusDto;

import java.util.List;

public interface HostRepositoryCustom {
    HostStatusDto findByHostStatusCheckId(Long id);
    List<HostStatusDto> findByAllHostsStatus();
}
