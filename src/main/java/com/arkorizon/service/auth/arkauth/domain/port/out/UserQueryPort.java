package com.arkorizon.service.auth.arkauth.domain.port.out;

import com.arkorizon.service.auth.arkauth.domain.model.entity.User;

import java.util.UUID;

public interface UserQueryPort {
    User findByEmailAndGroup(final String email, final UUID groupId, final UUID subGroupId);
}
