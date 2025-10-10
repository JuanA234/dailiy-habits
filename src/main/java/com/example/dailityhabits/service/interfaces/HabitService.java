package com.example.dailityhabits.service.interfaces;

import com.example.dailityhabits.DTO.habit.CreateHabitDTO;
import com.example.dailityhabits.DTO.habit.ResponseHabitDTO;
import com.example.dailityhabits.DTO.habit.UpdateHabitDTO;
import com.example.dailityhabits.DTO.registerCompleted.CreateRegisterCompletedDTO;
import com.example.dailityhabits.DTO.registerCompleted.ResponseRegisterCompletedDTO;

import java.util.List;

public interface HabitService{

    ResponseHabitDTO createHabit(CreateHabitDTO request, Long userId);

    List<ResponseHabitDTO> listHabits();

    ResponseHabitDTO findHabitById(Long id);

    ResponseHabitDTO updateHabit(Long id, UpdateHabitDTO request, Long userId);

    ResponseRegisterCompletedDTO checkHabit(Long id, CreateRegisterCompletedDTO registerCompletedDTO);

    void calculateProgress();

    void deleteHabit(Long id);

}
