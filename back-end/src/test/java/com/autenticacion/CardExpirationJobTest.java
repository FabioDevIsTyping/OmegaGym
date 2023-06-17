package com.autenticacion;

import com.autenticacion.models.Card;
import com.autenticacion.repositories.CardRepository;
import com.autenticacion.services.CardExpirationJob;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CardExpirationJobTest {

    @Mock
    private CardRepository cardRepository;

    private List<Card> testCards;

    private CardExpirationJob cardExpirationJob;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Piccolo test per vedere se le carte sono settato su active o inactive correttamente
    @Test
    public void testExpiredCardsAreDeactivated() throws JobExecutionException {
        setupTestData();
        cardExpirationJob = new CardExpirationJob(cardRepository);

        // Eseguo il job manualmente
        cardExpirationJob.execute(mock(JobExecutionContext.class));

        // Verifico se is active è settato correttamente
        assertFalse(testCards.get(0).isActive()); // test sulla prima carta
        assertTrue(testCards.get(1).isActive()); // test sulla seconda carta
        assertFalse(testCards.get(2).isActive()); // test sulla terza carta
    }

    // Test per vedere se le carte inattive sono correttamente eliminate dalla repository.
    @Test
    public void testExpiredCardsAreDeleted() throws JobExecutionException {
        setupTestData();
        cardExpirationJob = new CardExpirationJob(cardRepository);

        // Eseguo il job manualmente
        cardExpirationJob.execute(mock(JobExecutionContext.class));

        // Verifico che le carte sono state rimosse correttamente dalla repo
        verify(cardRepository).delete(testCards.get(0));
        verify(cardRepository).delete(testCards.get(2));

        // Verifico che la carte non scadute non siano state eliminate
        verify(cardRepository, never()).delete(testCards.get(1));
    }

    @Test
    public void testNonExpiredCardRemainsActive() throws JobExecutionException {
        setupTestData();
        cardExpirationJob = new CardExpirationJob(cardRepository);

        // Eseguo il job manualmente
        cardExpirationJob.execute(mock(JobExecutionContext.class));

        // Verifico che le carte non scadute rimangano attive
        assertTrue(testCards.get(1).isActive());
    }

    private void setupTestData() {
        // Creo carte per il test
        testCards = new ArrayList<>();
        testCards.add(new Card(LocalDate.now().minusDays(1), LocalDate.now().minusDays(1), true)); // Expired card
        testCards.add(new Card(LocalDate.now().minusDays(1), LocalDate.now().plusDays(30), true)); // Non-expired card
        testCards.add(new Card(LocalDate.now().minusDays(2), LocalDate.now().minusDays(1), true)); // Expired card

        // Repository simulata
        when(cardRepository.findExpiredCards(LocalDate.now())).thenReturn(testCards);
    }
}
