package org.example.use_case;

import org.example.model.Abonnement;
import org.example.model.Abonnements;
import org.example.model.CompteClient;
import org.example.model.CompteClients;
import org.example.model.vo.CompteId;

public class ResilierAbonnement {

    private final Abonnements abonnements;
    private final CompteClients compteClients;

    public ResilierAbonnement(Abonnements abonnements, CompteClients compteClients) {
        this.abonnements = abonnements;
        this.compteClients = compteClients;
    }

    public void resillierAbonnement(CompteId compteId) {

        CompteClient compteClient = compteClients.findByCompteClientId(compteId);       //Shared State ? OUI
        Abonnement abonnement = abonnements.findByCompteClientId(compteClient.getId());     //Shared State ? OUI

        abonnement.verifierConditionsResilliation();       //Shared State ? NON
        compteClient.abonnementActif(false);

        this.abonnements.resilier(abonnement);     //Shared State ? OUI
        this.compteClients.save(compteClient);        //Shared State ? OUI

        compteClients.save(compteClient);   //Shared State ? YES

    }
}
