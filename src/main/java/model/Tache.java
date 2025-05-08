package model;

public class Tache {
    private int idTache;
    private String nom;
    private int etat;
    private int refListe;

    public Tache(int idTache, String nom, int etat, int refListe) {
        this.idTache = idTache;
        this.nom = nom;
        this.etat = etat;
        this.refListe = refListe;
    }

    public int getIdTache() {
        return idTache;
    }

    public String getNom() {
        return nom;
    }

    public int getEtat() {
        return etat;
    }

    public int getRefListe() {
        return refListe;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public void setRefListe(int refListe) {
        this.refListe = refListe;
    }

    @Override
    public String toString() {
        return "Tache{id=" + idTache + ", nom='" + nom + "', etat='" + etat + "', refListe=" + refListe + "}";
    }
}