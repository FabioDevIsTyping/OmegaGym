package com.autenticacion.models;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "Carte clienti")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "Tipologia abbonamento")
    private Subscription subscription;

    @Column(name = "Data inizio")
    private LocalDateTime startDate;

    @Column(name = "Data fine")
    private LocalDateTime endDate;

    /**
     * Returns the ID of the object.
     *
     * @return the ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the subscription associated with the object.
     *
     * @return the subscription
     */
    public Subscription getSubscription() {
        return subscription;
    }

    /**
     * Sets the subscription for the object.
     *
     * @param subscription the subscription to set
     */
    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    /**
     * Returns the start date of the subscription.
     *
     * @return the start date
     */
    public LocalDateTime getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date of the subscription.
     *
     * @param startDate the start date to set
     */
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    /**
     * Returns the end date of the subscription.
     *
     * @return the end date
     */
    public LocalDateTime getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date of the subscription.
     *
     * @param endDate the end date to set
     */
    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

}
