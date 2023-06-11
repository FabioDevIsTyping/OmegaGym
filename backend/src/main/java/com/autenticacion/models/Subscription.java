package com.autenticacion.models;

import jakarta.persistence.*;

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

    @Column(name = "Durata in mesi")
    private int durata;

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
     * Retrieves the duration of the subscription associated with this card.
     *
     * @return the duration of the subscription in months.
     */
    public int getDurata() {
        return durata;
    }

    /**
     * Sets the duration of the subscription associated with this card.
     *
     * @param durata the duration of the subscription to set in months.
     */
    public void setDurata(int durata) {
        this.durata = durata;
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
