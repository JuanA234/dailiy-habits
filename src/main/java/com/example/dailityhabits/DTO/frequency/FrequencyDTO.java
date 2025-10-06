package com.example.dailityhabits.DTO.frequency;

import com.example.dailityhabits.enumeration.FrequencyType;
import jakarta.validation.constraints.*;

import java.time.DayOfWeek;
import java.util.List;

public record FrequencyDTO(
        @Positive(message = "El ID debe ser un número positivo")
        Long id,

        @NotNull(message = "Los días de la semana no pueden ser nulos")
        @Size(max = 7, message = "No puede tener más de 7 días")
        List<DayOfWeek> weekDays,

        @NotNull(message = "El tipo de frecuencia es obligatorio")
        FrequencyType type,

        @Positive(message = "El valor debe ser mayor a 0")
        @Max(value = 365, message = "El valor no puede ser mayor a 365")
        int value
) {
}
