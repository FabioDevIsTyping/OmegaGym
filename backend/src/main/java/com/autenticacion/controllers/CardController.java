package com.autenticacion.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.autenticacion.models.Card;
import com.autenticacion.repositories.CardRepository;
import com.autenticacion.repositories.UserRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CardController {

    @Autowired
    private CardRepository cardRepository;
    @Autowired 
    private UserRepository userRepository;

    /**
     * Retrieves a list of all cards.
     *
     * @return a list of all cards.
     */
    @GetMapping("/getCards")
    @PreAuthorize("hasAuthority('ADMIN')")
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
    @PreAuthorize("hasAuthority('ADMIN')")
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
    @PreAuthorize("hasAuthority('ADMIN')")
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
    @PreAuthorize("hasAuthority('ADMIN')")
    public boolean modifyCard(@RequestBody Card card) {
        cardRepository.save(card);
        return true;
    }

    @GetMapping("/getCard/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Card getCard(@PathVariable long id){
            
        return cardRepository.findByUser(userRepository.findById(id).get());
    }
}

