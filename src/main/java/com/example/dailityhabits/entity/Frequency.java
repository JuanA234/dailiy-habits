package com.example.dailityhabits.entity;

import com.example.dailityhabits.enumeration.FrequencyType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
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

    /**
     * Validates that the frequency configuration is coherent
     * @return true if the frequency is valid, false otherwise
     */
    public boolean checkFrequency() {
        if (value <= 0) {
            return false;
        }

        switch (type) {
            case DAILY:
                return true;

            case WEEKLY:
                if (weekDays == null || weekDays.isEmpty()) {
                    return false;
                }
                return value <= 52;

            case MONTHLY:
                return value <= 12;

            case ANNUAL:
                return value <= 10;

            case CUSTOM:
                return weekDays != null && !weekDays.isEmpty();

            default:
                return false;
        }
    }

    /**
     * Calculates the next date when the habit should be completed
     * @param currentDate the date from which to calculate
     * @return the next valid date or null if there's an error
     */
    public LocalDate getNextDate(LocalDate currentDate) {
        // STEP 1: Validate that the frequency is correct before calculating
        if (!checkFrequency()) {
            return null; // If not valid, we can't calculate
        }

        // STEP 2: Decide which logic to apply based on frequency type
        switch (type) {

            // ===== CASE: DAILY FREQUENCY =====
            case DAILY:
                // Subcase A: Every day (without specific days)
                if (weekDays == null || weekDays.isEmpty()) {
                    // Example: take vitamins every day
                    // If value = 1 → tomorrow
                    // If value = 2 → day after tomorrow
                    return currentDate.plusDays(value);
                }
                // Subcase B: Specific days of the week
                else {
                    // Example: run on Monday, Wednesday, Friday
                    // value indicates every how many of those days
                    return getNextDayInList(currentDate, weekDays, value);
                }

                // ===== CASE: WEEKLY FREQUENCY =====
            case WEEKLY:
                // Find the next day of the week that's in the list
                // Example: gym Monday, Wednesday, Friday
                // If value = 1 → every week
                // If value = 2 → every 2 weeks
                return getNextDayInList(currentDate, weekDays, value);

            // ===== CASE: MONTHLY FREQUENCY =====
            case MONTHLY:
                // Subcase A: Without specific days
                if (weekDays == null || weekDays.isEmpty()) {
                    // Same day but in the following month
                    // Example: Today is October 15, value = 1
                    // Result: November 15
                    // If value = 2 → December 15
                    return currentDate.plusMonths(value);
                }
                // Subcase B: With specific day
                else {
                    // Example: first Monday of each month
                    LocalDate nextMonth = currentDate.plusMonths(value);
                    return getFirstDayOfMonth(nextMonth, weekDays.get(0));
                }

                // ===== CASE: ANNUAL FREQUENCY =====
            case ANNUAL:
                // Same day but in the following year
                // Example: birthday, anniversary
                // Today: October 4, 2025, value = 1
                // Result: October 4, 2026
                return currentDate.plusYears(value);

            // ===== CASE: CUSTOM =====
            case CUSTOM:
                // Similar to weekly, but with custom logic
                return getNextDayInList(currentDate, weekDays, value);

            default:
                return null; // Unrecognized type
        }
    }

    /**
     * AUXILIARY METHOD 1: Get next day in list
     * This method searches for the next day that matches the specified days
     * considering the configured interval
     */
    private LocalDate getNextDayInList(LocalDate startDate,
                                       List<DayOfWeek> daysList,
                                       int interval) {
        // Start searching from tomorrow
        LocalDate temporalDate = startDate.plusDays(1);
        int daysFound = 0;
        int maxIterations = 365; // Protection: don't search more than 1 year
        int iterations = 0;

        // Iterate day by day until finding the next valid one
        while (iterations < maxIterations) {
            // Get what day of the week temporalDate is
            DayOfWeek currentDayOfWeek = temporalDate.getDayOfWeek();

            // Is this day in our list of valid days?
            if (daysList.contains(currentDayOfWeek)) {
                daysFound++;

                // CASE A: Interval = 1 (every week/day)
                // Return the first matching day
                if (interval == 1) {
                    return temporalDate;
                }

                // CASE B: Interval > 1 for WEEKLY frequency
                // Example: every 2 weeks on Mondays
                // We must verify that enough weeks have passed
                if (type == FrequencyType.WEEKLY &&
                        hasIntervalPassed(startDate, temporalDate, interval)) {
                    return temporalDate;
                }

                // CASE C: Interval > 1 for DAILY frequency with specific days
                // Example: every 2 times Monday appears
                if (type == FrequencyType.DAILY && daysFound >= interval) {
                    return temporalDate;
                }
            }

            // Move to next day
            temporalDate = temporalDate.plusDays(1);
            iterations++;
        }

        return null; // Not found (shouldn't happen in normal use)
    }

    /**
     * AUXILIARY METHOD 2: Verify if interval has passed
     * Calculates if enough weeks have elapsed since the start date
     */
    private boolean hasIntervalPassed(LocalDate startDate,
                                      LocalDate currentDate,
                                      int interval) {
        // Calculate how many complete weeks have passed
        long weeksElapsed = ChronoUnit.WEEKS.between(startDate, currentDate);

        // Example:
        // startDate: October 1 (Monday)
        // currentDate: October 15 (Monday)
        // weeksElapsed = 2
        // If interval = 2, returns true (2 weeks have passed)

        return weeksElapsed >= interval;
    }

    /**
     * AUXILIARY METHOD 3: Get first specific day of the month
     * Searches for the first occurrence of a day of the week in a given month
     */
    private LocalDate getFirstDayOfMonth(LocalDate date, DayOfWeek targetDay) {
        // Start from day 1 of the month
        LocalDate firstDayOfMonth = date.withDayOfMonth(1);

        // Iterate day by day until finding the target day
        while (firstDayOfMonth.getMonth() == date.getMonth()) {
            // Does this day match the day we're looking for?
            if (firstDayOfMonth.getDayOfWeek() == targetDay) {
                return firstDayOfMonth;
            }
            firstDayOfMonth = firstDayOfMonth.plusDays(1);
        }

        // If we exit the month without finding the day, return null
        // (this could happen in extreme cases)
        return null;
    }
}
