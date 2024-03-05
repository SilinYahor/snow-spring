package com.yahor.snow.controller;

import com.yahor.snow.additional.AuthorizeResult;
import com.yahor.snow.additional.LoginData;
import com.yahor.snow.exception.UserException;
import com.yahor.snow.model.entity.User;
import com.yahor.snow.model.service.UserService;
import com.yahor.snow.security.TokenHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;
    private final TokenHandler tokenHandler;

    @PostMapping("/login")
    public AuthorizeResult login(@RequestBody LoginData loginData) {
        try {
            User user = userService.login(loginData.getLogin(), loginData.getPassword());
        } catch (UserException ex) {
            return new AuthorizeResult(false, ex.getMessage(), null);
        }

        String token = tokenHandler.createTokenFromLoginData(loginData);
        return new AuthorizeResult(true, "Вы успешно вошли в свой аккаунт", token);
    }
}