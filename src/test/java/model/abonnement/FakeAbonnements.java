package model.abonnement;

import model.compte.CompteClientId;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class FakeAbonnements implements Abonnements {

    private final AtomicInteger count;
    private final Map<Long, Abonnement> data;

    public FakeAbonnements() {
        count = new AtomicInteger();
        data = new ConcurrentHashMap<>();
    }

    @Override
    public Long save(Abonnement abonnement) {
        data.put(abonnement.getId().getValue(), abonnement);
        return abonnement.getId().getValue();
    }


    @Override
    public Long delete(Abonnement abonnement) {
        return null;
    }


    @Override
    public Abonnement findByCompteClientId(CompteClientId id) {
        return data.get(id.getValue());
    }

    @Override
    public Long resilier(Abonnement abonnement) {
        return null;
    }
}
