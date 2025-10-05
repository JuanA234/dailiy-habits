package com.example.dailityhabits.service;

import com.example.dailityhabits.DTO.UserAuth.UserInfo;
import com.example.dailityhabits.DTO.UserAuth.UserLogInDTORequest;
import com.example.dailityhabits.DTO.UserAuth.UserLogInDTOResponse;
import com.example.dailityhabits.DTO.UserAuth.UserRegisterDTORequest;
import com.example.dailityhabits.entity.Role;
import com.example.dailityhabits.entity.User;
import com.example.dailityhabits.repository.RoleRepository;
import com.example.dailityhabits.repository.UserRepository;
import com.example.dailityhabits.security.jwt.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public UserInfo addUser(UserRegisterDTORequest request) {

        if(userRepository.findByUsername(request.user()).isPresent()) {
            throw new RuntimeException("El usuario ya existe");
        }

        Role role = roleRepository.findByRole(request.role())
                .orElseThrow(() -> new RuntimeException("Role no encontrado"));

        User newUser = User.builder()
                .username(request.user())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .roles(Set.of(role))
                .build();

        userRepository.save(newUser);
        return new UserInfo(
                newUser.getUsername(),
                newUser.getEmail(),
                "******",
                role.getRole().name()
        );
    }

    public UserLogInDTOResponse login(UserLogInDTORequest request) {

        User user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));


        if(!passwordEncoder.matches(request.password(), user.getPassword())){
            throw new RuntimeException("Username or password incorrect");
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        return new UserLogInDTOResponse(user.getRoles().stream()
                .findFirst()
                .map(r->r.getRole().name())
                .orElse("UNKNOWN"), jwtUtil.generateToken(user.getUsername()));
    }
}