package com.example.dailityhabits.service.impl;

import com.example.dailityhabits.DTO.habit.UpdateHabitDTO;
import com.example.dailityhabits.DTO.registerCompleted.ResponseRegisterCompletedDTO;
import com.example.dailityhabits.service.interfaces.RegisterCompletedService;
import org.springframework.stereotype.Service;

@Service
public class RegisterCompletedServideImpl implements RegisterCompletedService {
    @Override
    public void deleteRegister(Long id) {

    }

    @Override
    public ResponseRegisterCompletedDTO UpdateRegister(Long id, UpdateHabitDTO request) {
        return null;
    }
}
