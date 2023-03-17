package org.example.use_case.ohs;

import org.example.model.CompteClient;

public interface RecuperationCompteClient {

    CompteClient recupererCompteClientParId(Long compteClientId);
}
