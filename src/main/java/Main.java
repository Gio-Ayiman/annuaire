import database.Database;
import domaines.Adresse;
import domaines.Personne;
import enums.TypeAdresse;
import repositories.AdresseRepository;
import util.Menu;

import java.sql.SQLException;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) throws SQLException {
        Database.connect();
/*        Database.createPersonneTable();
        Database.createTypeAdresseTable();
        Database.createAdresseTable();
        Database.createRegimeSocialTable();
        Database.createOrganisationTable();*/

//        Personne giovanni = new Personne("AYIMAMBENWE", "Giovanni", LocalDate.of(2001, 8, 07), null);
//        Menu.registerPersonne(giovanni);

       /* Adresse adresse1 = new Adresse("Fidele Castro", 179L, "Glass", TypeAdresse.RESIDENCE);
        AdresseRepository adresseRepository = new AdresseRepository();

        adresseRepository.persist(adresse1);*/

        Menu.executeMenu();

//        System.out.println("-------------" + adresse);
    }
}
