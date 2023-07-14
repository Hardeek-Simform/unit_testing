package com.unit.testing.registration.controller;

import com.unit.testing.registration.entity.User;
import com.unit.testing.registration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        if (allUsers.isEmpty()) {
            return new ResponseEntity<>(allUsers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(allUsers, HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User saved = userService.addUser(user);
        if (saved == null) {
            return new ResponseEntity<>(saved, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }
}
