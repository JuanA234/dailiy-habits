package com.example.dailityhabits.controller;

import com.example.dailityhabits.dto.Statistic.StatisticReportDTO;
import com.example.dailityhabits.dto.Statistic.StatisticRequestDTO;
import com.example.dailityhabits.dto.Statistic.StatisticResponseDTO;
import com.example.dailityhabits.service.interfaces.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/statistics")
@RequiredArgsConstructor
public class StatisticController {

    private final StatisticService statisticService;

    @GetMapping("/{id}")
    public ResponseEntity<StatisticResponseDTO> getStatisticById(@PathVariable Long id){
        return ResponseEntity.ok(statisticService.getStatisticById(id));
    }

    @PostMapping("/habit/{habitId}")
    public ResponseEntity<StatisticResponseDTO> createStatistic(@PathVariable Long habitId){
        return ResponseEntity.status(HttpStatus.CREATED).body(statisticService.createStatistic(habitId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StatisticResponseDTO> updateStatistic(@PathVariable Long id, @RequestBody StatisticRequestDTO statisticRequestDTO){
        return ResponseEntity.ok(statisticService.updateStatistic(id, statisticRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStatistic(@PathVariable Long id){
        statisticService.deleteStatistic(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/streak")
    public ResponseEntity<Void> calculateStreak(@PathVariable Long id){
        statisticService.calculateStreak(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/percentage")
    public ResponseEntity<Void> calculatePercentage(@PathVariable Long id){
        statisticService.calculatePercentage(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/report")
    public ResponseEntity<StatisticReportDTO> generateReport(@PathVariable Long id){
        return ResponseEntity.ok(statisticService.generateReport(id));
    }
}
