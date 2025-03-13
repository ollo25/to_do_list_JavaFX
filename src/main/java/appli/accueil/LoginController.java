package appli.accueil;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Hyperlink;


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
        String email = emailCo.getText();
        String mdp = mdpCo.getText();
        if(email.isEmpty() || mdp.isEmpty()) {
            erreurCo.setText("Veuillez remplir tous les champs");
        }
        else {
            if(mdp.equals("azerty1234") && email.equals("a@a.a")) {
                System.out.println("Vous etes connecté");
            }
            erreurCo.setText("Les informations saisies sont incorrects");
        }
    }

    @FXML
    void btnRedirectionInscription(ActionEvent event) throws IOException {
        StartApplication.changeScene("accueil/Inscription", "Inscription");
    }

}
