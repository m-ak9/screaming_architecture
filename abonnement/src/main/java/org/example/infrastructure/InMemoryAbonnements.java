package org.example.infrastructure;

import org.example.model.Abonnement;
import org.example.model.Abonnements;
import org.example.model.vo.CompteId;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryAbonnements implements Abonnements {

    private final AtomicLong count;
    private final Map<Long, AbonnementEntity> data;
    private final AbonnementMapping abonnementMapping;

    public InMemoryAbonnements(AbonnementMapping abonnementMapping) {
        this.abonnementMapping = abonnementMapping;
        count = new AtomicLong();
        data = new ConcurrentHashMap<>();
    }

    @Override
    public Long save(Abonnement abonnement) {
        data.put(count.get(), abonnementMapping.fromDomain(abonnement));
        return (long) count.getAndIncrement();
    }


    @Override
    public Long delete(Abonnement abonnement) {
        return null;
    }


    @Override
    public Abonnement findByCompteClientId(CompteId id) {
        return abonnementMapping.toDomain(data.get(id.getValue()));
    }

    @Override
    public Long resilier(Abonnement abonnement) {
        return null;
    }
}
