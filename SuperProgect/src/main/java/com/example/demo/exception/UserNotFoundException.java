package com.example.demo.exception;

public class UserNotFoundException extends Exception {
    private long userId;

    public UserNotFoundException(long userId) {
        super(String.format("User is not found with id : '%s'", userId));
    }
}
