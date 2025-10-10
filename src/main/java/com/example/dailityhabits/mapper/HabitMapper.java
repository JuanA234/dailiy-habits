package com.example.dailityhabits.mapper;


import com.example.dailityhabits.DTO.habit.CreateHabitDTO;
import com.example.dailityhabits.DTO.habit.ResponseHabitDTO;
import com.example.dailityhabits.DTO.habit.UpdateHabitDTO;
import com.example.dailityhabits.entity.Habit;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        uses = {FrequencyMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface HabitMapper {

    ResponseHabitDTO toDTO(Habit habit);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "registerCompleted", ignore = true)
    @Mapping(target = "reminders", ignore = true)
    Habit toEntity(CreateHabitDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "registerCompleted", ignore = true)
    @Mapping(target = "reminders", ignore = true)
    Habit UpdateHabitFromDTO(UpdateHabitDTO dto, @MappingTarget Habit entity);
}
