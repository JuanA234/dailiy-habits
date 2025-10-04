package com.example.dailityhabits.entity;

import com.example.dailityhabits.enumeration.FrequencyType;
import com.example.dailityhabits.enumeration.WeekDay;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity (name = "frequencies")
public class Frequency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private List<WeekDay> weekDays;
    private FrequencyType type;
    /*
    How many times per Frequency type e.g, every two days
    FrequencyType = Daily
    value = 2
     */
    private int value;

}
