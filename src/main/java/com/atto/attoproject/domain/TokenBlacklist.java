package com.atto.attoproject.domain;

import com.atto.attoproject.config.basedomain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TokenBlacklist extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    public TokenBlacklist(String token) {
        this.token = token;
    }

    public static TokenBlacklist of(String token) {
        return new TokenBlacklist(token);
    }
}
