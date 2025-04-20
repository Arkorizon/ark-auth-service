package com.arkorizon.service.auth.arkauth.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "vw_permission")
@Data
public class PermissionEntity {

  @Id
  private UUID id;

  @Column(nullable = false, unique = true)
  private String authority;
}
