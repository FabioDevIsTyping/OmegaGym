package com.autenticacion.models;

import java.time.LocalDate;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    private LocalDate startDate;

    @Column(name = "Data scadenza")
    @NotNull(message = "La data di scadenza non può essere null!")
    private LocalDate endDate;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @OnDelete(action= OnDeleteAction.CASCADE)
    private User user;

    @Column(name = "Validità carta")
    private Boolean isActive;

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
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date of the card's subscription.
     *
     * @param startDate the start date to set
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Returns the end date of the card's subscription.
     *
     * @return the end date
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date of the card's subscription.
     * 
     * @param endDate the end date to set
     * @throws IllegalArgumentException if the endDate is null or before the
     *                                  startDate
     */
    public void setEndDate(LocalDate endDate) {
        if (endDate == null) {
            throw new IllegalArgumentException("La data di scadenza non può essere null!");
        }

        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("La data di scadenza non può essere prima di quella di inizio.");
        }

        this.endDate = endDate;
    }

    /**
     * Returns the user associated with the card.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user for the card.
     *
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Returns the status of the card.
     *
     * @return true if the card is active, false otherwise
     */
    public Boolean isActive() {
        return isActive;
    }

    /**
     * Sets the status of the card.
     *
     * @param isActive the status to set
     */
    public void setActive(Boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * Constructs a Card object with the specified start date, end date, and
     * status.
     *
     * @param startDate the start date of the card's subscription
     * @param endDate   the end date of the card's subscription
     * @param isActive  the status of the card
     */
    public Card(LocalDate startDate, LocalDate endDate, boolean isActive) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = isActive;
    }

    /**
     * Constructs a Card object with default values.
     */
    public Card() {
        // Default constructor
    }
}
