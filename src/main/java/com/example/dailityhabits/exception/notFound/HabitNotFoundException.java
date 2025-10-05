package com.example.dailityhabits.exception.notFound;

public class HabitNotFoundException extends ResourceNotFoundException{
    public HabitNotFoundException(String message) {
        super(message);
    }
}
