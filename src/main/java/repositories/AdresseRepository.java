package repositories;

import database.Database;
import domaines.Adresse;
import enums.TypeAdresse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static database.Database.executeQuery;

public class AdresseRepository {
    public Adresse persist(Adresse adresse) throws SQLException {

        String query = "INSERT INTO adresses " +
                "(id, nom_rue, numero_rue, nom_quartier, type_adresse) " +
                "VALUES " +
                       "('" + adresse.getId() + "'," +
                       "'" + adresse.getNomRue() + "'," +
                "'" + adresse.getNumeroRue() + "'," +
                "'" + adresse.getNomQuartier() + "'," +
                "'" + adresse.getTypeAdresse() + "');";

        executeQuery(query);

        return adresse;
    }

    public Adresse findById(long id) throws SQLException {
        Adresse adresse = new Adresse();

        String query = "SELECT id, nom_rue, numero_rue, nom_quartier, type_adresse " +
                "FROM adresses WHERE id = " + id;

        try (ResultSet result = executeQuery(query)) {

            if (result.next()) {
                adresse.setId(result.getLong("id"));
                adresse.setNomRue(result.getString("nom_rue"));
                adresse.setNumeroRue(result.getLong("numero_rue"));
                adresse.setNomQuartier(result.getString("nom_quartier"));
                adresse.setTypeAdresse(TypeAdresse.valueOf(result.getString("type_adresse")));
            }
        }

        return adresse;
    }

    public List<Adresse> findAll() throws SQLException {
        List<Adresse> adresses = new ArrayList<>();

        String query = "SELECT * from adresses;";

        ResultSet result = executeQuery(query);

        while (result.next()) {
            Adresse adresse = new Adresse();

            adresse.setId(result.getInt("id"));
            adresse.setNomRue(result.getString("nom_rue"));
            adresse.setNumeroRue(result.getLong("numero_rue"));
            adresse.setNomQuartier(result.getString("nom_quartier"));
            adresse.setTypeAdresse(TypeAdresse.valueOf(result.getString("type_adresse")));

            adresses.add(adresse);
        }

        return adresses;
    }
}
