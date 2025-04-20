package com.arkorizon.service.auth.arkauth.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Entity
@Table(name = "vw_group")
@Data
public class GroupEntity {

  @Id
  private UUID id;

  @Column(nullable = false, unique = true)
  private String name;

}
