package model.compteClient.vo;

import lombok.Data;

@Data(staticConstructor = "of")
public class CompteId {
    private static Long PAS_ENCORE_CREE = -1L;
    private final Long value;

    public static CompteId pasEncoreCree() {
        return new CompteId(PAS_ENCORE_CREE);
    }
}
