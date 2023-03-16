package infrastructure.abonnement;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class AbonnementEntity {
    private final Long id;
    private final String formule;
    private final String status;
    private final LocalDate dateDeDebut;
    private final LocalDate dateDeFin;
    private final Long compteClientId;
}
