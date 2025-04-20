package com.arkorizon.service.auth.arkauth.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Immutable;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "vw_user")
@Immutable
@Data
public class UserEntity {

  @Id
  private UUID id;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  @JoinColumn(name = "group_id", nullable = false)
  private GroupEntity group;

  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  @JoinColumn(name = "sub_group_id", nullable = false)
  private SubGroupEntity subGroup;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "user_role",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id")
  )
  private Set<RoleEntity> roles = new HashSet<>();

  public Set<PermissionEntity> getAllPermissions() {
    return roles.stream()
        .flatMap(role -> role.getPermissions().stream())
        .collect(Collectors.toSet());
  }
}
