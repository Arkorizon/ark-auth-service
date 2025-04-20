package com.arkorizon.service.auth.arkauth.adapter.in.web.exception.dto;

import java.time.Instant;
import java.util.Collection;

public record StandardErrorDTO(
   Instant timestamp,
   Integer status,
   Collection<String> errors,
   String path

) {

}