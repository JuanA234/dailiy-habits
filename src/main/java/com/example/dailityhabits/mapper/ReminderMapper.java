package com.example.dailityhabits.mapper;

import com.example.dailityhabits.DTO.reminder.ReminderDTO;
import com.example.dailityhabits.entity.Reminder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReminderMapper {

    Reminder fromReminderDTO(ReminderDTO reminderDTO);
    ReminderDTO toReminderDTO(Reminder reminder);
}
