package com.example.PasswordStore.controller;

import com.example.PasswordStore.model.User;
import com.example.PasswordStore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/allUsers")
    public ResponseEntity<String> getAllUsers() {
        ResponseEntity<String> response = null;

        Iterable<User> users = userRepository.findAll();
        response = ResponseEntity.status(HttpStatus.OK)
                .body("List users: " + users);

        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getUserById(@PathVariable int id) {
        ResponseEntity<String> response = null;
        Optional<User> user = userRepository.findById(id);

        try {
            if(!user.isEmpty()) {
                response = ResponseEntity.status(HttpStatus.OK)
                        .body("User: " + user);
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("User not found!!!");
            }
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Server error: " + e.getMessage());
        }

        return response;
    }
}
