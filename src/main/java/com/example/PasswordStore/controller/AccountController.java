package com.example.PasswordStore.controller;

import com.example.PasswordStore.model.Account;
import com.example.PasswordStore.repository.AccountRepository;
import com.example.PasswordStore.repository.UserRepository;
import com.example.PasswordStore.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/account")
public class AccountController {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountService accountService;

    @GetMapping("/userId/{uId}")
    public ResponseEntity<String> getAccountsByUserId(@PathVariable int uId) {
        ResponseEntity<String> response = null;

        Iterable<Account> accounts = accountRepository.findByUserId(uId);
        response = ResponseEntity.status(HttpStatus.OK)
                .body("All accounts of user has userId = " + uId + ": " + accounts.toString());
        return response;
    }

    @PostMapping("/{uId}/insertAccount")
    public ResponseEntity<String> insertAccount(@RequestBody Account account,
                                                @PathVariable int uId) {
        ResponseEntity<String> response = null;
        try {
            account.setUserId(uId);
            Account savedAccount = accountService.createAccount(account);
            if(savedAccount.getId() > 0) {
                response = ResponseEntity.status(HttpStatus.OK)
                        .body("Account is created successfully for account: " + account.getAcc() + " has userID: " + uId);
            }
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Server error: " + e.getMessage());
        }

        return response;
    }

    @DeleteMapping("/deleteAccount/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable int id) {
        Optional<Account> account = accountRepository.findById(id);
        ResponseEntity<String> response = ResponseEntity.status(HttpStatus.OK)
                .body("Deleted account: " + account.get().getAcc());
        try {
            accountRepository.deleteById(id);
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Server error: " + e.getMessage());
        }

        return response;
    }

}
