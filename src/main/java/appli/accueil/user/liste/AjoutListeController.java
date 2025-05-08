package appli.accueil.user.liste;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import repository.ListeRepository;
import session.SessionUtilisateur;

import java.io.IOException;

public class AjoutListeController {

    @FXML
    private Button ajouterListe;

    @FXML
    private TextField nomField;
    @FXML
    private Label erreur;
    @FXML
    void btnAjouterListe(ActionEvent event) throws IOException {
        String nom = nomField.getText().trim();

        if (nom.isEmpty()) {
            erreur.setText("Le champ nom est vide !");
            return;
        }

        ListeRepository listeRepo = new ListeRepository();
        SessionUtilisateur utilisateur = SessionUtilisateur.getInstance();
        listeRepo.ajouterListePourUtilisateur(utilisateur.getUtilisateur().getIdUser(), nom);

        System.out.println("Liste ajoutée avec succès !");
        StartApplication.changeScene("accueil/ConsultationListeUser", "Ajouter une liste");
    }

}
