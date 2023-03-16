package use_case.abonnement;

import model.abonnement.Abonnement;
import model.abonnement.Abonnements;
import model.abonnement.FakeAbonnements;
import model.abonnement.FakeComptes;
import model.abonnement.vo.AbonnementId;
import model.abonnement.vo.AbonnementStatus;
import model.abonnement.vo.PeriodeValidite;
import model.compte.CompteClient;
import model.compte.CompteClientId;
import model.compte.Comptes;
import model.compte.Email;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ResilierAbonnementTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void doit_renvoyer_exception_quand_abonnement_de_moins_de_3_mois() {

        //Setup + Mock
        final Abonnements abonnements = new FakeAbonnements();
        final Comptes comptes = new FakeComptes();

        CompteClientId compteClientId = CompteClientId.of(1L);
        CompteClient fakeCompteClient = CompteClient.builder()
                .id(compteClientId)
                .nom("DUPONT")
                .prenom("Jean")
                .email(Email.of("dupont@gmail.com"))
                .dateNaissance(LocalDate.of(1987, 2, 7))
                .dateCreation(LocalDate.now().atStartOfDay().minusYears(3))
                .abonnementActif(true)
                .build();
        Abonnement fakeAbonnement = Abonnement.builder()
                .id(AbonnementId.of(1L))
                .compteClientId(compteClientId)
                .status(AbonnementStatus.ACTIF)
                .periodeValidite(PeriodeValidite.of(LocalDate.now().minusMonths(2), null))
                .build();
        comptes.save(fakeCompteClient);
        abonnements.save(fakeAbonnement);

        final ResilierAbonnement resilierAbonnement = new ResilierAbonnement(abonnements, comptes);


        Exception exception = assertThrows(Exception.class, () ->
                resilierAbonnement.resillierAbonnement(compteClientId)
        );

        String expectedMessage = "La résilliation est possible uniquement après 3 mois";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void doit_changer_le_status_de_abonnement_sur_le_compte_client() {

        //Setup + Mock
        final Abonnements abonnements = new FakeAbonnements();
        final Comptes comptes = new FakeComptes();

        CompteClientId compteClientId = CompteClientId.of(1L);
        CompteClient fakeCompteClient = CompteClient.builder()
                .id(compteClientId)
                .nom("DUPONT")
                .prenom("Jean")
                .email(Email.of("dupont@gmail.com"))
                .dateNaissance(LocalDate.of(1987, 2, 7))
                .dateCreation(LocalDate.now().atStartOfDay().minusYears(3))
                .abonnementActif(true)
                .build();
        Abonnement fakeAbonnement = Abonnement.builder()
                .id(AbonnementId.of(1L))
                .compteClientId(compteClientId)
                .status(AbonnementStatus.ACTIF)
                .periodeValidite(PeriodeValidite.of(LocalDate.now().minusMonths(4), null))
                .build();
        comptes.save(fakeCompteClient);
        abonnements.save(fakeAbonnement);

        final ResilierAbonnement resilierAbonnement = new ResilierAbonnement(abonnements, comptes);

        try {
            resilierAbonnement.resillierAbonnement(compteClientId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertFalse(comptes.findByCompteClientId(compteClientId).statusAbonnement());
    }


}