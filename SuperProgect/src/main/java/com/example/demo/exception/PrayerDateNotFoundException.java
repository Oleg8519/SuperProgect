package com.example.demo.exception;

public class PrayerDateNotFoundException extends Exception {
    private long prayerDateId;

    public PrayerDateNotFoundException(long prayerDateId) {
        super(String.format("PrayerDates is not found with id : '%s'", prayerDateId));
    }
}

