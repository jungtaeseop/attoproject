package com.atto.attoproject.repository;

import com.atto.attoproject.domain.TokenBlacklist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenBlacklistRepository extends JpaRepository<TokenBlacklist,Long> {
    Boolean existsByToken(String token);
}
