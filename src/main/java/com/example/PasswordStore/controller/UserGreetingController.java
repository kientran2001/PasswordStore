package com.example.PasswordStore.controller;
import com.example.PasswordStore.model.Greeting;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/v1")
@Scope("prototype")
public class UserGreetingController {
    private static final String greetingTemplate = "Hello, %s %s";
    private final AtomicLong counter = new AtomicLong();
    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "gender", defaultValue = "0") boolean gender,
                             @RequestParam(value = "userName", defaultValue = "User") String userName) {
        return Greeting.builder()
                .id(counter.incrementAndGet())
                .content(String.format(greetingTemplate, gender ? "Mr." : "Ms.", userName))
                .build();
    }
}
