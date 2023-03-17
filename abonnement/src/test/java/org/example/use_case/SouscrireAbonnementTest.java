package org.example.use_case;


import org.example.infrastructure.FakeAbonnements;
import org.example.infrastructure.FakeCompteClients;
import org.example.model.Abonnements;
import org.example.model.CompteClient;
import org.example.model.CompteClients;
import org.example.model.exception.PasEligibleAbonnementException;
import org.example.model.vo.CompteId;
import org.example.model.vo.DateNaissance;
import org.example.use_case.ohs.DefaultRecuperationCompteClient;
import org.example.use_case.ohs.RecuperationCompteClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class SouscrireAbonnementTest {

    private SouscrireAbonnement souscrireAbonnement;
    private CompteClients compteClients;

    @BeforeEach
    void setUp() {
        Abonnements abonnements = new FakeAbonnements();
        compteClients = new FakeCompteClients();
        RecuperationCompteClient recuperationCompteClient = new DefaultRecuperationCompteClient(compteClients);
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