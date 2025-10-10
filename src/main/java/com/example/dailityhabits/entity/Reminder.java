package com.example.dailityhabits.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reminders")
public class Reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalTime time;
    private boolean active;
    private String message;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<DayOfWeek> days;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "habit_id")
    private Habit habit;

    public Reminder(LocalTime time, boolean active, String message, List<DayOfWeek> days) {
        this.time = time;
        this.active = active;
        this.message = message;
        this.days = days != null ? days : new ArrayList<>();
    }
}
