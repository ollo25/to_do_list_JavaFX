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
    public boolean deleteUser(String email){
        String sql = "DELETE FROM utilisateur where email = ?";
        try {
            PreparedStatement stmt = connexion.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.executeUpdate();
            System.out.println("Utilisateur supprimé avec succès !");
            return true;
        }catch (SQLException e){
            System.out.println("Erreur");
            return false;
        }
    }
    public boolean mettreAJourUtilisateur(Utilisateur utilisateur) {
        String sql = "UPDATE utilisateur SET nom = ?, prenom = ?, email = ?, mot_de_passe = ?, role = ? WHERE email = ?";

        try {
            PreparedStatement stmt = connexion.prepareStatement(sql);
            stmt.setString(1, utilisateur.getNom());
            stmt.setString(2, utilisateur.getPrenom());
            stmt.setString(3, utilisateur.getEmail());
            stmt.setString(4, utilisateur.getMdp());
            stmt.setString(5, utilisateur.getRole());
            stmt.setString(6, utilisateur.getEmail());

            stmt.executeUpdate();
            System.out.println("Utilisateur mis à jour avec succès !");
            return true;

        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de l'utilisateur : " + e.getMessage());
        }

        return false;
    }
    public int nbUser(){
        String sql = "SELECT count(id_utilisateur) AS nbUser FROM utilisateur";
        try{
            PreparedStatement stmt = connexion.prepareStatement(sql);
            ResultSet infoRecup = stmt.executeQuery();
            if(infoRecup.next()){
                return infoRecup.getInt("nbUser");
            }
        }
        catch (SQLException e){
            System.out.println("Erreur");
        }
        return -1;
    }
}
