package com.example.dailityhabits.controller;

import com.example.dailityhabits.DTO.reminder.CreateReminderDTO;
import com.example.dailityhabits.DTO.reminder.ResponseReminderDTO;
import com.example.dailityhabits.service.interfaces.ReminderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reminders")
@RequiredArgsConstructor
public class ReminderController {

    private final ReminderService reminderService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseReminderDTO> getReminder(@PathVariable Long id){
        return new ResponseEntity<>(reminderService.getReminderById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseReminderDTO> createReminder(@RequestBody CreateReminderDTO createReminderDTO){
        return new ResponseEntity<>(reminderService.createReminder(createReminderDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ResponseReminderDTO> updateReminder(@RequestBody ResponseReminderDTO responseReminderDTO){
        return new ResponseEntity<>(reminderService.updateReminder(responseReminderDTO), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<ResponseReminderDTO> deleteReminder(@PathVariable Long id){
        reminderService.deleteReminderById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
