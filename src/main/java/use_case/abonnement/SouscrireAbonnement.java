package use_case.abonnement;

import model.abonnement.Abonnement;
import model.abonnement.Abonnements;
import model.abonnement.acl.Client;
import model.abonnement.vo.AbonnementId;
import model.abonnement.vo.AbonnementStatus;
import use_case.compteClient.ohs.RecuperationCompteClient;

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

        Long abonnementId = this.abonnements.save(abonnement);
        return abonnementId;
    }
}
