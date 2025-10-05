package com.example.dailityhabits.mapper;

import com.example.dailityhabits.DTO.User.CreateUserDTO;
import com.example.dailityhabits.DTO.User.UserDTO;
import com.example.dailityhabits.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(User user);
    User toEntity(CreateUserDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User updateEntityFromDTO(UserDTO dto, @MappingTarget User user);
}
