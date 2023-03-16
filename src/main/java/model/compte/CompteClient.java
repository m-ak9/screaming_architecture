package model.compte;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class CompteClient {
    private final CompteClientId id;
    private final String prenom;
    private final String nom;
    private final LocalDate dateNaissance;
    private final Email email;
    private final LocalDateTime dateCreation;
    private boolean abonnementActif;

    public void abonnementActif(boolean abonnementActif) {
        this.abonnementActif = abonnementActif;
    }

    public boolean statusAbonnement() {
        return this.abonnementActif;
    }
}
