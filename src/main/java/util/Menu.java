package util;

import database.Database;
import domaines.Adresse;
import domaines.Organisation;
import domaines.Personne;
import enums.TypeAdresse;
import repositories.AdresseRepository;
import repositories.OrganisationRepository;
import repositories.PersonneRepository;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class Menu {

    private static AdresseRepository adresseRepository = new AdresseRepository();
    private static PersonneRepository personneRepository = new PersonneRepository();
    private static OrganisationRepository organisationRepository = new OrganisationRepository();

    private static Scanner scanner = new Scanner(System.in);
    private static boolean isOkay = true;

    public static void executeMenu() throws SQLException {

        while(isOkay) {
            printMenu();
            int choice = getChoice();

            if (choice >= 1 || choice <= 3) {
                redirect(choice);
                isOkay = false;
            }

        }
    }

    public static void registerPersonne() throws SQLException {
        Personne personne = new Personne();

        System.out.println("Entrez le nom de la personne");
        personne.setNom(scanner.next());

        System.out.println("Entrez le prénom de la personne");
        personne.setPrenom(scanner.next());

        // TODO Vérifier le format de la date
        System.out.println("Entrez la date de naissance de la personne en format (YYYY-MM-DD)");
        personne.setDateNaissance(LocalDate.parse(scanner.next()));

        registerAdresse();

        personneRepository.persist(personne);
    }

    public static void registerOrganisation() throws SQLException {
        Organisation organisation = new Organisation();

        organisationRepository.persist(organisation);
    }

    public static void registerAdresse() throws SQLException {
        Adresse adresse = new Adresse();

        System.out.println("Entrez le nom de la rue");
        adresse.setNomRue(scanner.next());

        System.out.println("Entrez le numéro de la rue");
        adresse.setNumeroRue(scanner.nextLong());

        System.out.println("Entrez le nom de quartier");
        adresse.setNomQuartier(scanner.next());

        // TODO Vérifier que l'utilisateur ne saisit qu'un chiffre compris
        System.out.println("""
                Sélectionnez le type d'adresse
                    1 - Adresse de résidence
                    2 - Adresse professionnelle
                """);

        int choice = getChoice();

        switch (choice) {
            case 1:
                adresse.setTypeAdresse(TypeAdresse.RESIDENCE);
                break;
            case 2:
                adresse.setTypeAdresse(TypeAdresse.PROFESSIONNELLE);
                break;
        }



        // TODO Créer une méthode pour retourner un booléen
        adresseRepository.persist(adresse);
//        return;
    }

    private static void printMenu() {
        String menu = """
        ------ Bienvenue dans l'annuaire ------
              
              1 - Enregistrer une personne
              2 - Enregistrer une organisation
              3 - Enregistrer une adresse
              
        """;
        System.out.println(menu);
    }

    private static void redirect(int choice) throws SQLException {
        switch (choice) {
            case 1 :
                registerPersonne();
                break;
            case 2 :
                registerOrganisation();
                break;
            case 3 :
                registerAdresse();
                break;
        }
    }

    private static int getChoice() {
        return scanner.nextInt();
    }
}
