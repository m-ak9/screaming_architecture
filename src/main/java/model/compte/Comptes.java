package model.compte;

public interface Comptes {
    Long save(CompteClient compteClient);

    CompteClient findByCompteClientId(CompteClientId id);
}
