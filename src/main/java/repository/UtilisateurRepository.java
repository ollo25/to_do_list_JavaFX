package repository;

import database.Database;
import model.Utilisateur;

import java.awt.image.AbstractMultiResolutionImage;
import java.sql.*;
import java.util.ArrayList;

public class UtilisateurRepository {
    private Connection connexion;
    public UtilisateurRepository() {
        this.connexion = Database.getConnexion();
    }
    public boolean inscription(Utilisateur utilisateur) {
        String sql = "INSERT INTO utilisateur (nom, prenom, email, mot_de_passe,role) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connexion.prepareStatement(sql);
            stmt.setString(1, utilisateur.getNom());
            stmt.setString(2, utilisateur.getPrenom());
            stmt.setString(3, utilisateur.getEmail());
            stmt.setString(4, utilisateur.getMdp());
            stmt.setString(5, utilisateur.getRole());
            stmt.executeUpdate();
            System.out.println("Utilisateur ajouté avec succès !");
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de l'utilisateur : " + e.getMessage());
            return false;
        }
    }

    public Utilisateur recupererUserParEmail(String email) {
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
                return user;
            }

        }
        catch (SQLException e){
            System.out.println("Erreur");
        }
        return null;
    }

    public ArrayList<Utilisateur> recupererToutLesUtilisateurs() {
        String sql = "SELECT * FROM utilisateur";
        ArrayList<Utilisateur> utilisateurs = new ArrayList<>();
        try {
            PreparedStatement stmt = connexion.prepareStatement(sql);
            ResultSet infoRecup = stmt.executeQuery();
            while (infoRecup.next()) {
                Utilisateur user = new Utilisateur(
                        infoRecup.getInt("id_utilisateur"),
                        infoRecup.getString("nom"),
                        infoRecup.getString("prenom"),
                        infoRecup.getString("email"),
                        infoRecup.getString("mot_de_passe"),
                        infoRecup.getString("role")
                );
                utilisateurs.add(user);
            }
        }catch (SQLException e){
            System.out.println("Erreur");
        }
        return utilisateurs;
    }

}
