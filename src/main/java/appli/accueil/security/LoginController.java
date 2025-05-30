package appli.accueil.security;

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
import session.SessionUtilisateur;
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
    private Hyperlink motDePasseOublie;

    @FXML
    void btnConnexion(ActionEvent event)throws IOException {
        if(emailCo.getText().isEmpty() || mdpCo.getText().isEmpty()) {
            erreurCo.setText("Veuillez remplir tous les champs");
        }
        else {
            UtilisateurRepository utilisateurRepository = new UtilisateurRepository();
            Utilisateur infoUser = utilisateurRepository.recupererUserParEmail(emailCo.getText());
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            boolean reponseVerif = encoder.matches(mdpCo.getText(),infoUser.getMdp());
            if(reponseVerif) {
                System.out.println("Connexion réussie pour : " + infoUser.getNom());
                SessionUtilisateur.getInstance().sauvegardeSession(infoUser);
                erreurCo.setVisible(false);
                Utilisateur utilisateurActuel = SessionUtilisateur.getInstance().getUtilisateur();
                if (utilisateurActuel != null) {
                    System.out.println("Utilisateur connecté : " + utilisateurActuel.getNom());
                }
                if(utilisateurActuel.getRole().equals("admin")) {
                    StartApplication.changeScene("accueil/AccueilAdmin", "Accueil Administrateur");
                }
                else {
                    StartApplication.changeScene("accueil/AccueilUser", "Accueil");
                }

            }
            else {
                erreurCo.setVisible(true);
                erreurCo.setText("Les informations saisies sont incorrects");
            }
        }
    }

    @FXML
    void btnRedirectionInscription(ActionEvent event) throws IOException {
        StartApplication.changeScene("accueil/Inscription", "Inscription");
    }
    @FXML
    void btnMdpOublie(ActionEvent event) throws IOException {
        StartApplication.changeScene("accueil/MotDePasseOublie", "Mot de passe oublié");
    }
}
