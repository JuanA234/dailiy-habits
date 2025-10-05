package com.example.dailityhabits.repository;

import com.example.dailityhabits.entity.Frequency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FrequencyRepository extends JpaRepository<Frequency, Long> {
}
