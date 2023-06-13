package com.autenticacion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autenticacion.models.Card;
import com.autenticacion.models.User;



public interface CardRepository extends JpaRepository<Card, Integer> {
    Card findByUser(User user);



}
