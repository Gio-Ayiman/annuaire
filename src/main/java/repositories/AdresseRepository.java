package repositories;

import database.Database;
import domaines.Adresse;
import enums.TypeAdresse;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdresseRepository {
    public void persist(Adresse adresse) throws SQLException {
        String query = "INSERT INTO adresses " +
                "(nom_rue, numero_rue, nom_quartier, type_adresse) " +
                "VALUES " +
                "('" + adresse.getNomRue() + "'," +
                "'" + adresse.getNumeroRue() + "'," +
                "'" + adresse.getNomQuartier() + "'," +
                "'" + adresse.getTypeAdresse() + "');";

        Database.executeQuery(query);
    }

    public Adresse findById(long id) throws SQLException {
        Adresse adresse = new Adresse();

        String query = "SELECT id, nom_rue, numero_rue, nom_quartier, type_adresse " +
                "FROM adresses WHERE id = " + id;

        ResultSet result = Database.executeQuery(query);

        if (result.next()) {
            adresse.setId(result.getLong("id"));
            adresse.setNomRue(result.getString("nom_rue"));
            adresse.setNumeroRue(result.getLong("numero_rue"));
            adresse.setNomQuartier(result.getString("nom_quartier"));
            adresse.setTypeAdresse(TypeAdresse.valueOf(result.getString("type_adresse")));
        }

        return adresse;
    }
}
