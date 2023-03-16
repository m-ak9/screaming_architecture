package model.abonnement.vo;

import lombok.Data;

@Data(staticConstructor = "of")
public class AbonnementId {
    private static Long PAS_ENCORE_CREE = -1L;
    private final Long value;

    public static AbonnementId pasEncoreCree() {
        return new AbonnementId(PAS_ENCORE_CREE);
    }
}
