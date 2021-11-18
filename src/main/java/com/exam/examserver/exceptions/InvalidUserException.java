package com.exam.examserver.exceptions;

public class InvalidUserException extends RuntimeException{
    public InvalidUserException(String errorMsg){
     super(errorMsg);
    }
}
