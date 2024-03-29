package com.atto.attoproject.repository;

import com.atto.attoproject.domain.enums.ERole;
import com.atto.attoproject.domain.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
