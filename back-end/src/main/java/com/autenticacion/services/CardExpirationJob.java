package com.autenticacion.services;

import java.time.LocalDate;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.autenticacion.models.Card;
import com.autenticacion.repositories.CardRepository;

public class CardExpirationJob implements Job {
    private final CardRepository cardRepository;

    public CardExpirationJob(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

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
