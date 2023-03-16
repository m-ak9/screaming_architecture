package model.abonnement;

import lombok.Builder;
import lombok.Data;
import model.abonnement.exception.PasEligibleResilliationException;
import model.abonnement.vo.AbonnementId;
import model.abonnement.vo.AbonnementStatus;
import model.abonnement.vo.Formule;
import model.abonnement.vo.PeriodeValide;
import model.compteClient.vo.CompteId;

import java.time.LocalDate;

@Data
@Builder
public class Abonnement {
    private final AbonnementId id;
    private final Formule formule;
    private final AbonnementStatus status;
    private final PeriodeValide periodeValide;
    private final CompteId compteId;

    public void verifierConditionsResilliation() {
        if (!periodeValide.getDateDeDebut().isBefore(LocalDate.now().minusMonths(3))) {
            throw new PasEligibleResilliationException("La résilliation est possible uniquement après 3 mois");
        }
    }
}