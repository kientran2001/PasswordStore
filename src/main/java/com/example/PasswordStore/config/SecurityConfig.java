package com.example.PasswordStore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource) {
//        UserDetails user = User.builder()
//                .username("kienv1")
//                .password("$2a$10$ZdyGMQeyjnqtzmOdRlC.H.T3d6S4j97dn4r6luSQ4CQLcmvpxWefS") // kien
//                .roles("USER")
//                .build();
//        UserDetails admin = User.builder()
//                .username("kienadminv1")
//                .password("$2a$10$rgJfoW3CMMMZRDv0dst4K.AwEBtlQBHGLcWFIVPeWOlFlDRedPXj6") // kienadmin
//                .roles("USER", "ADMIN")
//                .build();
//
//        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//        users.createUser(user);
//        users.createUser(admin);
//        return users;
//    }

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
                )
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }
}
