package com.example.dailityhabits.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
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
    @OneToOne(mappedBy = "habit")
    private Frequency frequency;

    @OneToOne(mappedBy = "habit")
    private Statistic statistic;

    @OneToMany(mappedBy = "habit")
    private Set<RegisterCompleted> registerCompleted;

    @OneToMany(mappedBy = "habit")
    private Set<Reminder> reminders;


}
