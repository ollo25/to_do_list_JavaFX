package model;

public class Liste {
    private int idListe;
    private String nom;

    public Liste(int idListe, String nom) {
        this.idListe = idListe;
        this.nom = nom;
    }

    public int getIdListe() {
        return idListe;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return nom;
    }
}