package com.example.dailityhabits.service.interfaces;

import com.example.dailityhabits.DTO.habit.CreateHabitDTO;
import com.example.dailityhabits.DTO.habit.ResponseHabitDTO;
import com.example.dailityhabits.DTO.habit.UpdateHabitDTO;
import com.example.dailityhabits.DTO.registerCompleted.CreateRegisterCompletedDTO;
import com.example.dailityhabits.DTO.registerCompleted.ResponseRegisterCompletedDTO;
import com.example.dailityhabits.entity.Habit;
import com.example.dailityhabits.entity.RegisterCompleted;

import java.util.List;

public interface HabitService{

    ResponseHabitDTO createHabit(CreateHabitDTO request);

    List<ResponseHabitDTO> listHabits();

    ResponseHabitDTO findHabitById(Long id);

    ResponseHabitDTO UpdateHabit(Long id, UpdateHabitDTO request);

    ResponseRegisterCompletedDTO checkHabit(Long id, CreateRegisterCompletedDTO registerCompletedDTO);

    void calculateProgress();

    void deleteHabit(Long id);

}
