package com.arkorizon.service.auth.arkauth.adapter.out.persistence;

import com.arkorizon.service.auth.arkauth.adapter.out.persistence.mapper.UserEntityMapper;
import com.arkorizon.service.auth.arkauth.adapter.out.persistence.repository.UserJpaRepository;
import com.arkorizon.service.auth.arkauth.domain.model.entity.User;
import com.arkorizon.service.auth.arkauth.domain.port.out.UserQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserQueryPort {

  private final UserJpaRepository userJpaRepository;
  private final UserEntityMapper mapper;

  @Override
  public User findByEmailAndGroup(final String email, final UUID groupId, final UUID subGroupId) {
    return userJpaRepository
        .findByEmailAndGroupIdAndSubGroupId(email, groupId, subGroupId)
        .map(mapper::toDomain).orElseThrow();
  }
}
