package com.example.demo.exception;

public class HomeGroupNotFoundException extends Exception {
    private long homeGroupId;

    public HomeGroupNotFoundException(long homeGroupId) {
        super(String.format("HomeGroup is not found with id : '%s'", homeGroupId));
    }
}
