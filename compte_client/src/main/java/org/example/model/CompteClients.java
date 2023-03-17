package org.example.model;

import org.example.model.vo.CompteId;

public interface CompteClients {
    Long save(CompteClient compteClient);

    CompteClient findByCompteClientId(CompteId id);
}
