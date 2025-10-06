package com.example.dailityhabits.DTO.UserAuth;

public record UserInfo(
        String user,
        String email,
        String password,
        String role
) {
}
