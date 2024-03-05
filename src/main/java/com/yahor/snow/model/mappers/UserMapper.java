package com.yahor.snow.model.mappers;

import com.yahor.snow.model.dto.UserDto;
import com.yahor.snow.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    public UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    public UserDto toDto(User user);
    public List<UserDto> toDtos(List<User> users);

    public User fromDto(UserDto userDto);
}
