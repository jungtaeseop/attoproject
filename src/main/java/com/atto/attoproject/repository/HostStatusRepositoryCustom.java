package com.atto.attoproject.repository;

import com.atto.attoproject.data.HostStatusDto;

import java.util.List;

public interface HostStatusRepositoryCustom {
    HostStatusDto findByCheckId(Long id);

    List<HostStatusDto> findByAllHostAndHostsStatus();
}
