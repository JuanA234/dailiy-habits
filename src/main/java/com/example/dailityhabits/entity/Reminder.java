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

    // Constructor sin el id (útil para crear nuevos recordatorios)
    public Reminder(LocalTime time, boolean active, String message, List<DayOfWeek> days) {
        this.time = time;
        this.active = active;
        this.message = message;
        this.days = days != null ? days : new ArrayList<>();
    }

    /**
     * Activates the reminder
     * @return true if successfully activated, false if already active
     */
    public boolean activate() {
        if (this.active) {
            return false; // Already active
        }
        this.active = true;
        return true;
    }

    /**
     * Deactivates the reminder
     * @return true if successfully deactivated, false if already inactive
     */
    public boolean deactivate() {
        if (!this.active) {
            return false; // Already inactive
        }
        this.active = false;
        return true;
    }

    /**
     * Modifies the reminder time
     * @param newTime the new time for the reminder
     * @return true if time was modified, false if newTime is null
     */
    public boolean modifyTime(LocalTime newTime) {
        if (newTime == null) {
            return false;
        }
        this.time = newTime;
        return true;
    }

    /**
     * Adds a day to the reminder schedule
     * @param day the day to add
     * @return true if day was added, false if it was already in the list or null
     */
    public boolean addDay(DayOfWeek day) {
        if (day == null) {
            return false;
        }

        if (this.days == null) {
            this.days = new ArrayList<>();
        }

        if (this.days.contains(day)) {
            return false; // Day already exists
        }

        return this.days.add(day);
    }

    /**
     * Removes a day from the reminder schedule
     * @param day the day to remove
     * @return true if day was removed, false if it wasn't in the list or null
     */
    public boolean removeDay(DayOfWeek day) {
        if (day == null || this.days == null || this.days.isEmpty()) {
            return false;
        }

        return this.days.remove(day);
    }

    /**
     * Sets multiple days at once
     * @param days list of days for the reminder
     */
    public void setDays(List<DayOfWeek> days) {
        this.days = days != null ? new ArrayList<>(days) : new ArrayList<>();
    }

    /**
     * Checks if the reminder should fire on a specific day
     * @param day the day to check
     * @return true if reminder is active and scheduled for that day
     */
    public boolean shouldFireOnDay(DayOfWeek day) {
        if (!this.active || day == null) {
            return false;
        }

        // If no specific days are set, fire every day
        if (this.days == null || this.days.isEmpty()) {
            return true;
        }

        return this.days.contains(day);
    }

    /**
     * Validates that the reminder configuration is correct
     * @return true if valid, false otherwise
     */
    public boolean validate() {
        // Time must not be null
        if (this.time == null) {
            return false;
        }

        // If days list is not empty, it should contain valid days
        if (this.days != null && !this.days.isEmpty()) {
            for (DayOfWeek day : this.days) {
                if (day == null) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Creates a default message if none is provided
     * @param habitName the name of the habit
     * @return a default reminder message
     */
    public String getOrCreateDefaultMessage(String habitName) {
        if (this.message != null && !this.message.trim().isEmpty()) {
            return this.message;
        }

        if (habitName != null && !habitName.trim().isEmpty()) {
            return "Time to complete: " + habitName;
        }

        return "Time to complete your habit!";
    }

    /**
     * Gets a human-readable representation of the reminder schedule
     * @return string describing when the reminder fires
     */
    public String getScheduleDescription() {
        if (this.days == null || this.days.isEmpty()) {
            return "Every day at " + time.toString();
        }

        StringBuilder schedule = new StringBuilder();
        schedule.append("On ");

        // Sort days for better readability
        List<DayOfWeek> sortedDays = new ArrayList<>(this.days);
        sortedDays.sort((d1, d2) -> Integer.compare(d1.getValue(), d2.getValue()));

        for (int i = 0; i < sortedDays.size(); i++) {
            schedule.append(sortedDays.get(i).toString());
            if (i < sortedDays.size() - 2) {
                schedule.append(", ");
            } else if (i == sortedDays.size() - 2) {
                schedule.append(" and ");
            }
        }

        schedule.append(" at ").append(time.toString());
        return schedule.toString();
    }
}
