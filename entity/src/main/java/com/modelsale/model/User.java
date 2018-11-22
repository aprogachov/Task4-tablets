package com.modelsale.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user")
@Table(name = "USERS")
public class User implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", unique = true, nullable = false)
    private Integer id;

    @Column(name = "LOGIN", nullable = false, length = 20)
    private String login;

    @Column(name = "EMAIL", nullable = false, length = 30)
    private String email;

    @Column(name = "PASSWORD", nullable = false, length = 100)
    private String password;

    public User(String login, String email, String password) {
        this.login = login;
        this.email = email;
        this.password = password;
    }

}