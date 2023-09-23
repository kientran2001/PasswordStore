package com.example.PasswordStore.repository;

import com.example.PasswordStore.model.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {
    Iterable<Account> findByUserId(int uId);
}
