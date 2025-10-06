package com.example.dailityhabits.service.interfaces;

import com.example.dailityhabits.dto.Statistic.StatisticReportDTO;
import com.example.dailityhabits.dto.Statistic.StatisticRequestDTO;
import com.example.dailityhabits.dto.Statistic.StatisticResponseDTO;

public interface StatisticService {
    StatisticResponseDTO getStatisticById(Long id);
    StatisticResponseDTO createStatistic(Long habitId);
    StatisticResponseDTO updateStatistic(Long id, StatisticRequestDTO statisticRequestDTO);
    void deleteStatistic(Long id);
    void calculateStreak(Long id);
    void calculatePercentage(Long id);
    StatisticReportDTO generateReport(Long id);

}
