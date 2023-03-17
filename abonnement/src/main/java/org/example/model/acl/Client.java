package org.example.model.acl;

import org.example.model.CompteClient;
import org.example.model.exception.PasEligibleAbonnementException;

import java.time.LocalDate;
import java.time.Period;

public class Client {
    private final CompteClient compteClient;

    private Client(CompteClient compteClient) {
        this.compteClient = compteClient;
    }

    public static Client fromCompteClient(CompteClient compteClient) {
        return new Client(compteClient);
    }

    public boolean estEligibleAbonnement() {
        LocalDate maintenant = LocalDate.now();
        Period difference = Period.between(compteClient.getDateNaissance().getValue(), maintenant);
        if (difference.getYears() < 12) {
            throw new PasEligibleAbonnementException("La personne n'a pas l'âge requis pour s'abonner");
        }
        return true;
    }
}
