package com.example.dailityhabits.controller;

import com.example.dailityhabits.DTO.reminder.ReminderDTO;
import com.example.dailityhabits.service.interfaces.ReminderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/reminders")
@RequiredArgsConstructor
public class ReminderController {

    private final ReminderService reminderService;

    @GetMapping("/{id}")
    public ResponseEntity<ReminderDTO> getReminder(@PathVariable Long id){
        return new ResponseEntity<>(reminderService.getReminderById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ReminderDTO> createReminder(@RequestBody ReminderDTO reminderDTO){
        return new ResponseEntity<>(reminderService.createReminder(reminderDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ReminderDTO> updateReminder(@RequestBody ReminderDTO reminderDTO){
        return new ResponseEntity<>(reminderService.updateReminder(reminderDTO), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<ReminderDTO> deleteReminder(@PathVariable Long id){
        reminderService.deleteReminderById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
