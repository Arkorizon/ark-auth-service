package com.arkorizon.service.auth.arkauth.domain.port.out;

import com.arkorizon.service.auth.arkauth.domain.model.entity.User;
import com.arkorizon.service.auth.arkauth.domain.model.result.AuthTokenResult;

public interface TokenServicePort {
    AuthTokenResult generateToken(final User user);

    void validatePassword(final String rawPassword, final String encodedPassword);
}
