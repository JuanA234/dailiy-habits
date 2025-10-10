package com.example.dailityhabits.mapper;

import com.example.dailityhabits.DTO.frequency.CreateFrequencyDTO;
import com.example.dailityhabits.DTO.frequency.ResponseFrequencyDTO;
import com.example.dailityhabits.entity.Frequency;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FrequencyMapper {
    Frequency fromCreateFrequencyDTO(CreateFrequencyDTO responseFrequencyDTO);
    Frequency fromResponseFrequencyDTO(ResponseFrequencyDTO responseFrequencyDTO);
    ResponseFrequencyDTO toResponseFrequencyDTO(Frequency frequency);
}
