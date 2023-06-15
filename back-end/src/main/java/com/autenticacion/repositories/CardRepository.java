package com.autenticacion.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.autenticacion.models.Card;
import com.autenticacion.models.User;



public interface CardRepository extends JpaRepository<Card, Integer> {
    Card findByUser(User user);
@Query("SELECT c FROM Card c WHERE c.endDate < :currentDate AND c.isActive = true")
List<Card> findExpiredCards(@Param("currentDate") LocalDate currentDate);





}
