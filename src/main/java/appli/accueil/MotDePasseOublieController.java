package appli.accueil;

import appli.StartApplication;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Utilisateur;
import service.EmailService;
import session.SessionUtilisateur;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

import java.io.IOException;

public class MotDePasseOublieController {
    @FXML
    private TextField EmailNvMdp;
    @FXML
    private Label erreur;
    @FXML
    private Label erreurCode;
    @FXML
    private TextField codeEntree;
    private String code;

    @FXML
    private void envoyerCode(ActionEvent event) {
        String email = EmailNvMdp.getText();
        if (email.isEmpty()) {
            erreur.setVisible(true);
            erreur.setText("Veuillez entrer une adresse e-mail.");

        }
        else{
                code = EmailService.genererCode();
                EmailService.envoyerEmail(email, "Réinitialisation de mot de passe", "Votre code de réinitialisation est : " + code);
                erreur.setVisible(true);
                erreur.setText("Code envoyé à : " + email);

        }
    }
    @FXML
    void ValiderCode(ActionEvent event) throws IOException {
        if (codeEntree.getText().isEmpty()) {
            erreurCode.setVisible(true);
            erreurCode.setText("Veuillez entrer le code recu par mail.");
        }
        else {
            if(codeEntree.getText().equals(code)){
                StartApplication.changeScene("accueil/ModifUserAdmin", "Modification d'un Utilisateur");
                ModifUserAdminController controller = (ModifUserAdminController)
                        StartApplication.getControllerFromStage();
                controller.initData(SessionUtilisateur.getInstance().getUtilisateur());
            }
            else {
                erreurCode.setVisible(true);
                erreurCode.setText("Code incorrect");
            }
        }
    }

}