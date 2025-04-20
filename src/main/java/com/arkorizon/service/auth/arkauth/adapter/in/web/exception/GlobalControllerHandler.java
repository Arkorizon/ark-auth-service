package com.arkorizon.service.auth.arkauth.adapter.in.web.exception;

import com.arkorizon.service.auth.arkauth.adapter.in.web.exception.dto.StandardErrorDTO;
import com.arkorizon.service.auth.arkauth.domain.exception.AuthenticationException;
import com.arkorizon.service.auth.arkauth.domain.exception.UserNotFoundException;
import ch.qos.logback.classic.Logger;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Collection;
import java.util.Collections;

@RestControllerAdvice
public class GlobalControllerHandler {

  private static final Logger logger = (Logger) LoggerFactory.getLogger(GlobalControllerHandler.class);

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<StandardErrorDTO> userNotFoundException(UserNotFoundException e, HttpServletRequest request) {
    return buildResponse(HttpStatus.NOT_FOUND, Collections.singletonList(e.getMessage()), request);
  }

  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<StandardErrorDTO> authenticationException(AuthenticationException e, HttpServletRequest request) {
    return buildResponse(HttpStatus.UNAUTHORIZED, Collections.singletonList(e.getMessage()), request);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<StandardErrorDTO> exception(Exception e, HttpServletRequest request) {
    logger.error("Error:{}", String.valueOf(e));
    return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, Collections.singletonList("Internal Error"), request);
  }

  private ResponseEntity<StandardErrorDTO> buildResponse(HttpStatus status, Collection<String> messages, HttpServletRequest request) {
    StandardErrorDTO error = new StandardErrorDTO(
        Instant.now(),
        status.value(),
        messages,
        request.getRequestURI());

    return ResponseEntity.status(status).body(error);

  }

}