package com.example.dailityhabits.dto.statistic;

public record StatisticRequestDTO(int currentStreak,
                                  int maxStreak,
                                  Float percentageCompleted,
                                  int totalCompleted) {
}
