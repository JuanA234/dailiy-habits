package com.example.dailityhabits.service.interfaces;


import com.example.dailityhabits.DTO.User.CreateUserDTO;
import com.example.dailityhabits.DTO.User.UserDTO;
import com.example.dailityhabits.entity.User;

public interface UserService {

    UserDTO createUser(CreateUserDTO userDTO);

    UserDTO getUserById(Long id);

    UserDTO updateUser(Long id, UserDTO userDTO);

    void deleteUser(Long id);
}
