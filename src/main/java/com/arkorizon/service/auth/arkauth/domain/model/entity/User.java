package com.arkorizon.service.auth.arkauth.domain.model.entity;

import java.util.Set;
import java.util.UUID;

public record User(
    UUID id,
    String email,
    String password,
    UUID groupId,
    UUID subGroupId,
    Set<UUID> roleIds
) {
}
