package com.autenticacion.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.autenticacion.models.Card;
import com.autenticacion.models.Subscription;
import com.autenticacion.models.User;
import com.autenticacion.repositories.CardRepository;
import com.autenticacion.repositories.SubscriptionRepository;
import com.autenticacion.repositories.UserRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CardController {

    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SubscriptionRepository subscriptionRepository;

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
    public ResponseEntity<String> addCard(@RequestBody Card card) {
        try {
            // Check if the user already has an active card
            User user = card.getUser();
            if (userHasActiveCard(user)) {
                return ResponseEntity.badRequest().body("Failed to add card: User already has an active card.");
            }

            card.setStartDate(LocalDate.now());

            if (card.getEndDate() == null && card.getSubscription() != null) {
                // Fetch the subscription details from the repository
                int subscriptionId = card.getSubscription().getId();
                Subscription subscription = subscriptionRepository.findById(subscriptionId).orElse(null);

                if (subscription != null) {
                    // Calculate the end date based on the subscription duration
                    int duration = subscription.getDuration();
                    LocalDate endDate = card.getStartDate().plusMonths(duration);
                    card.setEndDate(endDate);
                }
            }
            card.setActive(true);
            cardRepository.save(card);
            return ResponseEntity.ok("Card added successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add card: " + e.getMessage());
        }
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

    /**
     * Retrieves the card for the specified user ID.
     *
     * @param id the ID of the user.
     * @return the card associated with the user.
     */
    @GetMapping("/getCard/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public Card getCard(@PathVariable long id) {
        return cardRepository.findByUser(userRepository.findById(id).get());
    }

    /**
     * Checks if the user has an active card.
     *
     * @param user the user to check.
     * @return true if the user has an active card, false otherwise.
     */
    private boolean userHasActiveCard(User user) {
        // Check if the user has an active card in the system
        Card activeCard = cardRepository.findByUser(user);
        return activeCard != null;
    }
}
