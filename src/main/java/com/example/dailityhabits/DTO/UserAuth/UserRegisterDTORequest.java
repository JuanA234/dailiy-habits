package com.example.dailityhabits.DTO.UserAuth;

import com.example.dailityhabits.enumeration.RolesEnum;

public record UserRegisterDTORequest(
        String user,
        String email,
        String password,
        RolesEnum role
) {
}
