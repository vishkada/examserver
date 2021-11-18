package com.exam.examserver.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidUserException.class)
    public ResponseEntity<Object> handleInvalidUserException(InvalidUserException exp){
        return new ResponseEntity<>(exp.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
