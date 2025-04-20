package com.arkorizon.service.auth.arkauth.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "vw_role")
@Data
public class RoleEntity {

  @Id
  private UUID id;

  @Column(nullable = false, unique = true)
  private String name;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "role_permission",
      joinColumns = @JoinColumn(name = "role_id"),
      inverseJoinColumns = @JoinColumn(name = "permission_id")
  )
  private Set<PermissionEntity> permissions = new HashSet<>();
}