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

    public int getId() {
        return id;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }



    
}
