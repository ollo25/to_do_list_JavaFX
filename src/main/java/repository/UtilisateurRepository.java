package repository;

import database.Database;
import jdk.jshell.execution.Util;
import model.Utilisateur;
import model.UtilisateurListe;

import java.sql.*;

public class UtilisateurRepository {
    private Connection connexion;
    public UtilisateurRepository() {
        this.connexion = Database.getConnexion();
    }
    public void inscription(Utilisateur utilisateur) {
        String sql = "INSERT INTO utilisateur (nom, prenom, email, mdp) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connexion.prepareStatement(sql);
            stmt.setString(1, utilisateur.getNom());
            stmt.setString(2, utilisateur.getPrenom());
            stmt.setString(3, utilisateur.getEmail());
            stmt.setString(4, utilisateur.getMdp());
            stmt.setString(5, utilisateur.getRole());
            stmt.executeUpdate();
            System.out.println("Utilisateur ajouté avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de l'utilisateur : " + e.getMessage());
        }
    }

    public void recupererUserParEmail(String email) {
        String sql = "SELECT * FROM utilisateur WHERE email = ?";
        try {
            PreparedStatement stmt = connexion.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet infoRecup = stmt.executeQuery();
            if (infoRecup.next()) {
                Utilisateur user = new Utilisateur(
                        infoRecup.getInt("id_utilisateur"),
                        infoRecup.getString("nom"),
                        infoRecup.getString("prenom"),
                        infoRecup.getString("email"),
                        infoRecup.getString("mot_de_passe"),
                        infoRecup.getString("role")
                );
            }

        }
        catch (SQLException e){
            System.out.println("Erreur");
        }
    }

    public void connexion(Utilisateur user) {
        String sql = "SELECT * FROM utilisateur WHERE email = ? AND mot_de_passe = ?";
        try {
            PreparedStatement stmt = connexion.prepareStatement(sql);
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getMdp());
            ResultSet infoRecup = stmt.executeQuery();
            if (infoRecup.next()) {
                Utilisateur userCo = new Utilisateur(
                        infoRecup.getInt("id_utilisateur"),
                        infoRecup.getString("nom"),
                        infoRecup.getString("prenom"),
                        infoRecup.getString("email"),
                        infoRecup.getString("mot_de_passe"),
                        infoRecup.getString("role")
                );
                return userCo;
            }

        }
        catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de l'utilisateur : " + e.getMessage());
        }
    }

}
