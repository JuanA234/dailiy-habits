package com.example.dailityhabits.dto.statistic;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record StatisticReportDTO(String habitName,
                                 String category,
                                 Integer currentStreak,
                                 Integer maxStreak,
                                 Float percentageCompleted,
                                 Integer totalCompleted,
                                 LocalDateTime startDate,
                                 LocalDateTime reportGeneratedAt,
                                 Long daysActive,
                                 Float weeklyAverage,
                                 List<LocalDate> lastCompletions) {
}
