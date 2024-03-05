package com.yahor.snow.controller;

import com.yahor.snow.additional.AuthorizeResult;
import com.yahor.snow.additional.LoginData;
import com.yahor.snow.model.dto.UserDto;
import com.yahor.snow.model.entity.User;
import com.yahor.snow.model.mappers.UserMapper;
import com.yahor.snow.model.service.UserService;
import com.yahor.snow.security.TokenHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;
    private final TokenHandler tokenHandler;

    @PostMapping("/register")
    public AuthorizeResult register(@RequestBody UserDto userDto) {
        User user = UserMapper.INSTANCE.fromDto(userDto);

        if (userService.doesUserWithLoginExist(user.getLogin())) {
            return new AuthorizeResult(false, "Пользователь с таким логином уже существует", null);
        }
        userService.saveUser(user);
        LoginData loginData = LoginData.builder()
                .login(user.getLogin())
                .password(user.getPassword())
                .build();
        String token = tokenHandler.createTokenFromLoginData(loginData);

        return new AuthorizeResult(true, "Успешная регистрация", token);
    }
}
