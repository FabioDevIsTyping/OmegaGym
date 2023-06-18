package com.autenticacion.services;

import java.time.LocalDate;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.autenticacion.models.Card;
import com.autenticacion.repositories.CardRepository;

/**
 * Represents a job for updating the status of expired cards.
 */
public class CardExpirationJob implements Job {
    private final CardRepository cardRepository;

    /**
     * Constructs a CardExpirationJob with the given CardRepository.
     *
     * @param cardRepository The CardRepository to be used for accessing card data.
     */
    public CardExpirationJob(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    /**
     * Executes the job to update the status of expired cards.
     *
     * @param context The JobExecutionContext containing job execution data.
     * @throws JobExecutionException If an error occurs during job execution.
     */
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            // Access your card repository or service to retrieve the expired cards
            List<Card> expiredCards = cardRepository.findExpiredCards(LocalDate.now());

            // Update isActive based on endDate
            LocalDate currentDate = LocalDate.now();
            for (Card card : expiredCards) {
                if (card.getEndDate().isAfter(currentDate)) {
                    card.setActive(true);
                    cardRepository.save(card);
                } else {
                    card.setActive(false);
                    cardRepository.delete(card); // Delete the expired card
                }
            }
        } catch (Exception e) {
            throw new JobExecutionException("Failed to update expired cards: " + e.getMessage(), e);
        }
    }
}
