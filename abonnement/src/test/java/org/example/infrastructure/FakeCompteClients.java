package org.example.infrastructure;


import org.example.model.CompteClient;
import org.example.model.CompteClients;
import org.example.model.vo.CompteId;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class FakeCompteClients implements CompteClients {

    private final AtomicLong count;
    private final Map<Long, CompteClient> data;

    public FakeCompteClients() {
        count = new AtomicLong();
        data = new ConcurrentHashMap<>();
    }

    @Override
    public Long save(CompteClient compteClient) {
        data.put(count.incrementAndGet(), compteClient);
        return count.get();
    }

    @Override
    public CompteClient findByCompteClientId(CompteId id) {
        return data.get(id.getValue());
    }
}
