package appli.accueil;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Utilisateur;
import repository.UtilisateurRepository;
import session.SessionUtilisateur;

import java.io.IOException;

public class ModifUserController {

    @FXML
    private Label erreurIn;

    @FXML
    private TextField nomNv;

    @FXML
    private TextField prenomNv;

    @FXML
    void btnModifierUser(ActionEvent event) {
        if(nomNv.getText().isEmpty() || prenomNv.getText().isEmpty()){
            erreurIn.setVisible(true);
            erreurIn.setText("Veuillez remplir tous les champs");
        }
        else {
            erreurIn.setVisible(false);
            SessionUtilisateur utilisateurActuel = SessionUtilisateur.getInstance();
            Utilisateur userNv = new Utilisateur(nomNv.getText(), prenomNv.getText(),utilisateurActuel.getUtilisateur().getEmail(),utilisateurActuel.getUtilisateur().getIdUser());
            UtilisateurRepository userRepo = new UtilisateurRepository();
            userRepo.mettreAJourUtilisateur(userNv);
            erreurIn.setText("Les informations ont bien été modifiés");
            erreurIn.setVisible(true);
        }
    }

    @FXML
    void btnRedirectionModifMdp(ActionEvent event) throws IOException {
        StartApplication.changeScene("accueil/ModifMdpUser", "Modification du Mot de Passe");
    }
    @FXML
    void btnRetour(ActionEvent event) throws IOException {
        StartApplication.changeScene("accueil/AccueilUser", "Connexion");
    }

    public void initialize() {
        SessionUtilisateur utilisateurActuel = SessionUtilisateur.getInstance();
        nomNv.setText(utilisateurActuel.getUtilisateur().getNom());
        prenomNv.setText(utilisateurActuel.getUtilisateur().getPrenom());
    }
}
