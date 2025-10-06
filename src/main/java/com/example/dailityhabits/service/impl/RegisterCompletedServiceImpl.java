package com.example.dailityhabits.service.impl;

import com.example.dailityhabits.DTO.registerCompleted.ResponseRegisterCompletedDTO;
import com.example.dailityhabits.DTO.registerCompleted.UpdateRegisterCompletedDTO;
import com.example.dailityhabits.entity.RegisterCompleted;
import com.example.dailityhabits.exception.notFound.RegisterNotFoundException;
import com.example.dailityhabits.mapper.RegisterCompletedMapper;
import com.example.dailityhabits.repository.RegisterCompletedRepository;
import com.example.dailityhabits.service.interfaces.RegisterCompletedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegisterCompletedServiceImpl implements RegisterCompletedService {

    private final RegisterCompletedMapper registerCompletedMapper;
    private final RegisterCompletedRepository registerCompletedRepository;


    @Override
    public void deleteRegister(Long id) {
        if(!registerCompletedRepository.existsById(id)){
            throw new RegisterNotFoundException("Register not found");
        }
        registerCompletedRepository.deleteById(id);
    }

    @Override
    public List<ResponseRegisterCompletedDTO> listRegisterCompleted() {
        return registerCompletedRepository.findAll().
                stream().map(registerCompletedMapper::toDTO)
                .toList();
    }

    @Override
    public ResponseRegisterCompletedDTO findRegisterCompleted(Long id) {
        return registerCompletedMapper.toDTO(registerCompletedRepository.findById(id)
                .orElseThrow(() -> new RegisterNotFoundException("Register not found")));
    }

    @Override
    public ResponseRegisterCompletedDTO UpdateRegister(Long id, UpdateRegisterCompletedDTO request) {

        RegisterCompleted  registerCompletedToUpdate = registerCompletedRepository.findById(id)
                .orElseThrow(() -> new RegisterNotFoundException("Register not found"));

        registerCompletedMapper.updateEntity(request, registerCompletedToUpdate);

        return registerCompletedMapper.toDTO(registerCompletedRepository.save(registerCompletedToUpdate));
    }
}
