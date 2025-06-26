package com.example.properly.auth;

import jakarta.persistence.*;
import com.example.properly.auth.Role;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role = Role.TENANT;

    public Users() {}

    public Users(String username, String password) {
        this(username, password, Role.TENANT);
    }

    public Users(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
