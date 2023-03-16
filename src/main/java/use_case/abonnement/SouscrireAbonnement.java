package use_case.abonnement;

import model.abonnement.*;
import model.abonnement.vo.AbonnementId;
import model.abonnement.vo.AbonnementStatus;

public class SouscrireAbonnement {

    private final Abonnements abonnements;

    public SouscrireAbonnement(Abonnements abonnements) {
        this.abonnements = abonnements;
    }

    public Long souscrireAbonnementClassique(Long compteClientId) {

        Abonnement abonnement = Abonnement
                .builder()
                .id(AbonnementId.of(compteClientId))
                .status(AbonnementStatus.ACTIF)
                .build();

        Long abonnementId = this.abonnements.save(abonnement);//Shared State ? OUI
        return abonnementId;     //Shared State ?  NON
    }
}
