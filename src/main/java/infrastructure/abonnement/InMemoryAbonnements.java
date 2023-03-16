package infrastructure.abonnement;

import model.abonnement.Abonnement;
import model.abonnement.Abonnements;
import model.compteClient.vo.CompteId;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryAbonnements implements Abonnements {

    private final AtomicInteger count;
    private final Map<Integer, AbonnementEntity> data;
    private final AbonnementMapping abonnementMapping;

    public InMemoryAbonnements(AbonnementMapping abonnementMapping) {
        this.abonnementMapping = abonnementMapping;
        count = new AtomicInteger();
        data = new ConcurrentHashMap<>();
    }

    @Override
    public Long save(Abonnement abonnement) {
        data.put(count.get(), abonnementMapping.fromDomain(abonnement));
        return Long.valueOf(count.getAndIncrement());
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
