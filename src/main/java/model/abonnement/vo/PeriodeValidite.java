package model.abonnement.vo;

import lombok.Data;

import java.time.LocalDate;

@Data(staticConstructor = "of")
public final class PeriodeValidite {
    private final LocalDate dateDeDebut;
    private final LocalDate dateDeFin;
}
