package com.example.dailityhabits.service.interfaces;

import com.example.dailityhabits.DTO.habit.UpdateHabitDTO;
import com.example.dailityhabits.DTO.registerCompleted.ResponseRegisterCompletedDTO;
import com.example.dailityhabits.DTO.registerCompleted.UpdateRegisterCompletedDTO;

import java.util.List;

public interface RegisterCompletedService {

    List<ResponseRegisterCompletedDTO> listRegisterCompleted();

    ResponseRegisterCompletedDTO findRegisterCompleted(Long id);


    ResponseRegisterCompletedDTO UpdateRegister(Long id, UpdateRegisterCompletedDTO request);

    void deleteRegister(Long id);
}
