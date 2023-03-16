package model.compteClient;

import lombok.Builder;
import lombok.Data;
import model.compteClient.vo.CompteId;
import model.compteClient.vo.DateNaissance;
import model.compteClient.vo.Email;

import java.time.LocalDateTime;

@Data
@Builder
public class CompteClient {
    private final CompteId id;
    private final String prenom;
    private final String nom;
    private final DateNaissance dateNaissance;
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
