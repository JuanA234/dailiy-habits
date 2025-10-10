package com.example.dailityhabits.DTO.registerCompleted;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record CreateRegisterCompletedDTO(
        String notes
        ) {
}
