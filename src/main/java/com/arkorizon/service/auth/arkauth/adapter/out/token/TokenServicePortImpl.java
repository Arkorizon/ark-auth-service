package com.arkorizon.service.auth.arkauth.adapter.out.token;

import com.arkorizon.service.auth.arkauth.adapter.out.redis.FakeRefreshTokenAdapter;
import com.arkorizon.service.auth.arkauth.domain.exception.AuthenticationException;
import com.arkorizon.service.auth.arkauth.domain.model.entity.User;
import com.arkorizon.service.auth.arkauth.domain.model.result.AuthTokenResult;
import com.arkorizon.service.auth.arkauth.domain.port.out.TokenServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TokenServicePortImpl implements TokenServicePort {

  private final JwtEncoder jwtEncoder;
  private final FakeRefreshTokenAdapter refreshTokenAdapter;
  private final PasswordEncoder passwordEncoder;

  @Value("${jwt.expiration.accessToken}")
  private long accessTokenExpiration;

  @Value("${jwt.expiration.refreshToken}")
  private long refreshTokenExpiration;

  @Override
  public AuthTokenResult generateToken(final User user) {
    final Instant now = Instant.now();

    final String accessToken = encodeJwt(user, now, accessTokenExpiration, true);
    final String refreshToken = encodeJwt(user, now, refreshTokenExpiration, false);

    refreshTokenAdapter.saveRefreshToken(user.id(), refreshToken, refreshTokenExpiration);

    return new AuthTokenResult(accessToken, refreshToken, accessTokenExpiration);
  }

  @Override
  public void validatePassword(final String rawPassword, final String encodedPassword) {
    if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
      throw new AuthenticationException("User or password is invalid!");
    }
  }

  private String encodeJwt(final User user, final Instant now, final long expiration, final boolean includeRoles) {
    final JwtClaimsSet.Builder claims = JwtClaimsSet.builder()
        .issuer("arkauth")
        .issuedAt(now)
        .expiresAt(now.plusSeconds(expiration))
        .subject(user.id().toString())
        .claim("group_id", user.groupId().toString())
        .claim("sub_group_id", user.subGroupId().toString());

    if (includeRoles)
      claims.claim("roles", mapRoles(user.roleIds()));

    return jwtEncoder.encode(JwtEncoderParameters.from(claims.build())).getTokenValue();
  }

  private Set<String> mapRoles(final Set<UUID> roleIds) {
    return roleIds.stream().map(UUID::toString).collect(Collectors.toSet());
  }
}
