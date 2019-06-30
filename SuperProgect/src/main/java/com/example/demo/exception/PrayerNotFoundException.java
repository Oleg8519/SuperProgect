package com.example.demo.exception;

public class PrayerNotFoundException extends Exception {
    private long prayerId;

    public PrayerNotFoundException(long prayerId) {
        super(String.format("Prayer is not found with id : '%s'", prayerId));
    }
}

