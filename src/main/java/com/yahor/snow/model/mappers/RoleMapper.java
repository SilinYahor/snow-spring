package com.yahor.snow.model.mappers;

import com.yahor.snow.model.dto.RoleDto;
import com.yahor.snow.model.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper {
    public RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    public RoleDto toDto(Role role);

    public Role fromDto(RoleDto roleDto);
}
