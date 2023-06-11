package com.autenticacion.models;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Carte clienti")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "Tipologia abbonamento")
    private Subscription subscription;

    @Column(name = "Data inizio")
    @NotNull(message = "La data di inizio non può essere null!")
    private LocalDateTime startDate;

    @Column(name = "Data scadenza")
    @NotNull(message = "La data di scadenza non può essere null!")
    private LocalDateTime endDate;

    /**
     * Returns the ID of the card.
     *
     * @return the ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the subscription associated with the card.
     *
     * @return the subscription
     */
    public Subscription getSubscription() {
        return subscription;
    }

    /**
     * Sets the subscription for the card.
     *
     * @param subscription the subscription to set
     */
    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    /**
     * Returns the start date of the card's subscription.
     *
     * @return the start date
     */
    public LocalDateTime getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date of the card's subscription.
     *
     * @param startDate the start date to set
     */
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    /**
     * Returns the end date of the card's subscription.
     *
     * @return the end date
     */
    public LocalDateTime getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date of the card's subscription.
     * 
     * @param endDate the end date to set
     * @throws IllegalArgumentException if the endDate is null or before the startDate
     */
    public void setEndDate(LocalDateTime endDate) {
        if (endDate == null) {
            throw new IllegalArgumentException("La data di scadenza non può essere null!");
        }
        
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("La data di scadenza non può essere prima di quella di inizio.");
        }
        
        this.endDate = endDate;
    }
}
