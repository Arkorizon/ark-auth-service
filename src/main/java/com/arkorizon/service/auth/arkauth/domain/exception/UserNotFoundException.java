package com.arkorizon.service.auth.arkauth.domain.exception;


public class UserNotFoundException extends AuthException {

  public UserNotFoundException(String message) {
    super(message);
  }

  public UserNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }


  public UserNotFoundException(Throwable cause) {
    super(cause);
  }
}
