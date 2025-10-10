package com.example.dailityhabits.service.impl;

import com.example.dailityhabits.DTO.habit.CreateHabitDTO;
import com.example.dailityhabits.DTO.habit.ResponseHabitDTO;
import com.example.dailityhabits.DTO.habit.UpdateHabitDTO;
import com.example.dailityhabits.DTO.registerCompleted.ResponseRegisterCompletedDTO;
import com.example.dailityhabits.entity.Frequency;
import com.example.dailityhabits.entity.Habit;
import com.example.dailityhabits.entity.Reminder;
import com.example.dailityhabits.entity.Statistic;
import com.example.dailityhabits.exception.notFound.FrecuencyNotFoundException;
import com.example.dailityhabits.exception.notFound.HabitNotFoundException;
import com.example.dailityhabits.exception.notFound.ReminderNotFoundException;
import com.example.dailityhabits.exception.notFound.StatisticNotFoundException;
import com.example.dailityhabits.mapper.HabitMapper;
import com.example.dailityhabits.repository.FrequencyRepository;
import com.example.dailityhabits.repository.HabitRepository;
import com.example.dailityhabits.repository.ReminderRepository;
import com.example.dailityhabits.repository.StatisticRepository;
import com.example.dailityhabits.service.interfaces.HabitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HabitServiceImpl implements HabitService {

    private final HabitRepository habitRepository;
    private final HabitMapper habitMapper;


    @Override
    public void calculateProgress() {

    }

    @Override
    public void deleteHabit(Long id) {
        Habit habit = habitRepository.findById(id)
                .orElseThrow(() -> new HabitNotFoundException("Habit not found"));
        habitRepository.delete(habit);
    }

    @Override
    public ResponseHabitDTO createHabit(CreateHabitDTO request) {
        Habit habit = habitMapper.toEntity(request);
        habit.setStartDate(LocalDateTime.now());
        return habitMapper.toDTO(habitRepository.save(habit));
    }

    @Override
    public List<ResponseHabitDTO> listHabits() {

        return habitRepository.findAll()
                .stream().map(habitMapper::toDTO)
                .collect(Collectors.toList());

    }

    @Override
    public ResponseHabitDTO findHabitById(Long id) {

        return habitRepository.findById(id)
                .map(habitMapper::toDTO)
                .orElseThrow(() -> new HabitNotFoundException("Habit not found"));
    }

    @Override
    public ResponseHabitDTO UpdateHabit(Long id, UpdateHabitDTO request) {

        Habit foundHabit = habitRepository.findById(id)
                .orElseThrow(() -> new HabitNotFoundException("Habit not found"));

        habitMapper.UpdateHabitFromDTO(request, foundHabit);

        return habitMapper.toDTO(habitRepository.save(foundHabit));
    }

    @Override
    public ResponseRegisterCompletedDTO check(String notas) {

        return null;
    }
}
