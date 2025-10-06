package com.example.dailityhabits.DTO.statistic;

import java.util.Date;

public record StatisticResponseDTO(Integer currentStreak,
                                   Integer maxStreak,
                                   Float percentageCompleted,
                                   Integer totalCompleted,
                                   Date startTime,
                                   Long habitId) {
}
