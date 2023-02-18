package repositories;

import database.Database;
import domaines.Organisation;

import java.sql.SQLException;

public class OrganisationRepository {
    public void persist(Organisation organisation) throws SQLException {
        String query = "INSERT INTO organisations " +
                       "(nom, adresse_id, regime_social) " +
                       "VALUES " +
                       "('" + organisation.getNom() + "'," +
                       "'" + organisation.getAdresse().getId() + "'," +
                       "'" + organisation.getRegimeSocial() + "');";

        Database.executeQuery(query);
    }
}
