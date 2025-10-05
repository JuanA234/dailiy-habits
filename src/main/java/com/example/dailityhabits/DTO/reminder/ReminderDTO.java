package com.example.dailityhabits.DTO.reminder;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public record ReminderDTO(
        Long id,
        LocalTime time,
        boolean active,
        List<DayOfWeek>days
) {
}
