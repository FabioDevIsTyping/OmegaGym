package com.autenticacion.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.autenticacion.models.Card;
import com.autenticacion.repositories.CardRepository;

@RestController
@CrossOrigin
public class CardController {

    @Autowired
    private CardRepository cardRepository;

    /**
     * Retrieves a list of all cards.
     *
     * @return a list of all cards.
     */
    @GetMapping("/getCards")
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    /**
     * Adds a new card.
     *
     * @param card the card to add.
     * @return a confirmation message of the addition.
     */
    @PostMapping("/insertCard")
    public String addCard(@RequestBody Card card) {
        card.setStartDate(LocalDateTime.now());
        card.setEndDate(card.getStartDate().plusMonths(card.getSubscription().getDurata()));
        cardRepository.save(card);
        return "Card added successfully!";
    }

    /**
     * Deletes a card by ID.
     *
     * @param id the ID of the card to delete.
     * @return true if the card was deleted, false otherwise.
     */
    @DeleteMapping("/deleteCard/{id}")
    public boolean deleteCard(@PathVariable int id) {
        if (cardRepository.existsById(id)) {
            cardRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Modifies a card.
     *
     * @param card the card to modify.
     * @return true if the card was modified, false otherwise.
     */
    @PutMapping("/modifyCard")
    public boolean modifyCard(@RequestBody Card card) {
        cardRepository.save(card);
        return true;
    }
}

