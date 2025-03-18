package appli.accueil;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Utilisateur;
import repository.UtilisateurRepository;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TableauUserController implements Initializable {
    @FXML
    private TableView<Utilisateur> tableView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UtilisateurRepository userRepo = new UtilisateurRepository();
        ArrayList<Utilisateur> infosUser = userRepo.recupererToutLesUtilisateurs();

        String[][] colonnes = {
                {"Id Utilisateur", "idUser"},
                {"Nom", "nom"},
                {"Prénom", "prenom"},
                {"Email", "email"},
                {"Rôle", "role"}
        };
        for (int i = 0; i < colonnes.length; i++) {
            TableColumn<Utilisateur, String> maCol = new TableColumn<>(colonnes[i][0]);
            maCol.setCellValueFactory(
                    new PropertyValueFactory<Utilisateur, String>(colonnes[i][1]));
            tableView.getColumns().add(maCol);
        }
    }
}
