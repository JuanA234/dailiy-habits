package com.example.dailityhabits.mapper;

import com.example.dailityhabits.DTO.statistic.StatisticRequestDTO;
import com.example.dailityhabits.DTO.statistic.StatisticResponseDTO;
import com.example.dailityhabits.entity.Statistic;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StatisticMapper {

    @Mapping(target = "habitId", source = "habit.id")
    StatisticResponseDTO toDTO(Statistic statistic);

    Statistic toStatistic(StatisticRequestDTO statisticRequestDTO);
}
