package com.autenticacion.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.autenticacion.models.Card;
import com.autenticacion.models.Subscription;
import com.autenticacion.repositories.CardRepository;
import com.autenticacion.repositories.SubscriptionRepository;

@RestController
@CrossOrigin
public class SubscriptionController {

    @Autowired
    private SubscriptionRepository subscriptionRepository;
    
    @Autowired
    private CardRepository cardRepository;

    /**
     * Retrieves a list of all subscriptions.
     *
     * @return a list of all subscriptions.
     */
    @GetMapping("/getSubscriptions")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    /**
     * Adds a new subscription.
     *
     * @param subscription the subscription to add.
     * @return a confirmation message of the addition.
     */
    @PostMapping("/insertSubscription")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addSubscription(@RequestBody Subscription subscription) {
        subscriptionRepository.save(subscription);
        return "Subscription added successfully!";
    }

    /**
     * Deletes a subscription given the ID.
     *
     * @param id the ID of the subscription to delete.
     * @return true if the subscription was deleted successfully, false otherwise.
     */
    @DeleteMapping("/deleteSubscription/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public boolean deleteSubscription(@PathVariable int id) {
        if (subscriptionRepository.existsById(id)) {
            List<Card> cardList = cardRepository.findBySubscription(subscriptionRepository.findById(id).get());
            cardList.forEach(card -> {
                cardRepository.deleteById(card.getId());
            });

            subscriptionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Modifies an existing subscription.
     *
     * @param subscription the subscription to modify.
     * @return true if the subscription was modified successfully, false otherwise.
     */
    @PutMapping("/modifySubscriptions")
    @PreAuthorize("hasAuthority('ADMIN')") 
    public boolean modifySubscription(@RequestBody Subscription subscription) {
        subscriptionRepository.save(subscription);
        return true;
    }

    /**
     * Retrieves a single subscription based on the ID.
     *
     * @param id the ID of the subscription to retrieve.
     * @return the subscription object.
     */
    @GetMapping("/getSingleSubscription/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Subscription getSubscription(@PathVariable int id) {
        return subscriptionRepository.findById(id).get();
    }
}
