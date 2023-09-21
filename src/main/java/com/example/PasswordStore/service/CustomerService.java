package com.example.PasswordStore.service;

import com.example.PasswordStore.model.Customer;
import com.example.PasswordStore.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired  // Đánh dấu để Spring inject một đối tượng PasswordEncoder vào đây
    PasswordEncoder passwordEncoder;
    @Autowired
    CustomerRepository customerRepository;
    public Customer createCustomer(Customer customer) {
        String password = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(password);
        return customerRepository.save(customer);
    }
}
