package com.autenticacion.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

/**
 * Represents a gym class.
 */
@Entity
@Table(name = "Lezioni")
public class GymClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Nome lezione")
    @NotNull(message = "Inserire il nome della lezione.")
    private String name;

    @Column(name = "Istruttore")
    private String instructorName;

    @Column(name = "Durata in minuti")
    @NotNull(message = "La durata della lezione non può essere nulla.")
    private int duration;

    @Column(name = "Posti disponibili")
    private int capacity;

    /**
     * Retrieves the ID of the gym class.
     *
     * @return The ID of the gym class.
     */
    public int getId() {
        return id;
    }

    /**
     * Retrieves the name of the gym class.
     *
     * @return The name of the gym class.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the gym class.
     *
     * @param name The name of the gym class to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the name of the instructor for the gym class.
     *
     * @return The name of the instructor.
     */
    public String getInstructorName() {
        return instructorName;
    }

    /**
     * Sets the name of the instructor for the gym class.
     *
     * @param instructorName The name of the instructor to set.
     */
    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    /**
     * Retrieves the duration of the gym class in minutes.
     *
     * @return The duration of the gym class in minutes.
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets the duration of the gym class in minutes.
     *
     * @param duration The duration of the gym class to set in minutes.
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Retrieves the capacity or the number of available spots for the gym class.
     *
     * @return The capacity of the gym class.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Sets the capacity or the number of available spots for the gym class.
     *
     * @param capacity The capacity of the gym class to set.
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
