package com.autenticacion.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Represents a gym subscription.
 */
@Entity
@Table(name = "gym_subscription")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Tipologia Abbonamento")
    private String name;

    @Column(name = "Data inizio")
    private LocalDateTime startDate;

    @Column(name = "Data fine")
    private LocalDateTime endDate;

    @Column(name = "Prezzo")
    private double price;

    /**
     * Retrieves the ID of the subscription.
     *
     * @return The subscription ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the subscription.
     *
     * @param id The subscription ID to set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the subscription.
     *
     * @return The subscription name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the subscription.
     *
     * @param name The subscription name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the start date of the subscription.
     *
     * @return The start date of the subscription.
     */
    public LocalDateTime getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date of the subscription.
     *
     * @param startDate The start date of the subscription to set.
     */
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    /**
     * Retrieves the end date of the subscription.
     *
     * @return The end date of the subscription.
     */
    public LocalDateTime getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date of the subscription.
     *
     * @param endDate The end date of the subscription to set.
     */
    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    /**
     * Retrieves the price of the subscription.
     *
     * @return The subscription price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the subscription.
     *
     * @param price The subscription price to set.
     */
    public void setPrice(double price) {
        this.price = price;
    }
}


