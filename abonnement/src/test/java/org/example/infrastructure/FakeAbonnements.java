package org.example.infrastructure;

import org.example.model.Abonnement;
import org.example.model.Abonnements;
import org.example.model.vo.CompteId;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class FakeAbonnements implements Abonnements {

    private final AtomicLong count;
    private final Map<Long, Abonnement> data;

    public FakeAbonnements() {
        count = new AtomicLong();
        data = new ConcurrentHashMap<>();
    }

    @Override
    public Long save(Abonnement abonnement) {
        data.put(count.incrementAndGet(), abonnement);
        return count.get();
    }


    @Override
    public Long delete(Abonnement abonnement) {
        return null;
    }

    @Override
    public Abonnement findByCompteClientId(CompteId id) {
        return data.get(id.getValue());
    }

    @Override
    public Long resilier(Abonnement abonnement) {
        return null;
    }
}
