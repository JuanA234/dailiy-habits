package com.example.dailityhabits.DTO.habit;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record UpdateHabitDTO(
        String name,
        String description,
        String category,
        LocalDateTime startDate) {
}
