package com.example.dailityhabits.DTO.habit;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ResponseHabitDTO(
        Long id,
        String name,
        String description,
        String category,
        LocalDateTime startDate
) {
}
