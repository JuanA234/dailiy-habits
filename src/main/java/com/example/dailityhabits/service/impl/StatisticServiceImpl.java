package com.example.dailityhabits.service.impl;

import com.example.dailityhabits.DTO.statistic.StatisticReportDTO;
import com.example.dailityhabits.DTO.statistic.StatisticRequestDTO;
import com.example.dailityhabits.DTO.statistic.StatisticResponseDTO;
import com.example.dailityhabits.entity.Frequency;
import com.example.dailityhabits.entity.Habit;
import com.example.dailityhabits.entity.RegisterCompleted;
import com.example.dailityhabits.entity.Statistic;
import com.example.dailityhabits.exception.notFound.HabitNotFoundException;
import com.example.dailityhabits.exception.notFound.StatisticNotFoundException;
import com.example.dailityhabits.mapper.StatisticMapper;
import com.example.dailityhabits.repository.HabitRepository;
import com.example.dailityhabits.repository.RegisterCompletedRepository;
import com.example.dailityhabits.repository.StatisticRepository;
import com.example.dailityhabits.service.interfaces.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {

    private final StatisticRepository statisticRepository;
    private final StatisticMapper statisticMapper;
    private final RegisterCompletedRepository registerCompletedRepository;
    private final HabitRepository habitRepository;


    @Override
    public StatisticResponseDTO getStatisticById(Long id) {
        return statisticRepository.findById(id).map(statisticMapper::toDTO).orElseThrow(()->new StatisticNotFoundException("Statistic not found with id: " + id));
    }

    @Override
    public StatisticResponseDTO createStatistic(Long habitId) {
        Habit habit = habitRepository.findById(habitId).orElseThrow(()->new HabitNotFoundException("Habit not found with id: " + habitId));

        Statistic statistic = Statistic.builder()
                .currentStreak(0)
                .maxStreak(0)
                .percentageCompleted(0.0f)
                .totalCompleted(0)
                .startTime(LocalDateTime.now())
                .habit(habit)
                .build();
        return statisticMapper.toDTO(statisticRepository.save(statistic));
    }

    @Override
    public StatisticResponseDTO updateStatistic(Long id, StatisticRequestDTO statisticRequestDTO) {
        Statistic statistic = statisticRepository.findById(id).orElseThrow(()->new StatisticNotFoundException("Statistic not found with id: " + id));
        statistic.setCurrentStreak(statisticRequestDTO.currentStreak());
        statistic.setMaxStreak(statisticRequestDTO.maxStreak());
        statistic.setPercentageCompleted(statisticRequestDTO.percentageCompleted());
        statistic.setTotalCompleted(statisticRequestDTO.totalCompleted());
        return statisticMapper.toDTO(statisticRepository.save(statistic));
    }

    @Override
    public void deleteStatistic(Long id) {
        if(!statisticRepository.existsById(id)) {
            throw new StatisticNotFoundException("Statistic not found with id: " + id);
        }
        statisticRepository.deleteById(id);
    }

    @Override
    public void calculateStreak(Long id) {
        Statistic statistic = statisticRepository.findById(id).orElseThrow(()->new StatisticNotFoundException("Statistic not found with id: " + id));

        Optional<RegisterCompleted> lastRecord = registerCompletedRepository.findTopByHabitIdOrderByDateDesc(statistic.getHabit().getId());

        LocalDate yesterday = LocalDate.now().minusDays(1);

        if (lastRecord.isPresent() && lastRecord.get().getDate().equals(yesterday)) {
            statistic.setCurrentStreak(statistic.getCurrentStreak() + 1);

            if (statistic.getCurrentStreak() > statistic.getMaxStreak()) {
                statistic.setMaxStreak(statistic.getCurrentStreak());
            }
        } else {
            statistic.setCurrentStreak(1);
        }

        statisticRepository.save(statistic);
    }

    @Override
    public void calculatePercentage(Long id) {
        Statistic statistic = statisticRepository.findById(id)
                .orElseThrow(() -> new StatisticNotFoundException("Statistic not found with id: " + id));

        Habit habit = statistic.getHabit();
        Frequency frequency = habit.getFrequency();

        LocalDate startDate = statistic.getStartTime().toLocalDate();
        LocalDate today = LocalDate.now();

        // Calcular días transcurridos
        long daysElapsed = ChronoUnit.DAYS.between(startDate, today) + 1;

        if (daysElapsed <= 0) {
            statistic.setPercentageCompleted(0.0f);
            statisticRepository.save(statistic);
            return;
        }

        // Calcular días esperados según el tipo de frecuencia
        int expectedDays = calculateExpectedDays(frequency, startDate, today);

        // Obtener días completados
        int completedDays = statistic.getTotalCompleted();

        // Calcular porcentaje (limitar a 100%)
        float percentage = expectedDays > 0
                ? Math.min((float) completedDays / expectedDays * 100, 100.0f)
                : 0.0f;

        statistic.setPercentageCompleted(percentage);
        statisticRepository.save(statistic);
    }

    @Override
    public StatisticReportDTO generateReport(Long id) {
        // Actualizar estadísticas antes de generar el reporte
        calculateStreak(id);
        calculatePercentage(id);

        // Una sola consulta después de actualizar
        Statistic statistic = statisticRepository.findById(id)
                .orElseThrow(() -> new StatisticNotFoundException("Statistic not found with id: " + id));

        Habit habit = statistic.getHabit();

        // Calcular métricas adicionales
        long daysActive = ChronoUnit.DAYS.between(
                statistic.getStartTime().toLocalDate(),
                LocalDate.now()
        ) + 1;

        float weeklyAverage = calculateWeeklyAverage(statistic, daysActive);

        // Obtener últimos registros
        List<LocalDate> lastCompletions = registerCompletedRepository
                .findTop7ByHabitIdOrderByDateDesc(habit.getId())
                .stream()
                .map(RegisterCompleted::getDate)
                .toList();

        // Construir y retornar el reporte
        return StatisticReportDTO.builder()
                .habitName(habit.getName())
                .category(habit.getCategory())
                .currentStreak(statistic.getCurrentStreak())
                .maxStreak(statistic.getMaxStreak())
                .percentageCompleted(statistic.getPercentageCompleted())
                .totalCompleted(statistic.getTotalCompleted())
                .startDate(statistic.getStartTime())
                .reportGeneratedAt(LocalDateTime.now())
                .daysActive(daysActive)
                .weeklyAverage(weeklyAverage)
                .lastCompletions(lastCompletions)
                .build();
    }

    private int calculateExpectedDays(Frequency frequency, LocalDate startDate, LocalDate endDate) {
        if (!frequency.checkFrequency()) {
            return 0;
        }

        switch (frequency.getType()) {
            case DAILY:
                // Si tiene días específicos de la semana
                if (frequency.getWeekDays() != null && !frequency.getWeekDays().isEmpty()) {
                    return countSpecificDaysInRange(frequency.getWeekDays(), startDate, endDate);
                }
                // Si es cada N días
                long totalDays = ChronoUnit.DAYS.between(startDate, endDate) + 1;
                return (int) (totalDays / frequency.getValue());

            case WEEKLY:
                // Contar cuántas veces aparecen los días específicos
                return countSpecificDaysInRange(frequency.getWeekDays(), startDate, endDate);

            case MONTHLY:
                long months = ChronoUnit.MONTHS.between(startDate, endDate);
                return (int) (months / frequency.getValue()) + 1;

            case ANNUAL:
                long years = ChronoUnit.YEARS.between(startDate, endDate);
                return (int) (years / frequency.getValue()) + 1;

            case CUSTOM:
                return countSpecificDaysInRange(frequency.getWeekDays(), startDate, endDate);

            default:
                return 0;
        }
    }

    private int countSpecificDaysInRange(List<DayOfWeek> weekDays, LocalDate startDate, LocalDate endDate) {
        int count = 0;
        LocalDate currentDate = startDate;

        while (!currentDate.isAfter(endDate)) {
            if (weekDays.contains(currentDate.getDayOfWeek())) {
                count++;
            }
            currentDate = currentDate.plusDays(1);
        }

        return count;
    }

    private float calculateWeeklyAverage(Statistic statistic, long daysActive) {
        if (daysActive < 7) {
            return statistic.getTotalCompleted();
        }

        float weeksActive = daysActive / 7.0f;
        return statistic.getTotalCompleted() / weeksActive;
    }
}
