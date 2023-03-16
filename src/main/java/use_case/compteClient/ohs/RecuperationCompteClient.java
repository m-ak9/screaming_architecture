package use_case.compteClient.ohs;

import model.compteClient.CompteClient;

public interface RecuperationCompteClient {

    CompteClient recupererCompteClientParId(Long compteClientId);
}
