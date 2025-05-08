package repository;

import database.Database;
import model.Tache;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TacheRepository {
    private Connection connexion;

    public TacheRepository() {
        this.connexion = Database.getConnexion();
    }

    public ArrayList<Tache> getTachesParListeId(int idListe) {
        ArrayList<Tache> taches = new ArrayList<>();
        String sql = "SELECT id_tache, nom, etat FROM tache WHERE ref_liste = ?";

        try {
            PreparedStatement stmt = connexion.prepareStatement(sql);
            stmt.setInt(1, idListe);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Tache tache = new Tache(
                        rs.getInt("id_tache"),
                        rs.getString("nom"),
                        rs.getInt("etat"),
                        idListe
                );
                taches.add(tache);
            }

            if (taches.isEmpty()) {
                System.out.println("Aucune tâche trouvée pour cette liste.");
            } else {
                System.out.println("Tâches de la liste : " + taches);
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des tâches : " + e.getMessage());
        }

        return taches;
    }

    public void ajouterTache(String nomTache, int etat, int refListe) {
        String sql = "INSERT INTO tache (nom, etat, ref_liste) VALUES (?, ?, ?)";

        try {
            PreparedStatement stmt = connexion.prepareStatement(sql);
            stmt.setString(1, nomTache);
            stmt.setInt(2, etat);
            stmt.setInt(3, refListe);
            stmt.executeUpdate();
            System.out.println("Tâche ajoutée avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de la tâche : " + e.getMessage());
        }
    }

    public boolean mettreAJourTache(Tache tacheModifiee) {
        String sql = "UPDATE tache SET nom = ?, etat = ? WHERE id_tache = ?";

        try {
            PreparedStatement stmt = connexion.prepareStatement(sql);
            stmt.setString(1, tacheModifiee.getNom());
            stmt.setInt(2, tacheModifiee.getEtat());
            stmt.setInt(3, tacheModifiee.getIdTache());
            stmt.executeUpdate();
            System.out.println("Tâche mise à jour avec succès !");
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de la tâche : " + e.getMessage());
        }

        return false;
    }

    public void supprimerTache(int idTache) {
        String sql = "DELETE FROM tache WHERE id_tache = ?";

        try {
            PreparedStatement stmt = connexion.prepareStatement(sql);
            stmt.setInt(1, idTache);
            stmt.executeUpdate();
            System.out.println("Tâche supprimée avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de la tâche : " + e.getMessage());
        }
    }
}