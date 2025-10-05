package com.example.dailityhabits.service.impl;

import com.example.dailityhabits.DTO.habit.CreateHabitDTO;
import com.example.dailityhabits.DTO.habit.ResponseHabitDTO;
import com.example.dailityhabits.DTO.habit.UpdateHabitDTO;
import com.example.dailityhabits.DTO.registerCompleted.ResponseRegisterCompletedDTO;
import com.example.dailityhabits.service.interfaces.HabitService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitServiceImpl implements HabitService {
    @Override
    public void calculateProgress() {

    }

    @Override
    public ResponseHabitDTO createHabit(CreateHabitDTO request) {
        return null;
    }

    @Override
    public List<ResponseHabitDTO> listHabits() {
        return List.of();
    }

    @Override
    public ResponseHabitDTO findHabitById(Long id) {
        return null;
    }

    @Override
    public ResponseHabitDTO UpdateHabit(Long id, UpdateHabitDTO request) {
        return null;
    }

    @Override
    public ResponseRegisterCompletedDTO check(String notas) {
        return null;
    }
}
