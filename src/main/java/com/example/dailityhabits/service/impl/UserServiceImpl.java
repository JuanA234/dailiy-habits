package com.example.dailityhabits.service.impl;

import com.example.dailityhabits.DTO.User.CreateUserDTO;
import com.example.dailityhabits.DTO.User.UserDTO;
import com.example.dailityhabits.entity.User;
import com.example.dailityhabits.mapper.UserMapper;
import com.example.dailityhabits.repository.UserRepository;
import com.example.dailityhabits.service.interfaces.UserService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO createUser(CreateUserDTO userDTO) {
        return userMapper.toDTO(userRepository.save(userMapper.toEntity(userDTO)));
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id).map(userMapper::toDTO)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
         User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
         userMapper.updateEntityFromDTO(userDTO, user);
         return userMapper.toDTO(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
        userRepository.deleteById(id);
    }
}
