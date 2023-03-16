package model.abonnement.vo;

import lombok.Data;
import model.abonnement.exception.PeriodeValideException;

import java.time.LocalDate;

import static java.util.Objects.isNull;

@Data(staticConstructor = "of")
public final class PeriodeValide {
    private final LocalDate dateDeDebut;
    private final LocalDate dateDeFin;

    private PeriodeValide(LocalDate dateDeDebut, LocalDate dateDeFin) {
        if (isNull(dateDeDebut) || isNull(dateDeFin) || dateDeDebut.isAfter(dateDeFin))
            throw new PeriodeValideException("La date de début ne peut pas être après la date de fin");

        this.dateDeDebut = dateDeDebut;
        this.dateDeFin = dateDeFin;
    }
}
