package com.example.dailityhabits.service.interfaces;

import com.example.dailityhabits.DTO.reminder.ReminderDTO;

public interface ReminderService {

    ReminderDTO getReminderById(Long id);
    ReminderDTO createReminder(ReminderDTO reminderDTO);
    ReminderDTO updateReminder(ReminderDTO reminderDTO);
    void deleteReminderById(Long id);
}
