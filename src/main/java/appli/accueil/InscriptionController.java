package appli.accueil;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class InscriptionController {

    @FXML
    private TextField emailInscription;

    @FXML
    private Label erreurIn;

    @FXML
    private PasswordField mdpConfirmationInscription;

    @FXML
    private PasswordField mdpInscription;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    void btnInscription(ActionEvent event) {
        if(prenom.getText().isEmpty() || nom.getText().isEmpty() || mdpInscription.getText().isEmpty() || mdpConfirmationInscription.getText().isEmpty() || mdpInscription.getText().isEmpty() || emailInscription.getText().isEmpty()) {
            erreurIn.setText("Veuillez remplir tous les champs");
        }
        else {
            if(mdpConfirmationInscription.getText().equals(mdpInscription.getText())) {
                System.out.println("Bien joué, tu es inscrit");
                System.out.println(prenom.getText());
                System.out.println(nom.getText());
                System.out.println(emailInscription.getText());
                System.out.println(mdpInscription.getText());
            }
            else {
                erreurIn.setText("Les mots de passes ne correspondent pas");
            }
        }

    }

    @FXML
    void btnRedirectionConnexion(ActionEvent event) throws IOException {
        StartApplication.changeScene("accueil/Login", "Connexion");
    }

}
