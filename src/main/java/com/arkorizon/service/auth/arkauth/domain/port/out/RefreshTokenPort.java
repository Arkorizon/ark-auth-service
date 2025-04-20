package com.arkorizon.service.auth.arkauth.domain.port.out;

import java.util.UUID;

public interface RefreshTokenPort {

    void saveRefreshToken(final UUID userId, final String refreshToken, final long expirationInSeconds);

    String getRefreshTokenByUserId(final UUID userId);

    void deleteRefreshToken(final UUID userId);

}
