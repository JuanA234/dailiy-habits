package com.example.dailityhabits.DTO.habit;

import com.example.dailityhabits.DTO.frequency.CreateFrequencyDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateHabitDTO(
        @NotBlank (message = "El nombre no puede estar vacio")
        String name,
        String description,
        String category,
        //@NotNull(message = "El habito debe tener una frecuencia")
        CreateFrequencyDTO frequency
        ) {
}
