package repository;

import database.Database;

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
    public ArrayList<String> getListesParEmail(String email) {
        ArrayList<String> listes = new ArrayList<>();
        String sql = "SELECT l.nom FROM liste l " +
                "JOIN utilisateur_liste ul ON l.id_liste = ul.ref_liste " +
                "JOIN utilisateur u ON ul.ref_utilisateur = u.id_utilisateur " +
                "WHERE u.email = ?";

        try {
            PreparedStatement stmt = connexion.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                listes.add(rs.getString("nom"));
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
}
