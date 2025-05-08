package appli.accueil.admin;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Utilisateur;
import repository.UtilisateurRepository;

import java.io.IOException;

public class ModifUserAdminController {
    private Utilisateur utilisateur;

    @FXML
    private TextField emailNv;

    @FXML
    private Label erreurIn;

    @FXML
    private TextField nomNv;

    @FXML
    private TextField prenomNv;

    @FXML
    private Label utilisateurConcerne;

    @FXML
    void btnModifierUser(ActionEvent event) throws IOException {
        if(emailNv.getText().isEmpty() || nomNv.getText().isEmpty() || prenomNv.getText().isEmpty()) {
            erreurIn.setVisible(true);
            erreurIn.setText("Veuillez remplir tous les champs");
        }
        else {
            UtilisateurRepository userRepo = new UtilisateurRepository();
            Utilisateur userModif= new Utilisateur(nomNv.getText(), prenomNv.getText(), emailNv.getText(), utilisateur.getIdUser());
            boolean verif = userRepo.mettreAJourUtilisateur(userModif);
            if(verif) {
                erreurIn.setVisible(false);
                StartApplication.changeScene("accueil/AccueilAdmin", "Accueil Admin");
            }else{
                erreurIn.setVisible(true);
                erreurIn.setText("Erreur de modification");
            }
        }
    }

    public void initData(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
        this.utilisateurConcerne.setText(utilisateur.getEmail());
        nomNv.setText(utilisateur.getNom());
        prenomNv.setText(utilisateur.getPrenom());
        emailNv.setText(utilisateur.getEmail());
    }
    @FXML
    void btnRetour(ActionEvent event) throws IOException {
        StartApplication.changeScene("accueil/AccueilAdmin", "Accueil Admin");
    }
}
