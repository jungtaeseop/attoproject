package com.atto.attoproject.config.repository;

import com.atto.attoproject.config.models.ERole;
import com.atto.attoproject.config.models.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
