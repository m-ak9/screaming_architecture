package infrastructure.compteClient;

import model.compteClient.CompteClient;
import model.compteClient.vo.CompteId;
import model.compteClient.vo.DateNaissance;
import model.compteClient.vo.Email;

public class CompteMapping {

    CompteClient toDomain(CompteEntity compteEntity) {
        return CompteClient
            .builder()
            .id(CompteId.of(compteEntity.getId()))
            .prenom(String.valueOf(compteEntity.getPrenom()))
            .nom(String.valueOf(compteEntity.getNom()))
            .dateNaissance(DateNaissance.of(compteEntity.getDateNaissance()))
            .email(Email.of(compteEntity.getEmail()))
            .dateCreation(compteEntity.getDateCreation())
            .abonnementActif(compteEntity.isAbonnementActif())
            .build();
    }

    CompteEntity fromDomain(CompteClient compteClient) {
        return CompteEntity
            .builder()
            .id(compteClient.getId().getValue())
            .prenom(compteClient.getPrenom())
            .nom(compteClient.getNom())
            .dateNaissance(compteClient.getDateNaissance().getValue())
            .email(compteClient.getEmail().getValue())
            .dateCreation(compteClient.getDateCreation())
            .abonnementActif(compteClient.isAbonnementActif())
            .build();
    }
}
