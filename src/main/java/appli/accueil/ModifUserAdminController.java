package appli.accueil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Utilisateur;

public class ModifUserAdminController {

    @FXML
    private TextField emailNv;

    @FXML
    private Label erreurIn;

    @FXML
    private PasswordField mdpConfirmationNv;

    @FXML
    private PasswordField mdpNv;

    @FXML
    private TextField nomNv;

    @FXML
    private TextField prenomNv;

    @FXML
    void btnModifierUser(ActionEvent event) {

    }

    public void initData(Utilisateur utilisateur) {
        this.userInfo = utilisateur;
        nomNv.setText(utilisateur.getNom());
        prenomNv.setText(utilisateur.getPrenom());
        emailNv.setText(utilisateur.getEmail());
    }
}
