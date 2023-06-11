package com.autenticacion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autenticacion.models.Card;



public interface CardRepository extends JpaRepository<Card, Integer> {



}
