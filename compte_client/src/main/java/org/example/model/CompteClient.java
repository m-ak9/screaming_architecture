package org.example.model;

import lombok.Builder;
import lombok.Data;
import org.example.model.vo.CompteId;
import org.example.model.vo.DateNaissance;
import org.example.model.vo.Email;

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
