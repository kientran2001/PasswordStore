package com.example.PasswordStore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                .authorizeHttpRequests(authorizeHttpRequests ->
                        authorizeHttpRequests
                            .requestMatchers("/v1/register").permitAll()
                            .requestMatchers("/v1/greeting").authenticated()
                            .requestMatchers("/user/allUsers").authenticated()
                            .anyRequest().authenticated()

//                        authorizeHttpRequests
//                            .requestMatchers("/v1/register").permitAll()
//                            .requestMatchers("/v1/greeting").permitAll()
//                            .requestMatchers("/user/allUsers").permitAll()
//                            .anyRequest().permitAll()
                )
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }
}
