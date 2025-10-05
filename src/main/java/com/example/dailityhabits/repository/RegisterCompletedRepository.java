package com.example.dailityhabits.repository;

import com.example.dailityhabits.entity.RegisterCompleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterCompletedRepository extends JpaRepository<RegisterCompleted, Long> {
}
