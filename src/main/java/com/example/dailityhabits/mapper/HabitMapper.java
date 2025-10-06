package com.example.dailityhabits.mapper;


import com.example.dailityhabits.DTO.habit.CreateHabitDTO;
import com.example.dailityhabits.DTO.habit.ResponseHabitDTO;
import com.example.dailityhabits.DTO.habit.UpdateHabitDTO;
import com.example.dailityhabits.entity.Habit;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface HabitMapper {

    ResponseHabitDTO toDTO(Habit habit);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "frecuencyId", target = "frequency.id")
    @Mapping(source = "reminderId", target = "reminder.id")
    Habit toEntity(CreateHabitDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Habit UpdateHabitFromDTO(UpdateHabitDTO dto, @MappingTarget Habit entity);


}
