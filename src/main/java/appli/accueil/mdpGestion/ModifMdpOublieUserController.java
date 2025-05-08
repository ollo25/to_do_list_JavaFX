package appli.accueil.mdpGestion;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import model.Utilisateur;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import repository.UtilisateurRepository;

import java.io.IOException;

public class ModifMdpOublieUserController {
    private String emailConcerne;
    @FXML
    private Label erreur;

    @FXML
    private PasswordField nvMdp;

    @FXML
    private PasswordField nvMdpConfirmation;

    @FXML
    void btnModifierMdp(ActionEvent event) throws IOException {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(nvMdp.getText().equals(nvMdpConfirmation.getText())) {
            String mdpCrypteNv = encoder.encode(nvMdp.getText());
            Utilisateur utilisateurNvMdp = new Utilisateur(emailConcerne,mdpCrypteNv);
            UtilisateurRepository utilisateurRepository = new UtilisateurRepository();
            boolean validation = utilisateurRepository.changerMdpAPartirEmail(utilisateurNvMdp);
            if(validation) {
                StartApplication.changeScene("accueil/AccueilAdmin", "Accueil Admin");
            }
            else {
                erreur.setText("Erreur de la modification de mot de passe, veuillez réessayer");
            }
        }
        else{
            erreur.setVisible(true);
            erreur.setText("Les mots de passes ne correspondant pas !");
        }
    }
    public void initData(String email) {
        emailConcerne = email;
    }
    void btnRetour(ActionEvent event) throws IOException {
        StartApplication.changeScene("accueil/Login", "Connexion");
    }
}
