package enums;

public enum RegimeSocial {

    SA("SA"),
    SAS("SAS"),
    ASSOCIATION("ASSOCIATION");

    private String libelle;

    RegimeSocial(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }
}
