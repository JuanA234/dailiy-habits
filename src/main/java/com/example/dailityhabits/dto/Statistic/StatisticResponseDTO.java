package com.example.dailityhabits.dto.Statistic;

import java.util.Date;

public record StatisticResponseDTO(int currentStreak,
                                   int maxStreak,
                                   float percentageCompleted,
                                   int totalCompleted,
                                   Date startTime) {
}
