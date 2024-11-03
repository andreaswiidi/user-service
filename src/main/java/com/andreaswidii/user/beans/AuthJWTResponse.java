package com.andreaswidii.user.beans;


import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthJWTResponse {
    @JsonProperty("data")
    private Data data;

    @JsonProperty("message")
    private String message;

    @JsonProperty("timestamp")
    private String timestamp;

    public AuthJWTResponse(Data data, String message, String timestamp) {
        this.data = data;
        this.message = message;
        this.timestamp = timestamp;
    }

    public static class Data{
        @JsonProperty("jwtToken")
        private String jwtToken;

        @JsonProperty("refreshToken")
        private String refreshToken;

        public Data(String jwtToken, String refreshToken) {
            this.jwtToken = jwtToken;
            this.refreshToken = refreshToken;
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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
