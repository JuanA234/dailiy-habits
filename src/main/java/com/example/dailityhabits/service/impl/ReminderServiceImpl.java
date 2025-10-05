package com.example.dailityhabits.service.impl;

import com.example.dailityhabits.DTO.reminder.ReminderDTO;
import com.example.dailityhabits.mapper.ReminderMapper;
import com.example.dailityhabits.repository.HabitRepository;
import com.example.dailityhabits.repository.ReminderRepository;
import com.example.dailityhabits.service.interfaces.ReminderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ReminderServiceImpl implements ReminderService {

    private final ReminderRepository reminderRepository;
    private final ReminderMapper reminderMapper;

    @Override
    public ReminderDTO getReminderById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid ID");
        }
        return reminderRepository.findById(id)
                .map(reminderMapper::toReminderDTO)
                .orElseThrow(() -> new NoSuchElementException("Reminder not found"));
    }

    @Override
    public ReminderDTO createReminder(ReminderDTO reminderDTO) {
        return reminderMapper.toReminderDTO(
                reminderRepository.save(reminderMapper.fromReminderDTO(reminderDTO)));
    }

    @Override
    public ReminderDTO updateReminder(ReminderDTO reminderDTO) {
        if (reminderDTO.id() == null || reminderDTO.id() <= 0) {
            throw new IllegalArgumentException("Invalid ID");
        }
        return reminderMapper.toReminderDTO(
                reminderRepository.save(reminderMapper.fromReminderDTO(reminderDTO)));
    }

    @Override
    public void deleteReminderById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid ID");
        }
        reminderRepository.deleteById(id);
    }
}
