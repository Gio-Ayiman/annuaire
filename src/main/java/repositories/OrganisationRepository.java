package repositories;

import domaines.Organisation;
import enums.RegimeSocial;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static database.Database.executeQuery;
public class OrganisationRepository {

    private static final AdresseRepository adresseRepository = new AdresseRepository();
    public void persist(Organisation organisation) throws SQLException {
        String query = "INSERT INTO organisations " +
                       "(nom, adresse_id, regime_social) " +
                       "VALUES " +
                       "('" + organisation.getNom() + "'," +
                       "'" + organisation.getAdresse().getId() + "'," +
                       "'" + organisation.getRegimeSocial() + "');";

        executeQuery(query);
    }

    public Organisation findById(long id) throws SQLException {
        Organisation organisation = new Organisation();

        String query = "SELECT * FROM organisations WHERE id = " + id;

        try (ResultSet result = executeQuery(query)){
            if (result.next()) {
                organisation.setId(result.getLong("id"));
                organisation.setAdresse(adresseRepository.findById(result.getLong("adresse_id")));
                organisation.setRegimeSocial(RegimeSocial.valueOf(result.getString("regime_social")));
            }
        }

        return organisation;
    }

    public List<Organisation> findAll() throws SQLException {
        List<Organisation> organisations = new ArrayList<>();

        String query = "SELECT * FROM organisations;";
        ResultSet result = executeQuery(query);

        while (result.next()) {
            Organisation organisation = new Organisation();

            organisation.setId(result.getLong("id"));
            organisation.setAdresse(adresseRepository.findById(result.getLong("adresse_id")));
            organisation.setRegimeSocial(RegimeSocial.valueOf(result.getString("regime_social")));

            organisations.add(organisation);
        }

        return organisations;
    }
}
