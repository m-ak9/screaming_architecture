package model.abonnement;

import lombok.Builder;
import lombok.Data;
import model.abonnement.vo.AbonnementId;
import model.abonnement.vo.AbonnementStatus;
import model.abonnement.vo.Formule;
import model.abonnement.vo.PeriodeValidite;
import model.compte.CompteClientId;

import java.time.LocalDate;

@Data
@Builder
public class Abonnement {
    private final AbonnementId id;
    private final Formule formule;
    private final AbonnementStatus status;
    private final PeriodeValidite periodeValidite;
    private final CompteClientId compteClientId;

    public void verifierConditionsResilliation() throws Exception {
        if (!periodeValidite.getDateDeDebut().isBefore(LocalDate.now().minusMonths(3))) {
            throw new Exception("La résilliation est possible uniquement après 3 mois");
        }
    }
}