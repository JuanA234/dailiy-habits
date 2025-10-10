package com.example.dailityhabits.mapper;

import com.example.dailityhabits.DTO.reminder.CreateReminderDTO;
import com.example.dailityhabits.DTO.reminder.ResponseReminderDTO;
import com.example.dailityhabits.entity.Reminder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReminderMapper {

    Reminder fromResponseReminderDTO(ResponseReminderDTO responseReminderDTO);
    Reminder fromCreateReminderDTO(CreateReminderDTO createReminderDTO);
    ResponseReminderDTO toReminderDTO(Reminder reminder);
}
