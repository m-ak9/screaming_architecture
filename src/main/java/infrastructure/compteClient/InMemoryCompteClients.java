package infrastructure.compteClient;

import model.compteClient.CompteClient;
import model.compteClient.CompteClients;
import model.compteClient.vo.CompteId;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryCompteClients implements CompteClients {

    private final AtomicInteger count;
    private final Map<Integer, CompteEntity> data;
    private final CompteMapping compteMapping;


    public InMemoryCompteClients(CompteMapping compteMapping) {
        this.compteMapping = compteMapping;
        count = new AtomicInteger();
        data = new ConcurrentHashMap<>();
    }

    @Override
    public Long save(CompteClient compteClient) {
        data.put(count.get(), compteMapping.fromDomain(compteClient));
        return Long.valueOf(count.getAndIncrement());
    }

    @Override
    public CompteClient findByCompteClientId(CompteId id) {
        return compteMapping.toDomain(data.get(id.getValue()));
    }


}
