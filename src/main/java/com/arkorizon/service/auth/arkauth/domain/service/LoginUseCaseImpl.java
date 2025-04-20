package com.arkorizon.service.auth.arkauth.domain.service;

import com.arkorizon.service.auth.arkauth.domain.model.command.LoginCommand;
import com.arkorizon.service.auth.arkauth.domain.model.entity.User;
import com.arkorizon.service.auth.arkauth.domain.model.result.AuthTokenResult;
import com.arkorizon.service.auth.arkauth.domain.port.in.LoginUseCase;
import com.arkorizon.service.auth.arkauth.domain.port.out.TokenServicePort;
import com.arkorizon.service.auth.arkauth.domain.port.out.UserQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUseCaseImpl implements LoginUseCase {

    private final UserQueryPort userQueryPort;
    private final TokenServicePort tokenServicePort;

    @Override
    public AuthTokenResult login(final LoginCommand command) {
        final User user = userQueryPort.findByEmailAndGroup(command.email(), command.groupId(), command.subGroupId());
        tokenServicePort.validatePassword(command.password(), user.password());
        return tokenServicePort.generateToken(user);
    }
}
