package appli.accueil.user.liste;

import appli.StartApplication;
import appli.accueil.user.tache.ConsultationTacheListeUserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Liste;
import model.Utilisateur;
import repository.ListeRepository;
import session.SessionUtilisateur;

import java.io.IOException;
import java.util.ArrayList;

public class ConsultationListeUserController {
    private Liste listeSelected = null;

    @FXML
    private TableView<Liste> tableListe;

    private ListeRepository listeRepo = new ListeRepository();

    private Utilisateur utilisateurConnecte;
    @FXML
    private Button ajouterListe;
    @FXML
    private Button modifierListe;
    @FXML
    private Button supprimerListe;


    private void afficherListesUtilisateur(int idUtilisateur) {
        tableListe.getItems().clear();
        ArrayList<Liste> listes = listeRepo.getListesParId(idUtilisateur);
        tableListe.getItems().addAll(listes);
    }

    @FXML
    void btnAjouterListe(ActionEvent event) throws IOException {
        StartApplication.changeScene("accueil/AjoutListe", "Ajouter une liste");
    }

    @FXML
    void selected(MouseEvent event) throws IOException {
        listeSelected = tableListe.getSelectionModel().getSelectedItem();
        System.out.println(listeSelected);
        if (event.getClickCount() == 2) {
            if (listeSelected != null) {
                StartApplication.changeScene("accueil/ConsultationTacheListeUser", "Tache");
                ConsultationTacheListeUserController controller = (ConsultationTacheListeUserController)
                        StartApplication.getControllerFromStage();
                controller.initData(listeSelected);
            }
        } else {
            supprimerListe.setVisible(true);
            supprimerListe.setDisable(false);
            modifierListe.setVisible(true);
            modifierListe.setDisable(false);
        }
    }

    @FXML
    void btnSupprimerListe(ActionEvent event) {
        listeRepo.deleteListe(listeSelected.getIdListe());
        tableListe.getItems().remove(listeSelected);
        listeSelected = null;
        supprimerListe.setDisable(true);
    }

    @FXML
    void btnModifierListe(ActionEvent event) throws IOException {
        StartApplication.changeScene("accueil/ModifListe", "Modification du nom de la liste");
        ModifListeController controller = (ModifListeController) StartApplication.getControllerFromStage();
        controller.initData(listeSelected);
    }
    @FXML
    public void initialize() {
        String[][] colonnes = {
                {"Nom de la liste", "nom"}
        };
        for (String[] colonne : colonnes) {
            TableColumn<Liste, String> maCol = new TableColumn<>(colonne[0]);
            maCol.setCellValueFactory(
                    new PropertyValueFactory<Liste, String>(colonne[1]));
            tableListe.getColumns().add(maCol);
        }
        SessionUtilisateur utilisateur = SessionUtilisateur.getInstance();
        ArrayList<Liste> infoUsers = listeRepo.getListesParId(utilisateur.getUtilisateur().getIdUser());
        tableListe.getItems().addAll(infoUsers);
    }

}