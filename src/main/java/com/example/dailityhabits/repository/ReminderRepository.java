package com.example.dailityhabits.repository;

import com.example.dailityhabits.entity.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReminderRepository extends JpaRepository<Reminder,Long> {
}
