package com.example.dailityhabits.controller;

import com.example.dailityhabits.DTO.frequency.CreateFrequencyDTO;
import com.example.dailityhabits.DTO.frequency.ResponseFrequencyDTO;
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
    public ResponseEntity<ResponseFrequencyDTO> getFrequency(@PathVariable Long id) {
        return new ResponseEntity<>(frequencyService.getFrequencybyId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseFrequencyDTO> createFrequency(@RequestBody CreateFrequencyDTO createFrequencyDTO) {
        return new ResponseEntity<>(frequencyService.createFrequency(createFrequencyDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ResponseFrequencyDTO> updateFrequency(@RequestBody ResponseFrequencyDTO responseFrequencyDTO) {
        return new ResponseEntity<>(frequencyService.updateFrequency(responseFrequencyDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseFrequencyDTO> deleteFrequency(@PathVariable Long id) {
        frequencyService.deleteFrequencyById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
