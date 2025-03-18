package appli.accueil;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Hyperlink;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import repository.UtilisateurRepository;
import model.Utilisateur;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField emailCo;

    @FXML
    private Label erreurCo;

    @FXML
    private PasswordField mdpCo;
    @FXML
    private Hyperlink redirection;

    @FXML
    void btnConnexion(ActionEvent event) {
        if(emailCo.getText().isEmpty() || mdpCo.getText().isEmpty()) {
            erreurCo.setText("Veuillez remplir tous les champs");
        }
        else {
            UtilisateurRepository utilisateurRepository = new UtilisateurRepository();
            Utilisateur infoUser = utilisateurRepository.recupererUserParEmail(emailCo.getText());
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            boolean reponseVerif = encoder.matches(mdpCo.getText(),infoUser.getMdp());
            if(reponseVerif) {
                System.out.println("Vous etes connecté");
            }
            else {
                erreurCo.setText("Les informations saisies sont incorrects");
            }
        }
    }

    @FXML
    void btnRedirectionInscription(ActionEvent event) throws IOException {
        StartApplication.changeScene("accueil/Inscription", "Inscription");
    }

}
