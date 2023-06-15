package com.autenticacion.models;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.*;

@Entity
@Table(name = "schedules")
public class ClassSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "Id Lezione")
    private GymClass gymClass;

    @Column(name = "Data")
    private LocalDate date;

    @Column(name = "Ora")
    private LocalTime time;

    @Column(name = "Posti disponibili")
    private int availableSlots;

    /**
     * Retrieves the ID of the class schedule.
     *
     * @return The ID of the class schedule.
     */
    public int getId() {
        return id;
    }

    /**
     * Retrieves the gym class associated with the schedule.
     *
     * @return The gym class associated with the schedule.
     */
    public GymClass getGymClass() {
        return gymClass;
    }

    /**
     * Sets the gym class associated with the schedule.
     *
     * @param gymClass The gym class to set.
     */
    public void setGymClass(GymClass gymClass) {
        this.gymClass = gymClass;
    }

    /**
     * Retrieves the date of the class schedule.
     *
     * @return The date of the class schedule.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the date of the class schedule.
     *
     * @param date The date to set for the class schedule.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Retrieves the time of the class schedule.
     *
     * @return The time of the class schedule.
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * Sets the time of the class schedule.
     *
     * @param time The time to set for the class schedule.
     */
    public void setTime(LocalTime time) {
        this.time = time;
    }

    /**
     * Retrieves the number of available slots for the class schedule.
     *
     * @return The number of available slots.
     */
    public int getAvailableSlots() {
        return availableSlots;
    }

    /**
     * Sets the number of available slots for the class schedule.
     *
     * @param availableSlots The number of available slots to set.
     */
    public void setAvailableSlots(int availableSlots) {
        this.availableSlots = availableSlots;
    }


}
