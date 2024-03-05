package com.yahor.snow.security;

import com.yahor.snow.additional.LoginData;
import com.yahor.snow.exception.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
public class TokenHandler {
    private final String SEPARATOR = "!':,,::!!::,,!-___!!!_-";
    //   silinyahor73!':,,::!!::,,!-___!!!_-123456789

    public String createTokenFromLoginData(LoginData loginData) {
        String login = loginData.getLogin();
        String password = loginData.getPassword();

        return login + SEPARATOR + password;
    }

    public LoginData getLoginDataFromToken(String token) throws AccessDeniedException {
        if (token == null)
            throw new AccessDeniedException();

        String[] data = token.split(SEPARATOR, 2);
        if (data.length != 2)
            throw new AccessDeniedException();

        return LoginData.builder()
                .login(data[0])
                .password(data[1])
                .build();
    }
}
