package com.example.dailityhabits.controller;

import com.example.dailityhabits.service.interfaces.RegisterCompletedService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/registerCompleted")
public class RegisterCompletedController {
    private final RegisterCompletedService registerCompletedService;
    public RegisterCompletedController(RegisterCompletedService registerCompletedService) {
        this.registerCompletedService = registerCompletedService;
    }
}
