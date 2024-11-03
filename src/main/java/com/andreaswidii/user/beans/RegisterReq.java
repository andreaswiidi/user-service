package com.andreaswidii.user.beans;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public class RegisterReq implements Serializable {
    @NotNull
    private String username;
    @NotNull
    @Email(message = "Not valid email format")
    private String email;
    @NotNull
    @Size(min = 10, max = 14, message = "Not valid PhoneNumber Format", groups = Size.class)
    private String phoneNumber;
    @NotNull
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
