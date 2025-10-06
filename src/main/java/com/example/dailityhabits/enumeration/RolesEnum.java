package com.example.dailityhabits.enumeration;

public enum RolesEnum {
    USER("USER"),
    ADMIN("ADMIN");

    private final String role;

    private RolesEnum(String role) {
        this.role = role;
    }
}
