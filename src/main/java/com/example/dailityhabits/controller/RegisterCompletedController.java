package com.example.dailityhabits.controller;

import com.example.dailityhabits.DTO.registerCompleted.ResponseRegisterCompletedDTO;
import com.example.dailityhabits.DTO.registerCompleted.UpdateRegisterCompletedDTO;
import com.example.dailityhabits.service.interfaces.RegisterCompletedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registerCompleted")
@RequiredArgsConstructor
public class RegisterCompletedController {
    private final RegisterCompletedService registerCompletedService;


    @GetMapping
    public ResponseEntity<List<ResponseRegisterCompletedDTO>> getAllRegisters() {
        return ResponseEntity.ok(registerCompletedService.listRegisterCompleted());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseRegisterCompletedDTO> getRegisterById(@PathVariable Long id){
        return ResponseEntity.ok(registerCompletedService.findRegisterCompleted(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseRegisterCompletedDTO> updateRegister(@PathVariable Long id, @RequestBody UpdateRegisterCompletedDTO request){
        return ResponseEntity.ok(registerCompletedService.UpdateRegister(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseRegisterCompletedDTO> deleteRegister(@PathVariable Long id){
        registerCompletedService.deleteRegister(id);
        return ResponseEntity.noContent().build();
    }
}
