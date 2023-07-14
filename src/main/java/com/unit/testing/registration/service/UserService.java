package com.unit.testing.registration.service;

import com.unit.testing.registration.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User addUser(User user);

    void deleteUserById(int id);
}
