package com.example.dailityhabits.entity;

import com.example.dailityhabits.enumeration.FrequencyType;
import jakarta.persistence.*;
import lombok.*;

import java.time.DayOfWeek;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "frequencies")
public class Frequency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<DayOfWeek> weekDays;

    @Enumerated(EnumType.STRING)
    private FrequencyType type;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "habit_id", referencedColumnName = "id")
    private Habit habit;

    /*
    How many times per Frequency type e.g, every two days
    FrequencyType = Daily
    value = 2
     */
    private int value;
}
