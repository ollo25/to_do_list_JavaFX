package appli.accueil;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import model.Utilisateur;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import repository.UtilisateurRepository;

import java.io.IOException;

public class InscriptionController {
    private UtilisateurRepository userRepo = new UtilisateurRepository();

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
    void btnInscription(ActionEvent event) throws IOException{
        if (prenom.getText().isEmpty() || nom.getText().isEmpty() || mdpInscription.getText().isEmpty() || mdpConfirmationInscription.getText().isEmpty() || mdpInscription.getText().isEmpty() || emailInscription.getText().isEmpty()) {
            erreurIn.setText("Veuillez remplir tous les champs");
        } else if (!mdpConfirmationInscription.getText().equals(mdpInscription.getText())) {
            erreurIn.setText("Les mots de passe ne correspondent pas");
        } else if (mdpConfirmationInscription.getText().equals(mdpInscription.getText())) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String mdpCrypte = encoder.encode(mdpInscription.getText());
            String role="user";
            if(userRepo.nbUser()==0){
                 role="admin";
            }
            Utilisateur user = new Utilisateur(nom.getText(), prenom.getText(), emailInscription.getText(), mdpCrypte, role);
            boolean verifInscription = userRepo.inscription(user);
            System.out.println("Inscription reussi");
            StartApplication.changeScene("accueil/Login", "Connexion");
        }


    }


    @FXML
    void btnRedirectionConnexion(ActionEvent event) throws IOException {
        StartApplication.changeScene("accueil/Login", "Connexion");
    }
}


