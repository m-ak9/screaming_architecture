package use_case.abonnement;

import model.abonnement.Abonnement;
import model.abonnement.Abonnements;
import model.compte.CompteClient;
import model.compte.CompteClientId;
import model.compte.Comptes;

public class ResilierAbonnement {

    private final Abonnements abonnements;
    private final Comptes comptes;

    public ResilierAbonnement(Abonnements abonnements, Comptes comptes) {
        this.abonnements = abonnements;
        this.comptes = comptes;
    }

    public void resillierAbonnement(CompteClientId compteClientId) throws Exception {

        CompteClient compteClient = comptes.findByCompteClientId(compteClientId);       //Shared State ? OUI
        Abonnement abonnement = abonnements.findByCompteClientId(compteClient.getId());     //Shared State ? OUI

        abonnement.verifierConditionsResilliation();       //Shared State ? NON
        compteClient.abonnementActif(false);

        this.abonnements.resilier(abonnement);     //Shared State ? OUI
        this.comptes.save(compteClient);        //Shared State ? OUI

        comptes.save(compteClient);   //Shared State ? YES

    }
}
