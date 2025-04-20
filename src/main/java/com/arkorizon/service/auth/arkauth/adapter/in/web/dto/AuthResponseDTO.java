package com.arkorizon.service.auth.arkauth.adapter.in.web.dto;

public record AuthResponseDTO(String accessToken, String refreshToken, Long expiresInSeconds) {
} 
