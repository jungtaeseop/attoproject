package com.atto.attoproject.service;

import com.atto.attoproject.domain.TokenBlacklist;
import com.atto.attoproject.repository.TokenBlacklistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenBlacklistServiceImpl implements TokenBlacklistService {
    private final TokenBlacklistRepository tokenBlacklistRepository;

    @Override
    public void addToBlacklist(String token) {
        TokenBlacklist tokenBlacklist = TokenBlacklist.of(token);
        tokenBlacklistRepository.save(tokenBlacklist);
    }

    @Override
    public boolean isBlacklisted(String token) {
        return tokenBlacklistRepository.existsByToken(token);
    }
}
