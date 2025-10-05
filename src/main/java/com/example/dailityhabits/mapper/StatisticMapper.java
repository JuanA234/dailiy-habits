package com.example.dailityhabits.mapper;

import com.example.dailityhabits.dto.Statistic.StatisticRequestDTO;
import com.example.dailityhabits.dto.Statistic.StatisticResponseDTO;
import com.example.dailityhabits.entity.Statistic;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StatisticMapper {

    StatisticResponseDTO toDTO(Statistic statistic);

    Statistic toStatistic(StatisticRequestDTO statisticRequestDTO);
}
