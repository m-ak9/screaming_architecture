package use_case.abonnement;

import infrastructure.abonnement.FakeAbonnements;
import infrastructure.compteClient.FakeCompteClients;
import model.abonnement.Abonnement;
import model.abonnement.Abonnements;
import model.abonnement.vo.AbonnementId;
import model.abonnement.vo.AbonnementStatus;
import model.abonnement.vo.PeriodeValide;
import model.compteClient.CompteClient;
import model.compteClient.CompteClients;
import model.compteClient.vo.CompteId;
import model.compteClient.vo.DateNaissance;
import model.compteClient.vo.Email;
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
        final CompteClients compteClients = new FakeCompteClients();

        CompteId compteId = CompteId.of(1L);
        CompteClient fakeCompteClient = CompteClient.builder()
                .id(compteId)
                .nom("DUPONT")
                .prenom("Jean")
                .email(Email.of("dupont@gmail.com"))
                .dateNaissance(DateNaissance.of(LocalDate.of(1987, 2, 7)))
                .dateCreation(LocalDate.now().atStartOfDay().minusYears(3))
                .abonnementActif(true)
                .build();
        Abonnement fakeAbonnement = Abonnement.builder()
                .id(AbonnementId.of(1L))
                .compteId(compteId)
                .status(AbonnementStatus.ACTIF)
                .periodeValide(PeriodeValide.of(LocalDate.now().minusMonths(2), LocalDate.now()))
                .build();
        compteClients.save(fakeCompteClient);
        abonnements.save(fakeAbonnement);

        final ResilierAbonnement resilierAbonnement = new ResilierAbonnement(abonnements, compteClients);


        Exception exception = assertThrows(Exception.class, () ->
                resilierAbonnement.resillierAbonnement(compteId)
        );

        String expectedMessage = "La résilliation est possible uniquement après 3 mois";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void doit_changer_le_status_de_abonnement_sur_le_compte_client() {

        //Setup + Mock
        final Abonnements abonnements = new FakeAbonnements();
        final CompteClients compteClients = new FakeCompteClients();

        CompteId compteId = CompteId.of(1L);
        CompteClient fakeCompteClient = CompteClient.builder()
                .id(compteId)
                .nom("DUPONT")
                .prenom("Jean")
                .email(Email.of("dupont@gmail.com"))
                .dateNaissance(DateNaissance.of(LocalDate.of(1987, 2, 7)))
                .dateCreation(LocalDate.now().atStartOfDay().minusYears(3))
                .abonnementActif(true)
                .build();
        Abonnement fakeAbonnement = Abonnement.builder()
                .id(AbonnementId.of(1L))
                .compteId(compteId)
                .status(AbonnementStatus.ACTIF)
                .periodeValide(PeriodeValide.of(LocalDate.now().minusMonths(4), LocalDate.now()))
                .build();
        compteClients.save(fakeCompteClient);
        abonnements.save(fakeAbonnement);

        final ResilierAbonnement resilierAbonnement = new ResilierAbonnement(abonnements, compteClients);

        try {
            resilierAbonnement.resillierAbonnement(compteId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertFalse(compteClients.findByCompteClientId(compteId).statusAbonnement());
    }
}