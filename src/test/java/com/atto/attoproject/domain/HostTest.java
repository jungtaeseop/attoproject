package com.atto.attoproject.domain;

import static org.junit.jupiter.api.Assertions.*;

import com.atto.attoproject.repository.HostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HostTest {

    @Autowired
    private HostRepository hostRepository;

    @Test
    void saveTest() {
        Host host = new Host();

        host.setIp("1.2.3.4");
        host.setName("네트워그");

        hostRepository.save(host);
    }
}