package com.autenticacion.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

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
    @NotNull(message = "La tipologia di abbonamento non può essere null!")
    private String name;

    @Column(name = "Durata in mesi")
    @Min(value = 1, message = "La durata deve essere maggiore o uguale ad un mese! ")
    @Max(value = 24, message = "La durata massima prevista per un abbonamento è di 24 mesi!")
    private int duration;

    @Column(name = "Prezzo")
    private double price;

    @Column(name = "Descrizione")
    @NotNull(message = "La descrizione dell'abbonamento non può essere null!")
    private String descrizione;


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

    /**
     * Retrieves the description of the subscription.
     *
     * @return the description of the subscription.
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Sets the description of the subscription.
     *
     * @param descrizione the description of the subscription to set.
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }



    

}
