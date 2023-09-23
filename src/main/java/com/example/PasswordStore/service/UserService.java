package com.example.PasswordStore.service;

import com.example.PasswordStore.model.User;
import com.example.PasswordStore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired  // Đánh dấu để Spring inject một đối tượng PasswordEncoder vào đây
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    public User createUser(User user) {
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        return userRepository.save(user);
    }
}
