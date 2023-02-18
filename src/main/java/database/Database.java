package database;

import java.sql.*;
import java.util.Properties;

/**
 * Cette classe regroupe toutes les méthodes utilitaires pour la base de donnée
 * @author <a href="https://twitter.com/gioayima">Giovanni Ange Ivane AYIMAMBENWE</a>
 * @version 1.0
 */
public class Database {

    private final static String url = "jdbc:postgresql://localhost:5432/annuairedb";
    private static Properties props;
    private static Connection connection = null;

    /**
     * Cette méthode permet de se connecter à la base de donnée
     * @throws SQLException Si la connexion échoue
     * @since 1.0
     */
    public static void connect() throws SQLException {
        props = new Properties();
        props.setProperty("user", "giovanni");
        props.setProperty("password", "giovanni");

        try {
            connection = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            // TODO Gestion de l'exception
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        System.out.println("Connexion à la base de donnée effectué avec succès");

    }

    /**
     * Cette méthode permet d'executer une requête SQL dans la base de donnée
     * @param  query Le nom de table à créer
     * @throws SQLException Si la requête échoue
     * @since 1.0
     */
    public static ResultSet executeQuery(String query) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            return statement.execute(query) ? statement.getResultSet() : null;
        } catch (SQLException e) {
            // TODO Gestion de l'exception
            System.err.println(e.getMessage());
            return null;
        }

    }

    public static void createPersonneTable() throws SQLException {
        String query = "CREATE TABLE personnes " +
                "(id int PRIMARY KEY," +
                "nom VARCHAR(50) NOT NULL, " +
                "prenom VARCHAR(50) NOT NULL," +
                "date_naissance DATE NOT NULL, " +
                "adresse_id INT NOT NULL, " +
                "FOREIGN KEY (adresse_id) REFERENCES adresses(id));";


        executeQuery(query);
    }

    public static void createTypeAdresseTable() throws SQLException {
        String query = "CREATE TYPE type_adresse AS ENUM " +
                "('RESIDENCE', 'PROFESSIONNELLE');";

        executeQuery(query);
    }

    public static void createAdresseTable() throws SQLException {
        String query = "CREATE TABLE adresses " +
                "(id int PRIMARY KEY," +
                "nom_rue VARCHAR(50) NOT NULL," +
                "numero_rue VARCHAR(50) NOT NULL," +
                "nom_quartier VARCHAR(50) NOT NULL," +
                "type_adresse TYPE_ADRESSE NOT NULL);";

        executeQuery(query);
    }

    public static void createRegimeSocialTable() throws SQLException {
        String query = "CREATE TYPE regime_social AS ENUM " +
                "('SA', 'SAS', 'ASSOCIATION');";

        executeQuery(query);
    }

    public static void createOrganisationTable() throws SQLException {
        String query = "CREATE TABLE organisations " +
                "(id int PRIMARY KEY," +
                "nom VARCHAR(50) NOT NULL," +
                "adresse_id INT NOT NULL," +
                "regime_social REGIME_SOCIAL NOT NULL,"+
                "FOREIGN KEY (adresse_id) REFERENCES adresses(id));";

        executeQuery(query);
    }

    /**
     * Crée une séquence de {@code int} pour incrémenter l'{@code id} de chaque table
     * @throws SQLException Si la requête échoue
     * @since 1.0
     */
    public static void createSequence() throws SQLException {
        String query = "CREATE SEQUENCE id_sequence INCREMENT 1 START 1 MINVALUE 1;";

        executeQuery(query);
    }

    /**
     * Récupère la prochaine valeur de la séquence
     * @return {@code  int} La prochaine valeur de la séquence
     * @throws SQLException Si la requête échoue
     * @since 1.0
     */
    public static int nextVal() throws SQLException {
        String query = "SELECT nextval('id_sequence')";

        ResultSet result = Database.executeQuery(query);

        return result.next() ? result.getInt(1) : null;
    }
}
