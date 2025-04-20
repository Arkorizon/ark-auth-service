package com.arkorizon.service.auth.arkauth.adapter.in.web.dto;

import java.util.UUID;

public record LoginRequestDTO(String email, String password, UUID groupId, UUID subGroupId) {
} 
