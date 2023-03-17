package org.example.model;

import lombok.Builder;
import lombok.Data;
import org.example.model.exception.PasEligibleResilliationException;
import org.example.model.vo.*;

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