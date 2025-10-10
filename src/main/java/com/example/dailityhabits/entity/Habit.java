package com.example.dailityhabits.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "habits")
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private String category;

    private LocalDateTime startDate;
    @OneToOne(mappedBy = "habit", cascade = CascadeType.ALL,  orphanRemoval = true)
    private Frequency frequency;

    @OneToMany(mappedBy = "habit", cascade = CascadeType.ALL,  orphanRemoval = true)
    private Set<RegisterCompleted> registerCompleted = new HashSet<>();

    @OneToMany(mappedBy = "habit", cascade = CascadeType.ALL,  orphanRemoval = true)
    private Set<Reminder> reminders;
}
