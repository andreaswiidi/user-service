package com.andreaswidii.user.exception;

import com.andreaswidii.user.beans.ResponseWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MessageExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> badRequest(BadRequestException ex) {
        if (null != ex.getConflictingFields() && !ex.getConflictingFields().isEmpty()){
            return ResponseEntity
                    .status(ex.getHttpStatus())
                    .body(new ResponseWrapper<>().fail(ex.getConflictingFields(),ex.getErrorMessage()));
        }
        return ResponseEntity
                .status(ex.getHttpStatus())
                .body(new ResponseWrapper<>().fail(ex.getErrorMessage()));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String messageError = "";
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            messageError = error.getDefaultMessage();
        }
        return ResponseEntity.status(ex.getStatusCode()).body(new ResponseWrapper<>().fail(messageError));
    }
}
