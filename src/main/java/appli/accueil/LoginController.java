package appli.accueil;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    public TextField email;
    public PasswordField mdp;

    @FXML
    protected void connexion() {
        String emailCo = email.getText();
        String mdpCo = mdp.getText();
        System.out.println(emailCo);
        System.out.println(mdpCo);
    }


}