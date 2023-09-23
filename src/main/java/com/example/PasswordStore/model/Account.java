package com.example.PasswordStore.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "accounts")
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "uId")
    private int userId;

    private String acc;
    private String pass;
    private String web;
}
