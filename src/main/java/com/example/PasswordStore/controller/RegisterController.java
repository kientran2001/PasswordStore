package com.example.PasswordStore.controller;

import com.example.PasswordStore.model.User;
import com.example.PasswordStore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class RegisterController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@RequestBody User user) {
        ResponseEntity<String> response = null;

        try {
            User savedUser = userService.createUser(user);
            if(savedUser.getId() > 0) {
                response = ResponseEntity.status(HttpStatus.CREATED)
                        .body("User is created successfully for user = " + user.getUsername());
            }
        } catch (Exception exception) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occurred from server with exception = " + exception.getMessage());
        }

        return response;
    }
}
