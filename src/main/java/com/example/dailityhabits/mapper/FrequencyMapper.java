package com.example.dailityhabits.mapper;

import com.example.dailityhabits.DTO.frequency.FrequencyDTO;
import com.example.dailityhabits.entity.Frequency;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FrequencyMapper {
    Frequency fromFrequencyDTO(FrequencyDTO frequencyDTO);
    FrequencyDTO toFrequencyDTO(Frequency frequency);
}
