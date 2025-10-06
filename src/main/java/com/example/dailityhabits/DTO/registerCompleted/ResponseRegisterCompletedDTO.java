package com.example.dailityhabits.DTO.registerCompleted;

import com.example.dailityhabits.DTO.habit.ResponseHabitDTO;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record ResponseRegisterCompletedDTO(
        Long id,
        LocalDate date,
        LocalTime time,
        String notas,
        ResponseHabitDTO habit

        ) {
}
