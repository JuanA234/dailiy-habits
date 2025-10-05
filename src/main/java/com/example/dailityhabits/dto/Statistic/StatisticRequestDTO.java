package com.example.dailityhabits.dto.Statistic;

public record StatisticRequestDTO(int currentStreak,
                                  int maxStreak,
                                  Float percentageCompleted,
                                  int totalCompleted) {
}
