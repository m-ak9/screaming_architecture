package org.example.use_case.ohs;

import org.example.model.CompteClient;
import org.example.model.CompteClients;
import org.example.model.vo.CompteId;

public class DefaultRecuperationCompteClient implements RecuperationCompteClient {

    private final CompteClients compteClients;

    public DefaultRecuperationCompteClient(CompteClients compteClients) {
        this.compteClients = compteClients;
    }

    @Override
    public CompteClient recupererCompteClientParId(Long compteClientId) {
        return compteClients.findByCompteClientId(CompteId.of(compteClientId));
    }
}
