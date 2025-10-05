package com.example.dailityhabits.DTO.frequency;

import com.example.dailityhabits.enumeration.FrequencyType;

import java.time.DayOfWeek;
import java.util.List;

public record FrequencyDTO(
        Long id,
        List<DayOfWeek> weekDays,
        FrequencyType type,
        int value
) {
}
