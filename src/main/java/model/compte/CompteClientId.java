package model.compte;

import lombok.Data;

@Data(staticConstructor = "of")
public class CompteClientId {
    private static Long PAS_ENCORE_CREE = -1L;
    private final Long value;

    public static CompteClientId pasEncoreCree() {
        return new CompteClientId(PAS_ENCORE_CREE);
    }
}
