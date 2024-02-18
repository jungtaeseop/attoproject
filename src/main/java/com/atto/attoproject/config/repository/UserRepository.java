package com.atto.attoproject.config.repository;

import com.atto.attoproject.config.models.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String userId);

    Boolean existsByUsername(String username);

    Boolean existsByUserId(String userId);


}
