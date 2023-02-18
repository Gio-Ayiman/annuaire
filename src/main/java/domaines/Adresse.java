package domaines;

import enums.TypeAdresse;

public class Adresse extends BaseEntity {
    private String nomRue;
    private Long numeroRue;
    private String nomQuartier;
    private TypeAdresse typeAdresse;

    public Adresse(String nomRue, Long numeroRue, String nomQuartier, TypeAdresse typeAdresse) {
        super();
        this.nomRue = nomRue;
        this.numeroRue = numeroRue;
        this.nomQuartier = nomQuartier;
        this.typeAdresse = typeAdresse;
    }


    public Adresse() {

    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public String getNomRue() {
        return nomRue;
    }

    public void setNomRue(String nomRue) {
        this.nomRue = nomRue;
    }

    public Long getNumeroRue() {
        return numeroRue;
    }

    public void setNumeroRue(Long numeroRue) {
        this.numeroRue = numeroRue;
    }

    public String getNomQuartier() {
        return nomQuartier;
    }

    public void setNomQuartier(String nomQuartier) {
        this.nomQuartier = nomQuartier;
    }

    public TypeAdresse getTypeAdresse() {
        return typeAdresse;
    }

    public void setTypeAdresse(TypeAdresse typeAdresse) {
        this.typeAdresse = typeAdresse;
    }

    @Override
    public String toString() {
        return "Adresse{" +
                "nomRue='" + nomRue + '\'' +
                ", numeroRue=" + numeroRue +
                ", nomQuartier='" + nomQuartier + '\'' +
                ", typeAdresse=" + typeAdresse +
                ", id=" + id +
                '}';
    }
}
