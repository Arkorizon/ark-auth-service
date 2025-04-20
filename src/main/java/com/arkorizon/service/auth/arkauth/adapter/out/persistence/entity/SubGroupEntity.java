package com.arkorizon.service.auth.arkauth.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "vw_sub_group")
@Data
public class SubGroupEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "group_id", nullable = false)
    private GroupEntity group;

}
