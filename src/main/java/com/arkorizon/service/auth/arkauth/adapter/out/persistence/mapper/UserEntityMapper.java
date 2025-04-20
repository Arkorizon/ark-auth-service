package com.arkorizon.service.auth.arkauth.adapter.out.persistence.mapper;

import com.arkorizon.service.auth.arkauth.adapter.out.persistence.entity.RoleEntity;
import com.arkorizon.service.auth.arkauth.adapter.out.persistence.entity.UserEntity;
import com.arkorizon.service.auth.arkauth.domain.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {

    @Mapping(target = "subGroupId", source = "subGroup.id")
    @Mapping(target = "groupId", source = "group.id")
    @Mapping(source = "roles", target = "roleIds", qualifiedByName = "mapRoleEntitiesToIds")
    User toDomain(final UserEntity entity);

    @Named("mapRoleEntitiesToIds")
    static Set<UUID> mapRoleEntitiesToIds(final Set<RoleEntity> roles) {
        return roles == null ? Set.of() : roles.stream()
                .map(RoleEntity::getId)
                .collect(Collectors.toSet());
    }
}
