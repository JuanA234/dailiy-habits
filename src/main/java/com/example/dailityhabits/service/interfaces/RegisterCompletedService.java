package com.example.dailityhabits.service.interfaces;

import com.example.dailityhabits.DTO.habit.UpdateHabitDTO;
import com.example.dailityhabits.DTO.registerCompleted.ResponseRegisterCompletedDTO;

public interface RegisterCompletedService {

    ResponseRegisterCompletedDTO UpdateRegister(Long id, UpdateHabitDTO request);

    void deleteRegister(Long id);
}
