package model.abonnement;

import model.compte.CompteClientId;

public interface Abonnements {
    Long save(Abonnement abonnement);
    Long delete(Abonnement abonnement);

    Abonnement findByCompteClientId(CompteClientId id);

    Long resilier(Abonnement abonnement);
}
