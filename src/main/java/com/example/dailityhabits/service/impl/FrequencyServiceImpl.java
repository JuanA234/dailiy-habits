package com.example.dailityhabits.service.impl;

import com.example.dailityhabits.DTO.frequency.CreateFrequencyDTO;
import com.example.dailityhabits.DTO.frequency.ResponseFrequencyDTO;
import com.example.dailityhabits.exception.notFound.ReminderNotFoundException;
import com.example.dailityhabits.mapper.FrequencyMapper;
import com.example.dailityhabits.repository.FrequencyRepository;
import com.example.dailityhabits.service.interfaces.FrequencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FrequencyServiceImpl implements FrequencyService {

    private final FrequencyRepository frequencyRepository;
    private final FrequencyMapper frequencyMapper;

    @Override
    public ResponseFrequencyDTO getFrequencybyId(Long id) {
        if (id == null || id <= 0) {
            throw new ReminderNotFoundException("Invalid ID");
        }

        return frequencyRepository.findById(id)
                .map(frequencyMapper::toResponseFrequencyDTO)
                .orElseThrow(() -> new ReminderNotFoundException("Frequency not found"));
    }

    @Override
    public ResponseFrequencyDTO createFrequency(CreateFrequencyDTO createFrequencyDTO) {
        return frequencyMapper
                .toResponseFrequencyDTO(frequencyRepository.save(frequencyMapper.fromCreateFrequencyDTO(createFrequencyDTO)));
    }

    @Override
    public ResponseFrequencyDTO updateFrequency(ResponseFrequencyDTO responseFrequencyDTO) {
        if (frequencyRepository.existsById(responseFrequencyDTO.id())) {
            return frequencyMapper.toResponseFrequencyDTO(
                    frequencyRepository.save(frequencyMapper.fromResponseFrequencyDTO(responseFrequencyDTO)));
        }else{
            throw new ReminderNotFoundException("Frequency not found");
        }
    }

    @Override
    public void deleteFrequencyById(Long id) {
        if (id == null || id <= 0) {
            throw new ReminderNotFoundException("Invalid ID");
        }
        if (frequencyRepository.existsById(id)) {
            frequencyRepository.deleteById(id);
        }else {
            throw new ReminderNotFoundException("Frequency not found");
        }
    }
}
