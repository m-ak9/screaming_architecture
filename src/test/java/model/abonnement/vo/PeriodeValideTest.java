package model.abonnement.vo;

import model.abonnement.exception.PeriodeValideException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PeriodeValideTest {

    private PeriodeValide periodeValide;

    @Test
    void PeriodeValide_doit_retourner_PeriodeValideException_car_dateDebut_apres_dateFin() {
        assertThatThrownBy(() -> periodeValide = PeriodeValide.of(
            LocalDate.now().plusDays(1),
            LocalDate.now()
        )).isInstanceOf(PeriodeValideException.class);
    }

    @Test
    void PeriodeValide_doit_instancier_correctement() {
        assertThatCode(() -> periodeValide = PeriodeValide.of(
            LocalDate.now().minusDays(1),
            LocalDate.now()
        )).doesNotThrowAnyException();
    }
}