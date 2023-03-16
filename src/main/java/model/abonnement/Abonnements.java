package model.abonnement;

import model.compteClient.vo.CompteId;

public interface Abonnements {
    Long save(Abonnement abonnement);
    Long delete(Abonnement abonnement);

    Abonnement findByCompteClientId(CompteId id);

    Long resilier(Abonnement abonnement);
}
