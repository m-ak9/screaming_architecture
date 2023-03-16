package use_case.compteClient.ohs;

import model.compteClient.CompteClient;
import model.compteClient.CompteClients;
import model.compteClient.vo.CompteId;

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
