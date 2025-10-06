package com.example.dailityhabits.exception.notFound;

public class ReminderNotFoundException extends ResourceNotFoundException {
    public ReminderNotFoundException(String message) {
        super(message);
    }
}
