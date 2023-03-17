package org.example.use_case;

import org.example.model.Abonnement;
import org.example.model.Abonnements;
import org.example.model.acl.Client;
import org.example.model.vo.AbonnementId;
import org.example.model.vo.AbonnementStatus;
import org.example.use_case.ohs.RecuperationCompteClient;

public class SouscrireAbonnement {

    private final Abonnements abonnements;
    private final RecuperationCompteClient recuperationCompteClient;

    public SouscrireAbonnement(Abonnements abonnements, RecuperationCompteClient recuperationCompteClient) {
        this.abonnements = abonnements;
        this.recuperationCompteClient = recuperationCompteClient;
    }

    public Long souscrireAbonnementClassique(Long compteClientId) {
        Client client = Client.fromCompteClient(recuperationCompteClient.recupererCompteClientParId(compteClientId));

        Abonnement abonnement = Abonnement
                .builder()
                .id(AbonnementId.of(compteClientId))
                .status(AbonnementStatus.ACTIF)
                .build();

        client.estEligibleAbonnement();

        return this.abonnements.save(abonnement);
    }
}
