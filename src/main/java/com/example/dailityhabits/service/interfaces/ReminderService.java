package com.example.dailityhabits.service.interfaces;

import com.example.dailityhabits.DTO.reminder.CreateReminderDTO;
import com.example.dailityhabits.DTO.reminder.ResponseReminderDTO;

public interface ReminderService {
    ResponseReminderDTO getReminderById(Long id);
    ResponseReminderDTO createReminder(CreateReminderDTO createReminderDTO);
    ResponseReminderDTO updateReminder(ResponseReminderDTO responseReminderDTO);
    void deleteReminderById(Long id);
}
