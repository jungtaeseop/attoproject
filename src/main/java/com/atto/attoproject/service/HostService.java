package com.atto.attoproject.service;

import com.atto.attoproject.data.HostDto;
import com.atto.attoproject.data.HostStatusDto;
import com.atto.attoproject.domain.Host;
import com.atto.attoproject.domain.HostStatus;

import java.util.List;

public interface HostService {
    Host registerHost(HostDto hostDto);

    List<Host> getAllHosts();

    Host findById(Long id);

    Host updateHost(Long id, HostDto dto);

    void delete(Long id);

    HostStatusDto checkHostStatus(Long id);

    List<HostStatusDto> checkHostStatusAll();

    List<HostStatusDto> getAllHostsStatus();
}
