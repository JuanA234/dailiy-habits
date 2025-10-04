package com.example.dailityhabits.service.impl;

import com.example.dailityhabits.DTO.frequency.FrequencyDTO;
import com.example.dailityhabits.mapper.FrequencyMapper;
import com.example.dailityhabits.repository.FrequencyRepository;
import com.example.dailityhabits.service.interfaces.FrequencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class FrequencyServiceImpl implements FrequencyService {

    private final FrequencyRepository frequencyRepository;
    private final FrequencyMapper frequencyMapper;

    @Override
    public FrequencyDTO getFrequencybyId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid ID");
        }

        return frequencyRepository.findById(id)
                .map(frequencyMapper::toFrequencyDTO)
                .orElseThrow(() -> new NoSuchElementException("Frequency not found"));
    }

    @Override
    public FrequencyDTO createFrequency(FrequencyDTO frequencyDTO) {
        return frequencyMapper
                .toFrequencyDTO(frequencyRepository.save(frequencyMapper.fromFrequencyDTO(frequencyDTO)));
    }

    @Override
    public FrequencyDTO updateFrequency(FrequencyDTO frequencyDTO) {
        if (frequencyDTO.id() == null || frequencyDTO.id() <= 0) {
            throw new IllegalArgumentException("Invalid ID");
        }
        return frequencyMapper.toFrequencyDTO(
                frequencyRepository.save(frequencyMapper.fromFrequencyDTO(frequencyDTO))
        );
    }

    @Override
    public void deleteFrequencyById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid ID");
        }
        frequencyRepository.deleteById(id);
    }
}
