package com.example.PasswordStore.service;

import com.example.PasswordStore.model.Account;
import com.example.PasswordStore.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AccountRepository accountRepository;

    public Account createAccount(Account account) {
        String pass = passwordEncoder.encode(account.getPass());
        account.setPass(pass);
        return accountRepository.save(account);
    }
}
