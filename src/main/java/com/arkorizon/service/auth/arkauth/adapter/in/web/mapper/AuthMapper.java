package com.arkorizon.service.auth.arkauth.adapter.in.web.mapper;

import com.arkorizon.service.auth.arkauth.adapter.in.web.dto.AuthResponseDTO;
import com.arkorizon.service.auth.arkauth.adapter.in.web.dto.LoginRequestDTO;
import com.arkorizon.service.auth.arkauth.domain.model.command.LoginCommand;
import com.arkorizon.service.auth.arkauth.domain.model.result.AuthTokenResult;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    LoginCommand toCommand(LoginRequestDTO request);
    AuthResponseDTO toResponse(AuthTokenResult result);

} 
