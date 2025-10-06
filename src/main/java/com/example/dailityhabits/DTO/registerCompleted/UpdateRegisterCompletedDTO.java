package com.example.dailityhabits.DTO.registerCompleted;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record UpdateRegisterCompletedDTO(
        LocalDateTime date,
        LocalTime time,
        String notas
) {
}
