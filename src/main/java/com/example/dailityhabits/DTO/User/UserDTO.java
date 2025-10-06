package com.example.dailityhabits.DTO.User;

public record UserDTO(
        Long id,
        String username,
        String email,
        String password) {
}
