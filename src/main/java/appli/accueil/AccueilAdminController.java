package appli.accueil;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Utilisateur;
import repository.UtilisateurRepository;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class AccueilAdminController implements Initializable {
    UtilisateurRepository userRepo = new UtilisateurRepository();
    @FXML
    private TableView<Utilisateur> tableauUtilisateur;

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
}

