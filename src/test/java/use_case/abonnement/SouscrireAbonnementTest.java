package use_case.abonnement;


import model.abonnement.Abonnements;
import model.abonnement.FakeAbonnements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SouscrireAbonnementTest {

    private SouscrireAbonnement souscrireAbonnement;
    private Abonnements abonnements;

    @BeforeEach
    void setUp() {
        abonnements = new FakeAbonnements();
        souscrireAbonnement = new SouscrireAbonnement(abonnements);
    }

    @Test
    void souscrireAbonnementClassique() {
        //assertThat(souscrireAbonnement.souscrireAbonnementClassique());
    }
}