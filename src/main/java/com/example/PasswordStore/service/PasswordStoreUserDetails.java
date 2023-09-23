package com.example.PasswordStore.service;

import com.example.PasswordStore.model.User;
import com.example.PasswordStore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PasswordStoreUserDetails implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> users = userRepository.findByUsername(username);
        String password = null;
        List<GrantedAuthority> authorities = null;

        if(users.isEmpty()) {
            throw new UsernameNotFoundException("User details not found for username = " + username);
        }

        username = users.get(0).getUsername();
        password = users.get(0).getPassword();
        authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(users.get(0).getRole()));

        return new org.springframework.security.core.userdetails.User(username, password, authorities);
    }
}
