package appli.accueil.user.tache;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Liste;
import model.Tache;
import repository.TacheRepository;

import java.io.IOException;
import java.util.ArrayList;

public class ConsultationTacheListeUserController {
    private Liste listeConcerne;
    private Tache tacheSelected = null;

    private TacheRepository tacheRepo = new TacheRepository();

    @FXML
    private Button AjouterTache;

    @FXML
    private Button modifierTache;

    @FXML
    private Button supprimerTache;

    @FXML
    private TableView<Tache> tableTache;

    @FXML
    private Label textPresentation;
    @FXML
    private Label erreur;

    // Affiche les tâches associées à la liste
    private void afficherTaches() {
        tableTache.getItems().clear();
        ArrayList<Tache> taches = tacheRepo.getTachesParListeId(listeConcerne.getIdListe());
        tableTache.getItems().addAll(taches);
    }

    @FXML
    void btnAjouterTache(ActionEvent event) throws IOException {
        StartApplication.changeScene("accueil/AjoutTache", "Ajouter une tâche");
        AjoutTacheController controller = (AjoutTacheController) StartApplication.getControllerFromStage();
        controller.initData(listeConcerne);
    }

    @FXML
    void btnModifierTache(ActionEvent event) throws IOException {
        if (tacheSelected != null) {
            StartApplication.changeScene("accueil/ModifTache", "Modifier la tâche");
            ModifTacheController controller = (ModifTacheController) StartApplication.getControllerFromStage();
            controller.initData(tacheSelected);
        }
    }

    @FXML
    void btnSupprimerTache(ActionEvent event) {
        if (tacheSelected != null) {
            tacheRepo.supprimerTache(tacheSelected.getIdTache());
            tableTache.getItems().remove(tacheSelected);
            tacheSelected = null;
            modifierTache.setDisable(true);
            supprimerTache.setDisable(true);
        }
    }

    @FXML
    void selected(MouseEvent event) {
        tacheSelected = tableTache.getSelectionModel().getSelectedItem();
        if (tacheSelected != null) {
            modifierTache.setDisable(false);
            supprimerTache.setDisable(false);
        }
    }

    public void initData(Liste liste) {
        if (erreur != null) {
            erreur.setText("");
        }
        this.listeConcerne = liste;
        this.textPresentation.setText("Votre liste : " + liste.getNom());
        afficherTaches();
    }

    @FXML
    public void initialize() {
        String[][] colonnes = {
                {"Nom de la tâche", "nom"},
                {"État", "etat"}
        };
        for (String[] colonne : colonnes) {
            TableColumn<Tache, String> col = new TableColumn<>(colonne[0]);
            col.setCellValueFactory(new PropertyValueFactory<>(colonne[1]));
            tableTache.getColumns().add(col);
        }

        modifierTache.setDisable(true);
        supprimerTache.setDisable(true);
    }
}