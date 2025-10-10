package com.example.dailityhabits.repository;

import com.example.dailityhabits.entity.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticRepository extends JpaRepository<Statistic, Long> {
    Statistic findByHabit_Id(Long habitId);
}
