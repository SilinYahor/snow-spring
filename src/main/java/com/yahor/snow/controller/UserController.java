package com.yahor.snow.controller;

import com.yahor.snow.additional.AuthorizeResult;
import com.yahor.snow.additional.LoginData;
import com.yahor.snow.exception.AccessDeniedException;
import com.yahor.snow.exception.UserException;
import com.yahor.snow.model.dto.UserDto;
import com.yahor.snow.model.entity.User;
import com.yahor.snow.model.mappers.UserMapper;
import com.yahor.snow.model.service.UserService;
import com.yahor.snow.security.AccessHandler;
import com.yahor.snow.security.TokenHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final TokenHandler tokenHandler;
    private final AccessHandler accessHandler;

    @GetMapping("/users")
    public List<UserDto> getUsers() {
        List<User> users = userService.getAllUsers();
        return UserMapper.INSTANCE.toDtos(users);
    }

    @GetMapping("/users/id/{id}")
    public UserDto getUserById(@PathVariable Integer id) {
        try {
            User user = userService.getUserByIdOrThrown(id);
            return UserMapper.INSTANCE.toDto(user);
        } catch (UserException ex) {
            return null;
        }
    }

    @GetMapping("/users/token/{token}")
    public UserDto getUserByToken(@PathVariable String token) {
        try {
            User user = accessHandler.getUserByToken(token);
            return UserMapper.INSTANCE.toDto(user);
        } catch (AccessDeniedException ex) {
            return null;
        }
    }

    @PostMapping("/users/save")
    public AuthorizeResult saveUser(@RequestBody UserDto userDto) {
        User user = UserMapper.INSTANCE.fromDto(userDto);
        userService.saveUser(user);

        LoginData loginData = LoginData.builder()
                .login(user.getLogin())
                .password(user.getPassword())
                .build();
        String token = tokenHandler.createTokenFromLoginData(loginData);
        return new AuthorizeResult(true, "Изменения успешно применены", token);
    }
}
