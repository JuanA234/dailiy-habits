package com.example.dailityhabits.mapper;

import com.example.dailityhabits.DTO.registerCompleted.CreateRegisterCompletedDTO;
import com.example.dailityhabits.DTO.registerCompleted.ResponseRegisterCompletedDTO;
import com.example.dailityhabits.DTO.registerCompleted.UpdateRegisterCompletedDTO;
import com.example.dailityhabits.entity.RegisterCompleted;
import org.mapstruct.*;


@Mapper(componentModel = "spring")
public interface RegisterCompletedMapper {

    @Mapping(source = "habit.id", target = "habitId")
    ResponseRegisterCompletedDTO toDTO(RegisterCompleted registerCompleted);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    RegisterCompleted updateEntity(UpdateRegisterCompletedDTO dto, @MappingTarget RegisterCompleted entity);


}
