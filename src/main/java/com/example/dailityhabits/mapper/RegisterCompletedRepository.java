package com.example.dailityhabits.mapper;

import com.example.dailityhabits.DTO.registerCompleted.CreateRegisterCompletedDTO;
import com.example.dailityhabits.DTO.registerCompleted.ResponseRegisterCompletedDTO;
import com.example.dailityhabits.entity.RegisterCompleted;
import org.mapstruct.*;


@Mapper(componentModel = "spring")
public interface RegisterCompletedRepository {

    @Mapping(source = "habit", target = "habit")
    ResponseRegisterCompletedDTO toDTO(RegisterCompleted registerCompleted);

    @Mapping(source = "habitId", target = "habit.id")
    RegisterCompleted toEntity(CreateRegisterCompletedDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    RegisterCompleted updateEntity(CreateRegisterCompletedDTO dto,@MappingTarget RegisterCompleted entity);


}
