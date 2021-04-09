package com.example.examproject.ExceptionHandling;

public class UserDuplicateException extends RuntimeException {

    public UserDuplicateException(String message) {
        super(message);
    }
}
