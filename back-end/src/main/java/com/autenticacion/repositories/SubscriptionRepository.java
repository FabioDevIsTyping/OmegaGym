package com.autenticacion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autenticacion.models.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {



}