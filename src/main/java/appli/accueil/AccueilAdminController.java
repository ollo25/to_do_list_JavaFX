package appli.accueil;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Utilisateur;
import repository.UtilisateurRepository;
import javafx.scene.input.MouseEvent;
import session.SessionUtilisateur;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class AccueilAdminController implements Initializable {
    private Utilisateur utilisateurSelected = null;
    UtilisateurRepository userRepo = new UtilisateurRepository();
    @FXML
    private TableView<Utilisateur> tableauUtilisateur;

    @FXML
    private Button btnSupprimer;

    @FXML
    private Button btnSwitchRole;

    public void initialize(URL location, ResourceBundle resources) {
        String [][] colonnes = {
                { "Id Utilisateur","idUser" },
                { "Nom","nom" },
                { "Prénom","prenom" },
                { "Email","email" },
                { "Rôle","role" },
        };

        for ( int i = 0 ; i < colonnes.length ; i ++ ){
            //Création de la colonne avec le titre
            TableColumn<Utilisateur,String> maCol = new TableColumn<>(colonnes[i][0]);
            //Ligne permettant la liaison automatique de la cellule avec la propriété
            maCol.setCellValueFactory(
                    new PropertyValueFactory<Utilisateur,String>(colonnes[i][1]));
            //Ajout de la colonne dans notre tableau
            tableauUtilisateur.getColumns().add(maCol);
        }
        ArrayList<Utilisateur> infoUsers = userRepo.recupererToutLesUtilisateurs();
        tableauUtilisateur.getItems().addAll(infoUsers);
    }

    @FXML
    void selected(MouseEvent event) throws IOException {
        utilisateurSelected = tableauUtilisateur.getSelectionModel().getSelectedItem();
        System.out.println(utilisateurSelected + "test");
        if (event.getClickCount() == 2) { // Vérifie si c'est un double-clic
            if (utilisateurSelected != null) {
                System.out.println("test reussite");
                StartApplication.changeScene("accueil/ModifUserAdmin", "Modification d'un Utilisateur");
                ModifUserAdminController controller = (ModifUserAdminController)
                        StartApplication.getControllerFromStage();
                controller.initData(utilisateurSelected);
            }
        }
        else {
            btnSupprimer.setVisible(true);
            btnSupprimer.setDisable(false);
        }
    }

    @FXML
    void supprimerUser(ActionEvent event) {
        userRepo.deleteUser(utilisateurSelected.getEmail());
        tableauUtilisateur.getItems().remove(utilisateurSelected);
        utilisateurSelected = null;
        btnSupprimer.setDisable(true);
    }

    @FXML
    void switchUser(ActionEvent event) {
        userRepo.deleteUser(utilisateurSelected.getEmail());
        tableauUtilisateur.getItems().remove(utilisateurSelected);
        utilisateurSelected = null;
        btnSwitchRole.setDisable(true);
    }
    @FXML
    void btnDeconnexion(ActionEvent event) throws IOException {
        SessionUtilisateur utilisateur = SessionUtilisateur.getInstance();
        utilisateur.deconnecter();
        StartApplication.changeScene("accueil/Login","Page de Connexion");
    }
}

