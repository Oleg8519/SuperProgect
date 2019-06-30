package com.example.demo.exception;

public class HomeGroupDateNotFoundException extends Exception {
    private long homeGroupDateId;

    public HomeGroupDateNotFoundException(long homeGroupDateId) {
        super(String.format("HomeGroupDates is not found with id : '%s'", homeGroupDateId));
    }
}

