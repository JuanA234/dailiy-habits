package com.example.dailityhabits.DTO.reminder;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public record ResponseReminderDTO(
        @Positive(message = "El ID debe ser un número positivo")
        Long id,

        @NotNull(message = "La hora es obligatoria")
        LocalTime time,

        boolean active,

        @NotNull(message = "Los días no pueden ser nulos")
        @Size(max = 7, message = "No puede tener más de 7 días")
        List<DayOfWeek> days
) {
}
