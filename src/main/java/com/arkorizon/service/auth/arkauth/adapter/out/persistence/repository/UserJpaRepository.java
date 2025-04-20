package com.arkorizon.service.auth.arkauth.adapter.out.persistence.repository;

import com.arkorizon.service.auth.arkauth.adapter.out.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, UUID> {

  Optional<UserEntity> findByEmailAndGroupIdAndSubGroupId(final String email, final UUID groupId, final UUID subGroupId);

}
