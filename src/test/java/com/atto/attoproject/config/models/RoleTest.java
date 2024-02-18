package com.atto.attoproject.config.models;

import static org.junit.jupiter.api.Assertions.*;

import com.atto.attoproject.config.repository.RoleRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class RoleTest {
    @Autowired
    private RoleRepository roleRepository;


    @Rollback(value = true)
    @Transactional
    @Test
    void roleSave() {
        List<ERole> roles = Arrays.asList(ERole.ROLE_USER,ERole.ROLE_ADMIN);

        roles.forEach(item -> {
            Role role = new Role(item);
            roleRepository.save(role);
        });
    }
}