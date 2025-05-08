package appli.accueil.user.tache;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Tache;
import repository.TacheRepository;

import java.io.IOException;

public class ModifTacheController {
    private Tache tache;

    @FXML
    private Label erreur;

    @FXML
    private TextField nomField;

    @FXML
    private ComboBox<String> etatCombo;

    @FXML
    private Button modifierTache;

    @FXML
    void btnModifierTache(ActionEvent event) throws IOException {
        if (nomField.getText().isEmpty()) {
            erreur.setVisible(true);
            erreur.setText("Veuillez remplir le nom de la tâche");
            return;
        }

        String nom = nomField.getText().trim();
        int etat = mapEtatToInt(etatCombo.getValue());

        TacheRepository tacheRepo = new TacheRepository();
        Tache tacheModif = new Tache(tache.getIdTache(), nom, etat, tache.getRefListe());
        boolean verif = tacheRepo.mettreAJourTache(tacheModif);

        if (verif) {
            erreur.setVisible(false);
            StartApplication.changeScene("accueil/ConsultationTacheListeUser", "Tâches");
        } else {
            erreur.setVisible(true);
            erreur.setText("Erreur de modification");
        }
    }

    public void initData(Tache tacheSelected) {
        this.tache = tacheSelected;
        nomField.setText(tache.getNom());
        etatCombo.getItems().addAll("à faire", "en cours", "terminée");
        etatCombo.setValue(mapEtatToString(tache.getEtat()));
    }

    private int mapEtatToInt(String etat) {
        return switch (etat) {
            case "à faire" -> 0;
            case "en cours" -> 1;
            case "terminée" -> 2;
            default -> -1; // ou tu peux lever une exception
        };
    }

    private String mapEtatToString(int etat) {
        return switch (etat) {
            case 0 -> "à faire";
            case 1 -> "en cours";
            case 2 -> "terminée";
            default -> "Inconnu";
        };
    }
}
