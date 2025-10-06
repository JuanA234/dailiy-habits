package com.example.dailityhabits.exception.notFound;

public class ReminderNotfoundException extends ResourceNotFoundException {
    public ReminderNotfoundException(String message) {
        super(message);
    }
}
