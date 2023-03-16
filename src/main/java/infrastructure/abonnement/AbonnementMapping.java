package infrastructure.abonnement;

import model.abonnement.Abonnement;
import model.abonnement.vo.AbonnementId;
import model.abonnement.vo.AbonnementStatus;
import model.abonnement.vo.Formule;
import model.abonnement.vo.PeriodeValide;
import model.compteClient.vo.CompteId;

public class AbonnementMapping {
    Abonnement toDomain(AbonnementEntity abonnementEntity) {
        return Abonnement
            .builder()
            .id(AbonnementId.of(abonnementEntity.getId()))
            .formule(Formule.valueOf(abonnementEntity.getFormule()))
            .status(AbonnementStatus.valueOf(abonnementEntity.getStatus()))
            .periodeValide(PeriodeValide.of(abonnementEntity.getDateDeDebut(), abonnementEntity.getDateDeFin()))
            .compteId(CompteId.of(abonnementEntity.getCompteClientId()))
            .build();
    }

    AbonnementEntity fromDomain(Abonnement abonnement) {
        return AbonnementEntity
            .builder()
            .id(abonnement.getId().getValue())
            .formule(abonnement.getFormule().name())
            .status(abonnement.getStatus().name())
            .dateDeFin(abonnement.getPeriodeValide().getDateDeDebut())
            .dateDeFin(abonnement.getPeriodeValide().getDateDeFin())
            .compteClientId(abonnement.getCompteId().getValue())
            .build();
    }
}
