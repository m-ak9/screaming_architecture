package model.abonnement.acl;

import model.abonnement.exception.PasEligibleAbonnementException;
import model.compteClient.CompteClient;
import model.compteClient.vo.DateNaissance;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ClientTest {

    private Client client;

    @Test
    void estEligibleAbonnement_doit_renvoyer_PasEligibleAbonnementException() {
        client = Client.fromCompteClient(
            CompteClient.builder()
                .dateNaissance(DateNaissance.of(LocalDate.now().minusYears(11)))
                .build()
        );

        assertThatThrownBy(() -> client.estEligibleAbonnement()).isInstanceOf(PasEligibleAbonnementException.class);
    }

    @Test
    void estEligibleAbonnement_doit_renvoyer_vrai() {
        client = Client.fromCompteClient(
            CompteClient.builder()
                .dateNaissance(DateNaissance.of(LocalDate.now().minusYears(13)))
                .build()
        );

        assertThat(client.estEligibleAbonnement()).isTrue();
    }
}