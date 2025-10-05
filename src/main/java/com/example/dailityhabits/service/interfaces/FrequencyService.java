package com.example.dailityhabits.service.interfaces;

import com.example.dailityhabits.DTO.frequency.FrequencyDTO;

public interface FrequencyService {
    FrequencyDTO getFrequencybyId(Long id);
    FrequencyDTO createFrequency(FrequencyDTO frequencyDTO);
    FrequencyDTO updateFrequency(FrequencyDTO frequencyDTO);
    void  deleteFrequencyById(Long id);
}
