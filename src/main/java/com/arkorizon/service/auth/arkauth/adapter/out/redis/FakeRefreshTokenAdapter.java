package com.arkorizon.service.auth.arkauth.adapter.out.redis;

import com.arkorizon.service.auth.arkauth.domain.port.out.RefreshTokenPort;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * IMPLEMENTAR REDIS
 *
 * */
@Component
public class FakeRefreshTokenAdapter implements RefreshTokenPort {

    private static class RefreshTokenData {
        String token;
        Instant expiresAt;
    }

    private final Map<UUID, RefreshTokenData> refreshTokenStorage = new ConcurrentHashMap<>();

    @Override
    public void saveRefreshToken(UUID userId, String refreshToken, long expirationInSeconds) {
        RefreshTokenData data = new RefreshTokenData();
        data.token = refreshToken;
        data.expiresAt = Instant.now().plusSeconds(expirationInSeconds);
        refreshTokenStorage.put(userId, data);
    }

    @Override
    public String getRefreshTokenByUserId(UUID userId) {
        RefreshTokenData data = refreshTokenStorage.get(userId);
        if (data == null || data.expiresAt.isBefore(Instant.now())) {
            refreshTokenStorage.remove(userId);
            return null;
        }
        return data.token;
    }

    @Override
    public void deleteRefreshToken(UUID userId) {
        refreshTokenStorage.remove(userId);
    }
} 
