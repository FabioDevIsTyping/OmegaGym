package com.autenticacion.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.autenticacion.models.Subscription;
import com.autenticacion.repositories.SubscriptionRepository;

@RestController
@CrossOrigin
public class SubscriptionController {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    /**
     * Retrieves a list of all subscriptions.
     *
     * @return a list of all subscriptions.
     */
    @GetMapping("/getSubscriptions")
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
    @PreAuthorize("hasRole('ADMIN')") 
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
    @DeleteMapping("/deleteSubscriptions/{id}")
    @PreAuthorize("hasRole('ADMIN')") 
    public boolean deleteSubscription(@PathVariable int id) {
        if (subscriptionRepository.existsById(id)) {
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
    @PreAuthorize("hasRole('ADMIN')") 
    public boolean modifySubscription(@RequestBody Subscription subscription) {
        subscriptionRepository.save(subscription);
        return true;
    }
}
