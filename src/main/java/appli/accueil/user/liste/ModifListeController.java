package appli.accueil.user.liste;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Liste;
import repository.ListeRepository;

import java.io.IOException;

public class ModifListeController {
    Liste liste;
    @FXML
    private Label erreur;

    @FXML
    private Button modifierListe;

    @FXML
    private TextField nomField;

    @FXML
    void btnModifierListe(ActionEvent event) throws IOException {
        if(nomField.getText().isEmpty()) {
            erreur.setVisible(true);
            erreur.setText("Veuillez remplir le nom de la liste");
        } else {
            ListeRepository listeRepo = new ListeRepository();
            Liste listeModif = new Liste(liste.getIdListe(), nomField.getText());
            boolean verif = listeRepo.mettreAJourListe(listeModif);
            if(verif) {
                erreur.setVisible(false);
                StartApplication.changeScene("accueil/ConsultationListeUser", "Vos Listes");
            } else {
                erreur.setVisible(true);
                erreur.setText("Erreur de modification");
            }
        }
    }
    public void initData(Liste listeSelected) {
        liste = listeSelected;
        nomField.setText(listeSelected.getNom());

    }
}
