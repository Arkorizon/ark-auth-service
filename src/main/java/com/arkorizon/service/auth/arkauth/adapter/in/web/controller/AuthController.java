package com.arkorizon.service.auth.arkauth.adapter.in.web.controller;

import com.arkorizon.service.auth.arkauth.adapter.in.web.dto.AuthResponseDTO;
import com.arkorizon.service.auth.arkauth.adapter.in.web.dto.LoginRequestDTO;
import com.arkorizon.service.auth.arkauth.adapter.in.web.mapper.AuthMapper;
import com.arkorizon.service.auth.arkauth.domain.model.command.LoginCommand;
import com.arkorizon.service.auth.arkauth.domain.model.result.AuthTokenResult;
import com.arkorizon.service.auth.arkauth.domain.port.in.LoginUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

  private final LoginUseCase loginUseCase;
  private final AuthMapper mapper;

  @PostMapping("/login")
  public ResponseEntity<AuthResponseDTO> login(@RequestBody final LoginRequestDTO request) {
    final LoginCommand command = mapper.toCommand(request);
    final AuthTokenResult result = loginUseCase.login(command);
    final AuthResponseDTO response = mapper.toResponse(result);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }


} 
