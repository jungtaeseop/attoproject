package com.atto.attoproject.service;

import com.atto.attoproject.data.HostDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class HostServiceImplTest {

    @Autowired
    private HostService hostService;

    @Transactional
    @Test
    void host_등록() {

        for (int index = 1; index <= 100; index++) {
            HostDto hostDto = HostDto.of("네트워크" + index, "1.1.1." + index);
            hostService.registerHost(hostDto);
        }
    }
}