package domaines;

import enums.RegimeSocial;

import java.sql.SQLException;

/**
 * Cette classe représente le modèle d'instantiation d'une organisation
 * @author <a href="https://twitter.com/gioayima">Giovanni Ange Ivane AYIMAMBENWE</a>
 * @version 1.0
 */
public class Organisation extends BaseEntity {
    private String nom;

    private Adresse adresse;

    private RegimeSocial regimeSocial;

    public Organisation(String nom, Adresse adresse, RegimeSocial regimeSocial) throws SQLException {
        super();
        this.nom = nom;
        this.adresse = adresse;
        this.regimeSocial = regimeSocial;
    }

    public Organisation() throws SQLException {
        super();

    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public RegimeSocial getRegimeSocial() {
        return regimeSocial;
    }

    public void setRegimeSocial(RegimeSocial regimeSocial) {
        this.regimeSocial = regimeSocial;
    }
}
