package appli.accueil;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import session.SessionUtilisateur;

import java.io.IOException;

public class AccueilUserController {

    @FXML
    private Button ConsulterListe;

    @FXML
    private Button editerProfil;

    @FXML
    void btnConsulterListe(ActionEvent event) throws IOException {
        StartApplication.changeScene("accueil/ConsultationListeUser", "Accueil des Listes");
    }

    @FXML
    void btnEditionProfil(ActionEvent event) throws IOException {
        StartApplication.changeScene("accueil/ModifUser", "Modification de l'utilisateur");
    }
    @FXML
    void btnDeconnexion(ActionEvent event) throws IOException {
        SessionUtilisateur utilisateur = SessionUtilisateur.getInstance();
        utilisateur.deconnecter();
        StartApplication.changeScene("accueil/Login","Page de Connexion");
    }


}
