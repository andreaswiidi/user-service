package com.andreaswidii.user.beans;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ResponseWrapper <T> {
    private String message;
    private T data;
    private Date timestamp = new Date();

    public ResponseWrapper<T> success() {
        this.message = "Success";

        return this;
    }

    public ResponseWrapper<T> success(T t) {
        this.message = "Success";
        this.data = t;

        return this;
    }

    public ResponseWrapper<T> fail(String message) {
        this.message = message;

        return this;
    }

    public ResponseWrapper<T> fail(T t, String message) {
        this.message = message;
        this.data = t;

        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
