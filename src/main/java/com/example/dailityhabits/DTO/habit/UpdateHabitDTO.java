package com.example.dailityhabits.DTO.habit;

import com.example.dailityhabits.DTO.frequency.UpdateFrequencyDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record UpdateHabitDTO(
        String name,
        String description,
        String category,
        LocalDateTime startDate,
        UpdateFrequencyDTO frequency) {
}
