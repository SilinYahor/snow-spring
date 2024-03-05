package com.yahor.snow.model.service;

import com.yahor.snow.exception.UserException;
import com.yahor.snow.model.entity.Role;
import com.yahor.snow.model.entity.User;
import com.yahor.snow.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User login(String login, String password) throws UserException {
        User user = getUserByLogin(login);
        if (user == null)
            throw new UserException("Пользователя с логином " + login + " не существует");

        if (!user.getPassword().equals(password))
            throw new UserException("Неправильный пароль");

        return user;
    }

    public User getUserByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }

    public User getUserByLoginOrThrown(String login) throws UserException {
        User user = getUserByLogin(login);
        if (user == null) {
            throw new UserException("Пользователь с логином " + login + " не найден");
        }
        return user;
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByIdOrThrown(Integer id) throws UserException {
        User user = getUserById(id);
        if (user == null) {
            throw new UserException("Пользователь с id " + id + " не найден");
        }
        return user;
    }

    public boolean doesUserWithLoginExist(String login) {
        return userRepository.findUserByLogin(login) != null;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        if (user.getRole() == null) {
            user.setRole(Role.member());
        }
        return userRepository.save(user);
    }

    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }
}