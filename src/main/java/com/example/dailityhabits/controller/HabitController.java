package com.example.dailityhabits.controller;


import com.example.dailityhabits.DTO.habit.CreateHabitDTO;
import com.example.dailityhabits.DTO.habit.ResponseHabitDTO;
import com.example.dailityhabits.DTO.habit.UpdateHabitDTO;
import com.example.dailityhabits.DTO.registerCompleted.CreateRegisterCompletedDTO;
import com.example.dailityhabits.DTO.registerCompleted.ResponseRegisterCompletedDTO;
import com.example.dailityhabits.service.interfaces.HabitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habits")
@RequiredArgsConstructor
public class HabitController {

    private final HabitService habitService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseHabitDTO> getHabit(@PathVariable Long id) {
        return ResponseEntity.ok(habitService.findHabitById(id));
    }

    @GetMapping
    public ResponseEntity<List<ResponseHabitDTO>> getAllHabits() {
        return ResponseEntity.ok(habitService.listHabits());
    }

    @PostMapping
    public ResponseEntity<ResponseHabitDTO> createHabit(@RequestBody @Valid CreateHabitDTO habit){
        return ResponseEntity.status(HttpStatus.CREATED).body(habitService.createHabit(habit));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseHabitDTO> updateHabit(@PathVariable Long id, @RequestBody @Valid UpdateHabitDTO habit){
        return ResponseEntity.ok(habitService.updateHabit(id, habit));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseHabitDTO> deleteHabit(@PathVariable Long id){
        habitService.deleteHabit(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}")
    public ResponseEntity<ResponseRegisterCompletedDTO> checkHabit(@PathVariable Long id, @RequestBody CreateRegisterCompletedDTO registerCompletedDTO){
        return ResponseEntity.ok(habitService.checkHabit(id, registerCompletedDTO));
    }
}
