package com.arkorizon.service.auth.arkauth.config.usecase;


import com.arkorizon.service.auth.arkauth.domain.port.in.LoginUseCase;
import com.arkorizon.service.auth.arkauth.domain.port.out.TokenServicePort;
import com.arkorizon.service.auth.arkauth.domain.port.out.UserQueryPort;
import com.arkorizon.service.auth.arkauth.domain.service.LoginUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoginUseCaseConfig {

  @Bean
  public LoginUseCase loginUseCase(final UserQueryPort userQueryPort, final TokenServicePort tokenServicePort) {
    return new LoginUseCaseImpl(userQueryPort, tokenServicePort);
  }

}
