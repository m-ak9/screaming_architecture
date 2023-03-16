package use_case.abonnement;

import model.abonnement.Abonnement;
import model.abonnement.Abonnements;
import model.compteClient.CompteClient;
import model.compteClient.CompteClients;
import model.compteClient.vo.CompteId;

public class ResilierAbonnement {

    private final Abonnements abonnements;
    private final CompteClients compteClients;

    public ResilierAbonnement(Abonnements abonnements, CompteClients compteClients) {
        this.abonnements = abonnements;
        this.compteClients = compteClients;
    }

    public void resillierAbonnement(CompteId compteId) throws Exception {

        CompteClient compteClient = compteClients.findByCompteClientId(compteId);       //Shared State ? OUI
        Abonnement abonnement = abonnements.findByCompteClientId(compteClient.getId());     //Shared State ? OUI

        abonnement.verifierConditionsResilliation();       //Shared State ? NON
        compteClient.abonnementActif(false);

        this.abonnements.resilier(abonnement);     //Shared State ? OUI
        this.compteClients.save(compteClient);        //Shared State ? OUI

        compteClients.save(compteClient);   //Shared State ? YES

    }
}
