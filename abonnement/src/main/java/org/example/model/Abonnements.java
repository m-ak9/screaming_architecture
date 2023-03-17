package org.example.model;


import org.example.model.vo.CompteId;

public interface Abonnements {
    Long save(Abonnement abonnement);

    Long delete(Abonnement abonnement);

    Abonnement findByCompteClientId(CompteId id);

    Long resilier(Abonnement abonnement);
}
