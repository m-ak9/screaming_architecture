package use_case.abonnement;


import infrastructure.abonnement.FakeAbonnements;
import infrastructure.compteClient.FakeCompteClients;
import model.abonnement.Abonnements;
import model.abonnement.exception.PasEligibleAbonnementException;
import model.compteClient.CompteClient;
import model.compteClient.CompteClients;
import model.compteClient.vo.CompteId;
import model.compteClient.vo.DateNaissance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.compteClient.ohs.DefaultRecuperationCompteClient;
import use_case.compteClient.ohs.RecuperationCompteClient;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class SouscrireAbonnementTest {

    private SouscrireAbonnement souscrireAbonnement;
    private Abonnements abonnements;
    private CompteClients compteClients;
    private RecuperationCompteClient recuperationCompteClient;

    @BeforeEach
    void setUp() {
        abonnements = new FakeAbonnements();
        compteClients = new FakeCompteClients();
        recuperationCompteClient = new DefaultRecuperationCompteClient(compteClients);
        souscrireAbonnement = new SouscrireAbonnement(abonnements, recuperationCompteClient);
    }

    @Test
    void souscrireAbonnementClassique_doit_renvoyer_PasEligibleAbonnementException() {
        // Mock
        compteClients.save(
            CompteClient
                .builder()
                .id(CompteId.of(1L))
                .dateNaissance(DateNaissance.of(LocalDate.now().minusYears(11)))
                .build()
        );

        // Assertions
        assertThatThrownBy(() -> souscrireAbonnement.souscrireAbonnementClassique(1L))
            .isInstanceOf(PasEligibleAbonnementException.class);
    }

    @Test
    void souscrireAbonnementClassique() {
        // Mock
        compteClients.save(
            CompteClient
                .builder()
                .id(CompteId.of(1L))
                .dateNaissance(DateNaissance.of(LocalDate.now().minusYears(13)))
                .build()
        );

        // Assertions
        assertThat(souscrireAbonnement.souscrireAbonnementClassique(1L)).isEqualTo(1L);

    }
}