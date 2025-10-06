package com.example.dailityhabits.exception.notFound;

public class RegisterNotFoundException extends ResourceNotFoundException{
    public RegisterNotFoundException(String message) {
        super(message);
    }
}
