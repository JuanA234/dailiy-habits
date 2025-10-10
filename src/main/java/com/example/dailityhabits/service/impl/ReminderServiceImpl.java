package com.example.dailityhabits.service.impl;

import com.example.dailityhabits.DTO.reminder.CreateReminderDTO;
import com.example.dailityhabits.DTO.reminder.ResponseReminderDTO;
import com.example.dailityhabits.exception.notFound.ReminderNotFoundException;
import com.example.dailityhabits.mapper.ReminderMapper;
import com.example.dailityhabits.repository.ReminderRepository;
import com.example.dailityhabits.service.interfaces.ReminderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReminderServiceImpl implements ReminderService {

    private final ReminderRepository reminderRepository;
    private final ReminderMapper reminderMapper;

    @Override
    public ResponseReminderDTO getReminderById(Long id) {
        if (id == null || id <= 0) {
            throw new ReminderNotFoundException("Invalid ID");
        }
        return reminderRepository.findById(id)
                .map(reminderMapper::toReminderDTO)
                .orElseThrow(() -> new ReminderNotFoundException("Reminder not found"));
    }

    @Override
    public ResponseReminderDTO createReminder(CreateReminderDTO createReminderDTO) {
        return reminderMapper.toReminderDTO(
                reminderRepository.save(reminderMapper.fromCreateReminderDTO(createReminderDTO)));
    }

    @Override
    public ResponseReminderDTO updateReminder(ResponseReminderDTO responseReminderDTO) {
        if (reminderRepository.existsById(responseReminderDTO.id())){
            return reminderMapper.toReminderDTO(
                    reminderRepository.save(reminderMapper.fromResponseReminderDTO(responseReminderDTO)));
        }else{
            throw new ReminderNotFoundException("Reminder not found");
        }
    }

    @Override
    public void deleteReminderById(Long id) {
        if (id == null || id <= 0) {
            throw new ReminderNotFoundException("Invalid ID");
        }
        if (reminderRepository.existsById(id)){
            reminderRepository.deleteById(id);
        }
    }
}
