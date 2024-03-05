package com.yahor.snow.security;

import com.yahor.snow.additional.LoginData;
import com.yahor.snow.exception.AccessDeniedException;
import com.yahor.snow.exception.UserException;
import com.yahor.snow.model.entity.Role;
import com.yahor.snow.model.entity.User;
import com.yahor.snow.model.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccessHandler {
    private final TokenHandler tokenHandler;
    private final UserService userService;

    public User getUserByToken(String token) throws AccessDeniedException {
        LoginData loginData = tokenHandler.getLoginDataFromToken(token);
        User user;
        try {
            user = userService.getUserByLoginOrThrown(loginData.getLogin());
        } catch (UserException ex) {
            throw new AccessDeniedException();
        }
        if (!loginData.getPassword().equals(user.getPassword())) {
            throw new AccessDeniedException();
        }
        return user;
    }
}
