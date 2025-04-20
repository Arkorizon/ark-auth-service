package com.arkorizon.service.auth.arkauth.domain.model.command;

import java.util.UUID;

public record RefreshTokenCommand(UUID userId, String refreshToken) {}
