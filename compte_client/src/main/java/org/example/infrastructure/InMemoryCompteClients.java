package org.example.infrastructure;

import org.example.model.CompteClient;
import org.example.model.CompteClients;
import org.example.model.vo.CompteId;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryCompteClients implements CompteClients {

    private final AtomicLong count;
    private final Map<Long, CompteEntity> data;
    private final CompteMapping compteMapping;


    public InMemoryCompteClients(CompteMapping compteMapping) {
        this.compteMapping = compteMapping;
        count = new AtomicLong();
        data = new ConcurrentHashMap<>();
    }

    @Override
    public Long save(CompteClient compteClient) {
        data.put(count.get(), compteMapping.fromDomain(compteClient));
        return count.getAndIncrement();
    }

    @Override
    public CompteClient findByCompteClientId(CompteId id) {
        return compteMapping.toDomain(data.get(id.getValue()));
    }


}
