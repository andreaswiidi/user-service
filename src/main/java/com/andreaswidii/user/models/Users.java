package com.andreaswidii.user.models;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
public class Users extends BaseModel {
    @Id
    @SequenceGenerator(
            name = "users_pk_seq",
            sequenceName = "users_pk_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "users_pk_seq"
    )
    private Long id;
    private String phoneNumber;
    private String email;
    private String username;
    private String password;
    @ColumnDefault("0")
    private Integer loginAttempt;
    private boolean isLocked;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Integer getLoginAttempt() {
        return loginAttempt;
    }

    public void setLoginAttempt(Integer loginAttempt) {
        this.loginAttempt = loginAttempt;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }
}
