package com.arkorizon.service.auth.arkauth.domain.model.command;

import java.util.UUID;

public record LoginCommand(
    String email,
    String password,
    UUID groupId,
    UUID subGroupId
) {}
