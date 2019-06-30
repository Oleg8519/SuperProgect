package com.example.demo.exception;

public class ActivityNotFoundException extends Exception {
    private long activityId;

    public ActivityNotFoundException(long activityId) {
        super(String.format("Activity is not found with id : '%s'", activityId));
    }
}