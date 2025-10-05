package com.example.dailityhabits.controller;

import com.example.dailityhabits.DTO.UserAuth.UserInfo;
import com.example.dailityhabits.DTO.UserAuth.UserLogInDTORequest;
import com.example.dailityhabits.DTO.UserAuth.UserLogInDTOResponse;
import com.example.dailityhabits.DTO.UserAuth.UserRegisterDTORequest;
import com.example.dailityhabits.security.jwt.JwtUtil;
import com.example.dailityhabits.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<UserInfo> addNewUser(@RequestBody UserRegisterDTORequest request) {
        UserInfo response = authService.addUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<UserLogInDTOResponse> authenticateAndGetToken(@RequestBody UserLogInDTORequest userLoginDTORequest) {
        return ResponseEntity.ok(authService.login(userLoginDTORequest));
    }
}
