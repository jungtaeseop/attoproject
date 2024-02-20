package com.atto.attoproject.service;

import com.atto.attoproject.data.HostDto;
import com.atto.attoproject.domain.Host;

import java.util.List;

public interface HostService {
    Host registerHost(HostDto hostDto);

    List<Host> getAllHosts();

    Host findById(Long id);

    Host updateHost(Long id, HostDto dto);

    void delete(Long id);
}
