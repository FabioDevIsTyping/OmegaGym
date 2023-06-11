package com.autenticacion.models.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.autenticacion.models.Card;
import com.autenticacion.models.repositories.CardRepository;

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
        return (List<Card>) cardRepository.findAll();
    }

    /**
     * Adds a new subscription.
     *
     * @param subscription the subscription to add.
     * @return a confirmation message of the addition.
     */
    @PostMapping("/insertCard")
    public String addSubscription(@RequestBody Card card) {
        cardRepository.save(card);
        return "Card added successfully!";
    }

    @DeleteMapping("/deleteCard/{id}")
    public boolean deleteCard(@PathVariable int id)
    {
        if(cardRepository.existsById(id))
        {
            cardRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @PutMapping("/modifyCard")
    public boolean modifyCard(@RequestBody Card card)
    {
        cardRepository.save(card);
        return true;

    }


    
}
