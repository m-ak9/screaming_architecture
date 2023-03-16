package model.compteClient;

import model.compteClient.vo.CompteId;

public interface CompteClients {
    Long save(CompteClient compteClient);

    CompteClient findByCompteClientId(CompteId id);
}
