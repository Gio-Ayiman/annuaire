package repositories;

import domaines.Personne;
import enums.TypeAdresse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static database.Database.executeQuery;

public class PersonneRepository {

    private static final AdresseRepository adresseRepository = new AdresseRepository();

    public void persist(Personne personne) throws SQLException {
        String query = "INSERT INTO personnes " +
                       "(id, nom, prenom, date_naissance, adresse_id) " +
                       "VALUES " +
                       "('" + personne.getId() + "'," +
                       "'" + personne.getNom() + "'," +
                       "'" + personne.getPrenom() + "'," +
                       "'" + personne.getDateNaissance() + "'," +
                       personne.getAdresse().getId() + ");";

        executeQuery(query);
    }

    public Personne findById(long id) throws SQLException {
        Personne personne = new Personne();

        String query = "SELECT id, nom, prenom, date_naissance, adresse_id " +
                       "FROM personnes WHERE id = " + id;

        try (ResultSet result = executeQuery(query)) {

            if (result.next()) {
                personne.setId(result.getLong("id"));
                personne.setNom(result.getString("nom"));
                personne.setPrenom(result.getString("prenom"));
                personne.setDateNaissance(result.getDate("date_naissance").toLocalDate());
                personne.setAdresse(adresseRepository.findById(result.getLong("adresse_id")));
            }
        }

        return personne;
    }

    public List<Personne> findAll() throws SQLException {
        List<Personne> personnes = new ArrayList<>();

        String query = "SELECT * FROM personnes;";

        ResultSet result = executeQuery(query);

        while (result.next()) {
            Personne personne = new Personne();

            personne.setId(result.getLong("id"));
            personne.setNom(result.getString("nom"));
            personne.setPrenom(result.getString("prenom"));
            personne.setDateNaissance(result.getDate("date_naissance").toLocalDate());
            personne.setAdresse(adresseRepository.findById(result.getLong("adresse_id")));

            personnes.add(personne);
        }

        return personnes;
    }
}
