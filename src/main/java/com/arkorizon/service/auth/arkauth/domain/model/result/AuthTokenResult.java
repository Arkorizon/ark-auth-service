package com.arkorizon.service.auth.arkauth.domain.model.result;

public record AuthTokenResult(String accessToken, String refreshToken, Long expiresInSeconds) {}
