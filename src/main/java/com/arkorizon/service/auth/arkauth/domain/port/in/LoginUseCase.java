package com.arkorizon.service.auth.arkauth.domain.port.in;

import com.arkorizon.service.auth.arkauth.domain.model.command.LoginCommand;
import com.arkorizon.service.auth.arkauth.domain.model.result.AuthTokenResult;

public interface LoginUseCase {
  AuthTokenResult login(final LoginCommand command);
}