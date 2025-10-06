package com.example.dailityhabits.DTO.User;

public record CreateUserDTO(
        String username,
        String email,
        String password
) {
}
