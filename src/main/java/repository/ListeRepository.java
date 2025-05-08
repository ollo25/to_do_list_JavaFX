package repository;

import database.Database;
import model.Liste;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class ListeRepository {
    private Connection connexion;
    public ListeRepository() {
        this.connexion = Database.getConnexion();
    }
    public ArrayList<Liste> getListesParId(int id) {
        ArrayList<Liste> listes = new ArrayList<Liste>();
        String sql = "SELECT l.nom, l.id_liste FROM liste l " +
                "JOIN utilisateur_liste ul ON l.id_liste = ul.ref_liste " +
                "WHERE ul.ref_utilisateur=?";

        try {
            PreparedStatement stmt = connexion.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Liste liste = new Liste(rs.getInt("id_liste"), rs.getString("nom"));
                listes.add(liste);
            }

            if (listes.isEmpty()) {
                System.out.println("Aucune liste trouvée pour cet utilisateur.");
            } else {
                System.out.println("Listes de l'utilisateur : " + listes);
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des listes : " + e.getMessage());
        }

        return listes;
    }
    public void deleteListe(int id) {
        String sql = "DELETE FROM liste WHERE id_liste = ?";
        try {
            PreparedStatement stmt = connexion.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Liste supprimée avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de la liste : " + e.getMessage());
        }
    }
    public void ajouterListePourUtilisateur(int idUtilisateur, String nomListe) {

        String sqlListe = "INSERT INTO liste (nom) VALUES (?)";
        String sqlLien = "INSERT INTO utilisateur_liste (ref_utilisateur, ref_liste) VALUES (?, ?)";

        try {
            // le return generated key permet de recup l'id de la liste qu'on creer pour pouvoir ensuite la lier a l'utilisateur via liste_utilisateur
            PreparedStatement stmtListe = connexion.prepareStatement(sqlListe, PreparedStatement.RETURN_GENERATED_KEYS);
            stmtListe.setString(1, nomListe);
            stmtListe.executeUpdate();

            ResultSet rs = stmtListe.getGeneratedKeys();
            if (rs.next()) {
                int idListe = rs.getInt(1);

                PreparedStatement stmtLien = connexion.prepareStatement(sqlLien);
                stmtLien.setInt(1, idUtilisateur);
                stmtLien.setInt(2, idListe);
                stmtLien.executeUpdate();

                   System.out.println("Liste ajoutée et associée à l'utilisateur !");
            } else {
                System.out.println("Erreur : ID de la liste non récupéré.");
            }

        } catch (SQLException e) {
                System.out.println("Erreur lors de l'ajout de la liste : " + e.getMessage());
        }
    }
    public boolean mettreAJourListe(Liste listeModifier) {
        String sql = "UPDATE liste SET nom = ? WHERE id_liste = ?";
        try {
            PreparedStatement stmt = connexion.prepareStatement(sql);
            stmt.setString(1, listeModifier.getNom());
            stmt.setInt(2, listeModifier.getIdListe());

            stmt.executeUpdate();
            System.out.println("Liste mise à jour avec succès !");
            return true;

        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de la liste : " + e.getMessage());
        }

        return false;
    }
}


