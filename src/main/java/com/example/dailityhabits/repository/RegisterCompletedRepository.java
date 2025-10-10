package com.example.dailityhabits.repository;

import com.example.dailityhabits.entity.RegisterCompleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegisterCompletedRepository extends JpaRepository<RegisterCompleted, Long> {
    Optional<RegisterCompleted> findTopByHabitIdOrderByDateDesc(Long id);
    List<RegisterCompleted> findTop7ByHabitIdOrderByDateDesc(Long id);

    List<RegisterCompleted> findByHabit_Id(Long habitId);
}
