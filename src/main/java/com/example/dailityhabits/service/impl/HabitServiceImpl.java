package com.example.dailityhabits.service.impl;

import com.example.dailityhabits.DTO.habit.CreateHabitDTO;
import com.example.dailityhabits.DTO.habit.ResponseHabitDTO;
import com.example.dailityhabits.DTO.habit.UpdateHabitDTO;
import com.example.dailityhabits.DTO.registerCompleted.CreateRegisterCompletedDTO;
import com.example.dailityhabits.DTO.registerCompleted.ResponseRegisterCompletedDTO;
import com.example.dailityhabits.entity.*;
import com.example.dailityhabits.exception.notFound.HabitNotFoundException;
import com.example.dailityhabits.exception.notFound.UserNotFoundException;
import com.example.dailityhabits.mapper.HabitMapper;
import com.example.dailityhabits.mapper.RegisterCompletedMapper;
import com.example.dailityhabits.repository.*;
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
    private final RegisterCompletedRepository registerCompletedRepository;
    private final RegisterCompletedMapper registerCompletedMapper;
    private final StatisticRepository statisticRepository;
    private final UserRepository userRepository;


    @Override
    public void calculateProgress() {

    }

    @Override
    public void deleteHabit(Long id) {
        Statistic statistic = statisticRepository.findByHabit_Id(id);
        List<RegisterCompleted> registerCompleted = registerCompletedRepository.findByHabit_Id(id);

        if(statistic != null) {
            statisticRepository.delete(statistic);
        }

        if(!registerCompleted.isEmpty()) {
            registerCompletedRepository.deleteAll(registerCompleted);
        }

        Habit habit = habitRepository.findById(id)
                .orElseThrow(() -> new HabitNotFoundException("Habit not found"));
        habitRepository.delete(habit);
    }

    @Override
    public ResponseHabitDTO createHabit(CreateHabitDTO request, Long userId) {
        Habit habit = habitMapper.toEntity(request);
        habit.setStartDate(LocalDateTime.now());

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        habit.setUser(user);

        if (habit.getFrequency() != null) {
            habit.getFrequency().setHabit(habit);
        }
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
    public ResponseHabitDTO updateHabit(Long id, UpdateHabitDTO request, Long userId) {

        Habit foundHabit = habitRepository.findById(id)
                .orElseThrow(() -> new HabitNotFoundException("Habit not found"));

        if (foundHabit.getUser().getId() !=  userId ) {
            throw new ForbiddenException("You don't have permission to update this habit");
        }

        habitMapper.UpdateHabitFromDTO(request, foundHabit);
        if (foundHabit.getFrequency() != null) {
            foundHabit.getFrequency().setHabit(foundHabit);
        }

        return habitMapper.toDTO(habitRepository.save(foundHabit));
    }

    @Override
    public ResponseRegisterCompletedDTO checkHabit(Long id, CreateRegisterCompletedDTO createRegisterCompletedDTO) {
        Habit foundHabit = habitRepository.findById(id)
                .orElseThrow(() -> new HabitNotFoundException("Habit not found"));

        RegisterCompleted registerCompleted = RegisterCompleted.builder()
                .date(LocalDate.now())
                .time(LocalTime.now())
                .notas(createRegisterCompletedDTO.notes())
                .habit(foundHabit)
                .build();

        foundHabit.getRegisterCompleted().add(registerCompleted);
        habitRepository.save(foundHabit);

        return registerCompletedMapper.toDTO(registerCompletedRepository.save(registerCompleted));
    }
}
