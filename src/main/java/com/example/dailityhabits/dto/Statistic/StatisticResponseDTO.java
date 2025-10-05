package com.example.dailityhabits.dto.Statistic;

import java.util.Date;

public record StatisticResponseDTO(Integer currentStreak,
                                   Integer maxStreak,
                                   Float percentageCompleted,
                                   Integer totalCompleted,
                                   Date startTime) {
}
