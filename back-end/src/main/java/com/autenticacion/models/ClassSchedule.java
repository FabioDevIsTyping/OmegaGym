package com.autenticacion.models;

import java.time.LocalDateTime;

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
    private LocalDateTime startTime;

    @Column(name = "Ora")
    private LocalDateTime endTime;

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

 

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
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
