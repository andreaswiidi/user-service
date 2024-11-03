package com.andreaswidii.user.beans;

public class RegisterResponse {
    private UserBean user;
    private String jwtToken;
    private String refreshToken;

    public RegisterResponse(UserBean user, String jwtToken, String refreshToken) {
        this.user = user;
        this.jwtToken = jwtToken;
        this.refreshToken = refreshToken;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
