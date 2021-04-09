package com.example.examproject.security.service.api;

import com.example.examproject.security.model.User;

import java.util.List;

public interface AppUserService {

    List<User> getAllUsers();

    User getUserById(int userId);

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(int userId);
}
