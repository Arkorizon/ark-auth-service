package com.arkorizon.service.auth.arkauth.domain.exception;


public class AuthenticationException extends AuthException {

  public AuthenticationException(final String message) {
    super(message);
  }

  public AuthenticationException(final String message, final Throwable cause) {
    super(message, cause);
  }


  public AuthenticationException(final Throwable cause) {
    super(cause);
  }
}
