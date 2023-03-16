package model.abonnement;

import model.compte.CompteClient;
import model.compte.CompteClientId;
import model.compte.Comptes;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class FakeComptes implements Comptes {

    private final AtomicInteger count;
    private final Map<Long, CompteClient> data;

    public FakeComptes() {
        count = new AtomicInteger();
        data = new ConcurrentHashMap<>();
    }

    @Override
    public Long save(CompteClient compteClient) {
        data.put(compteClient.getId().getValue(), compteClient);
        return compteClient.getId().getValue();
    }

    @Override
    public CompteClient findByCompteClientId(CompteClientId id) {
        return data.get(id.getValue());
    }
}
