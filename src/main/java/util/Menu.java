package util;

import domaines.Adresse;
import domaines.Organisation;
import domaines.Personne;
import enums.TypeAdresse;
import repositories.AdresseRepository;
import repositories.OrganisationRepository;
import repositories.PersonneRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private static AdresseRepository adresseRepository = new AdresseRepository();
    private static PersonneRepository personneRepository = new PersonneRepository();
    private static OrganisationRepository organisationRepository = new OrganisationRepository();

    private static Scanner scanner = new Scanner(System.in);
    private static boolean isOkay = true;

    public static void executeMenu() throws SQLException {

        printMenu();
        do {
            int choice = getChoice();
            scanner.nextLine();

            if (choice >= 1 && choice <= 3) {
                redirect(choice);
            } else System.out.println("Entrez une option valide");


        }while (isOkay);
        scanner.close();
    }

    public static void registerPersonne() throws SQLException {
        Personne personne = new Personne();

        System.out.println("\n===== Menu création personne ======");
        System.out.println("\nEntrez le nom de la personne");
        personne.setNom(scanner.nextLine());

        System.out.println("Entrez le prénom de la personne");
        personne.setPrenom(scanner.nextLine());

        // TODO Vérifier le format de la date
        System.out.println("Entrez la date de naissance de la personne en format (YYYY-MM-DD)");
        personne.setDateNaissance(LocalDate.parse(scanner.nextLine()));

        // TODO Lui donner la possibilité de créer ou de sélectionner une adresse
        System.out.println("""
            
            1 - Saisir l'adresse
            2 - Sélectionner une addresse
                """);
        int choice = getChoice();
        switch (choice) {
            case 1:
                scanner.nextLine();
                personne.setAdresse(registerAdresse());
                break;
            case 2:
                scanner.nextLine();
                List<Adresse> adresses = adresseRepository.findAll();


                for (int i = 0; i < adresses.size(); i++) {
                    System.out.println(i + 1 + " - " + adresses.get(i).fullAdresse());
                }

                int otherChoice = getChoice() - 1;

                // TODO Vérifier que user insère une bonne option
                personne.setAdresse(adresses.get(otherChoice));
                break;
        }

        personneRepository.persist(personne);
    }

    public static void registerOrganisation() throws SQLException {
        Organisation organisation = new Organisation();

        organisationRepository.persist(organisation);
    }

    public static Adresse registerAdresse() throws SQLException {
        Adresse adresse = new Adresse();

        System.out.println("Entrez le nom de la rue");
        adresse.setNomRue(scanner.nextLine());

        System.out.println("Entrez le numéro de la rue");
        adresse.setNumeroRue(scanner.nextLong());
        scanner.nextLine();

        System.out.println("Entrez le nom de quartier");
        adresse.setNomQuartier(scanner.nextLine());

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

        return adresseRepository.persist(adresse);
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

        isOkay = false;
    }

    private static int getChoice() {
        return scanner.nextInt();
    }
}
