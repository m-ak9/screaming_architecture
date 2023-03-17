package org.example.infrastructure;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class CompteEntity {
    private final Long id;
    private final String prenom;
    private final String nom;
    private final LocalDate dateNaissance;
    private final String email;
    private final LocalDateTime dateCreation;
    private final boolean abonnementActif;

}
