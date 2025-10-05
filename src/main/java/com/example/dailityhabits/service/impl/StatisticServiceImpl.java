package com.example.dailityhabits.service.impl;

import com.example.dailityhabits.dto.Statistic.StatisticRequestDTO;
import com.example.dailityhabits.dto.Statistic.StatisticResponseDTO;
import com.example.dailityhabits.entity.Statistic;
import com.example.dailityhabits.mapper.StatisticMapper;
import com.example.dailityhabits.repository.StatisticRepository;
import com.example.dailityhabits.service.interfaces.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {

    private final StatisticRepository statisticRepository;
    private final StatisticMapper statisticMapper;


    @Override
    public StatisticResponseDTO getStatisticById(Long id) {
        return statisticRepository.findById(id).map(statisticMapper::toDTO).orElseThrow(()->new RuntimeException("Statistic not found"));
    }

    @Override
    public StatisticResponseDTO createStatistic() {
        Statistic statistic = Statistic.builder()
                .currentStreak(0)
                .maxStreak(0)
                .percentageCompleted(0.0f)
                .totalCompleted(0)
                .startTime(LocalDateTime.now())
                .build();
        return statisticMapper.toDTO(statisticRepository.save(statistic));
    }

    @Override
    public StatisticResponseDTO updateStatistic(Long id, StatisticRequestDTO statisticRequestDTO) {
        Statistic statistic = statisticRepository.findById(id).orElseThrow(()->new RuntimeException("Statistic not found"));
        statistic.setCurrentStreak(statisticRequestDTO.currentStreak());
        statistic.setMaxStreak(statisticRequestDTO.maxStreak());
        statistic.setPercentageCompleted(statisticRequestDTO.percentageCompleted());
        statistic.setTotalCompleted(statisticRequestDTO.totalCompleted());
        return statisticMapper.toDTO(statisticRepository.save(statistic));
    }

    @Override
    public void deleteStatistic(Long id) {
        if(!statisticRepository.existsById(id)) {
            throw new RuntimeException("Statistic not found");
        }
        statisticRepository.deleteById(id);
    }

    @Override
    public void calculateStreak(Long id) {
        Statistic statistic = statisticRepository.findById(id).orElseThrow(()->new RuntimeException("Statistic not found"));
        statistic.setCurrentStreak(statistic.getCurrentStreak() + 1);
        if(statistic.getCurrentStreak() > statistic.getMaxStreak()) {
            statistic.setMaxStreak(statistic.getCurrentStreak());
        }
        statisticRepository.save(statistic);
    }

    @Override
    public void calculatePercentage(Long id) {

    }
}
