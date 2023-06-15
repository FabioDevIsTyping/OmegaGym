package com.autenticacion;


import com.autenticacion.models.Card;
import com.autenticacion.repositories.CardRepository;
import com.autenticacion.services.CardExpirationJob;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CardExpirationJobTest {

    @Mock
    private CardRepository cardRepository;

    @Test
    public void testCardExpirationJob() {
        // Create test data with expired and non-expired cards
        List<Card> testCards = new ArrayList<>();
        testCards.add(new Card(LocalDate.now().minusDays(1), LocalDate.now().minusDays(1), true)); // Expired card
        testCards.add(new Card(LocalDate.now().minusDays(1), LocalDate.now().plusDays(30), true)); // Non-expired card
        testCards.add(new Card( LocalDate.now().minusDays(2), LocalDate.now().minusDays(1), true)); // Expired card
         assertTrue(testCards.get(1).isActive());
        

        // Mock the card repository to return the test cards
        when(cardRepository.findExpiredCards(LocalDate.now())).thenReturn(testCards);

        // Create an instance of the CardExpirationJob with the mocked repository
        CardExpirationJob cardExpirationJob = new CardExpirationJob(cardRepository);

        // Execute the job manually
        try {
            cardExpirationJob.execute(null);
        } catch (Exception e) {
            // Handle any exceptions if needed
        }

        // Verify the updated isActive values of the expired cards
        assertFalse(testCards.get(0).isActive()); // Expect the first card to be inactive
        assertTrue(testCards.get(1).isActive());  // Expect the second card to still be active
        assertFalse(testCards.get(2).isActive()); // Expect the third card to be inactive
        // ... Verify the isActive values of more cards as needed
    }
}
