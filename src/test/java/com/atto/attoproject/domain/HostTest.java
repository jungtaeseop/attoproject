package com.atto.attoproject.domain;

import static org.junit.jupiter.api.Assertions.*;

import com.atto.attoproject.data.HostDto;
import com.atto.attoproject.repository.HostRepository;
import com.atto.attoproject.service.HostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class HostTest {

    @Autowired
    private HostService hostService;

    @Transactional
    @Test
    void saveTest() {
        for (int index = 1; index < 50; index++) {
            HostDto hostDto = HostDto.builder()
                    .ip("1.1.1." + index)
                    .name("네트워" + index)
                    .build();

            hostService.registerHost(hostDto);
        }

    }
}