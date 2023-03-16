package model.abonnement;

import model.abonnement.exception.PasEligibleResilliationException;
import model.abonnement.vo.PeriodeValide;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AbonnementTest {

    private Abonnement abonnement;

    @Test
    void verifierConditionsResilliation_doit_retourner_PasEligibleResilliationException() {
        abonnement = Abonnement
            .builder()
            .periodeValide(PeriodeValide.of(
                LocalDate.now().minusMonths(2),
                LocalDate.now()
            ))
            .build();

        assertThatThrownBy(() -> abonnement.verifierConditionsResilliation())
            .isInstanceOf(PasEligibleResilliationException.class);
    }

    @Test
    void verifierConditionsResilliation_doit_marcher_correctement() {
        abonnement = Abonnement
            .builder()
            .periodeValide(PeriodeValide.of(
                LocalDate.now().minusMonths(4),
                LocalDate.now()
            ))
            .build();

        assertThatCode(() -> abonnement.verifierConditionsResilliation())
            .doesNotThrowAnyException();
    }
}