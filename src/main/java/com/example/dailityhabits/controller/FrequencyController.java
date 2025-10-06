package com.example.dailityhabits.controller;

import com.example.dailityhabits.DTO.frequency.FrequencyDTO;
import com.example.dailityhabits.service.interfaces.FrequencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/frequencies")
@RequiredArgsConstructor
public class FrequencyController {

    private final FrequencyService frequencyService;

    @GetMapping("/{id}")
    public ResponseEntity<FrequencyDTO> getFrequency(@PathVariable Long id) {
        return new ResponseEntity<>(frequencyService.getFrequencybyId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FrequencyDTO> createFrequency(@RequestBody FrequencyDTO frequencyDTO) {
        return new ResponseEntity<>(frequencyService.createFrequency(frequencyDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<FrequencyDTO> updateFrequency(@RequestBody FrequencyDTO frequencyDTO) {
        return new ResponseEntity<>(frequencyService.updateFrequency(frequencyDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FrequencyDTO> deleteFrequency(@PathVariable Long id) {
        frequencyService.deleteFrequencyById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
