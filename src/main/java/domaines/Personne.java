package domaines;

import java.time.LocalDate;

/**
 * Cette classe sert de modèle d'instantiation d'une personne
 * @author <a href="https://twitter.com/gioayima">Giovanni Ange Ivane AYIMAMBENWE</a>
 * @version 1.0
 */
public class Personne extends BaseEntity {
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private Adresse adresse;

    public Personne(String nom, String prenom, LocalDate dateNaissance, Adresse adresse) {
        super();
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.adresse = adresse;
    }

    public Personne() {

    }

    /**
     * Permet de récupérer le nom d'une personne
     * @return Un nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Permet de définir le nom d'une personne
     * @param nom Le nom à définir
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }
}
