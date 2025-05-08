package appli.accueil.user.tache;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Liste;
import repository.TacheRepository;

import java.io.IOException;

public class AjoutTacheController {

    private Liste listeConcernee;

    @FXML
    private TextField nomTacheField;

    @FXML
    private ComboBox<String> etatCombo;

    @FXML
    private Button ajouterTache;

    @FXML
    private Label erreur;

    public void initData(Liste liste) {
        this.listeConcernee = liste;
        // Préremplir le ComboBox avec des états possibles
        etatCombo.getItems().addAll("à faire", "en cours", "terminée");
        etatCombo.getSelectionModel().selectFirst(); // Valeur par défaut
    }

    @FXML
    void btnAjouterTache(ActionEvent event) throws IOException {
        String nom = nomTacheField.getText().trim();
        String etatString = etatCombo.getValue();
        int etat = mapEtatToInt(etatString);

        if (nom.isEmpty()) {
            erreur.setText("Le champ nom est vide !");
            return;
        }

        TacheRepository tacheRepo = new TacheRepository();
        tacheRepo.ajouterTache(nom, etat, listeConcernee.getIdListe());

        System.out.println("Tâche ajoutée avec succès !");
        StartApplication.changeScene("accueil/ConsultationTacheListeUser", "Tâches");
        ConsultationTacheListeUserController controller =
                (ConsultationTacheListeUserController) StartApplication.getControllerFromStage();
        controller.initData(listeConcernee);
    }
    private int mapEtatToInt(String etat) {
        return switch (etat) {
            case "à faire" -> 0;
            case "en cours" -> 1;
            case "terminée" -> 2;
            default -> throw new IllegalArgumentException("État inconnu : " + etat);
        };
    }
}