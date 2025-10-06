package com.example.dailityhabits.DTO.statistic;

public record StatisticRequestDTO(int currentStreak,
                                  int maxStreak,
                                  Float percentageCompleted,
                                  int totalCompleted) {
}
