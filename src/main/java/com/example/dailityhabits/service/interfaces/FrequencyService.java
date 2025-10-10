package com.example.dailityhabits.service.interfaces;

import com.example.dailityhabits.DTO.frequency.CreateFrequencyDTO;
import com.example.dailityhabits.DTO.frequency.ResponseFrequencyDTO;

public interface FrequencyService {
    ResponseFrequencyDTO getFrequencybyId(Long id);
    ResponseFrequencyDTO createFrequency(CreateFrequencyDTO createFrequencyDTO);
    ResponseFrequencyDTO updateFrequency(ResponseFrequencyDTO responseFrequencyDTO);
    void  deleteFrequencyById(Long id);
}
