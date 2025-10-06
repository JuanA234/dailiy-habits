package com.example.dailityhabits.DTO.habit;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateHabitDTO(
        @NotBlank String name,
        String description,
        String category,
        @NotNull LocalDateTime startDate,
        @NotNull Long frecuencyId,
        @NotNull Long statisticId

        ) {
}
