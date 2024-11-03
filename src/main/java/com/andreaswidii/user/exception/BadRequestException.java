package com.andreaswidii.user.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class BadRequestException extends RuntimeException {
    private HttpStatus httpStatus;
    private String errorMessage;
    private List<String> conflictingFields;

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<String> getConflictingFields() {
        return conflictingFields;
    }

    public void setConflictingFields(List<String> conflictingFields) {
        this.conflictingFields = conflictingFields;
    }

    public BadRequestException(String message) {
        super(message);
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.errorMessage = message;
    }

    public BadRequestException(String message,List<String> conflictingFields) {
        super("Fields already taken: " + String.join(", ", conflictingFields));
        this.errorMessage = message;
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.conflictingFields = conflictingFields;
    }
}
