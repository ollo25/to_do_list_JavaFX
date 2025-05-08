package appli.accueil.mdpGestion;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import model.Utilisateur;
import repository.UtilisateurRepository;
import session.SessionUtilisateur;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;


public class ModifMdpUserController {
    private String emailConcerne;

    @FXML
    private PasswordField mdpActuel;

    @FXML
    private PasswordField nvMdp;

    @FXML
    private PasswordField nvMdpConfirmation;

    @FXML
    private Label erreur;

    @FXML
    void btnModifierMdp(ActionEvent event) {
        if(nvMdp.getText().isEmpty()||mdpActuel.getText().isEmpty()||nvMdpConfirmation.getText().isEmpty()) {
            erreur.setVisible(true);
            erreur.setText("Veuillez remplir tous les champs");
        }
        else {
            SessionUtilisateur utilisateur = SessionUtilisateur.getInstance();
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            boolean reponseVerif = encoder.matches(mdpActuel.getText(),utilisateur.getUtilisateur().getMdp());
            if(reponseVerif) {
                if(nvMdp.getText().equals(nvMdpConfirmation.getText())) {
                    String mdpCrypteNv = encoder.encode(nvMdp.getText());
                    Utilisateur utilisateurNvMdp = new Utilisateur(utilisateur.getUtilisateur().getEmail(),mdpCrypteNv);
                    UtilisateurRepository utilisateurRepository = new UtilisateurRepository();
                    boolean validation = utilisateurRepository.changerMdpAPartirEmail(utilisateurNvMdp);
                    if(validation) {
                        System.out.println("Modif du mdp reussie");
                    }
                    else {
                        System.out.println("Modif du mdp ratée");
                    }
                }
                else{
                    erreur.setVisible(true);
                    erreur.setText("Les mots de passes ne correspondant pas !");
                }
            }
            else {
                erreur.setVisible(true);
                erreur.setText("Le mot de passe actuel n'est pas correct");
            }
        }
    }
    void btnRetour(ActionEvent event) throws IOException {
        StartApplication.changeScene("accueil/ModifUser", "Modification de l'utilisateur");
    }
}
