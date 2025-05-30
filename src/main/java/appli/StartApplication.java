package appli;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class StartApplication extends Application {
    private static Stage mainStage;

    @Override
    public void start(Stage stage) throws IOException {
        mainStage = stage;
        FXMLLoader fxmlLoader = new
                FXMLLoader(StartApplication.class.getResource("accueil/loginView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        mainStage.setTitle("Hello!");
        mainStage.setScene(scene);
        mainStage.show();
    }
    public static void changeScene(String nomDuFichierFxml , String nomAfficherFichier) throws IOException {
        StartApplication.changeScene(nomDuFichierFxml, nomAfficherFichier,false);
    }

    public static void changeScene(String nomDuFichierFxml , String nomAfficherFichier,boolean fullSreen) throws IOException {
        FXMLLoader fxmlLoader = new
                FXMLLoader(StartApplication.class.getResource(nomDuFichierFxml + "View.fxml" ));
        Scene scene;
        if (fullSreen) {
            Rectangle2D bounds = Screen.getPrimary().getBounds();
            scene = new Scene(fxmlLoader.load(), bounds.getWidth(), bounds.getHeight());
            mainStage.setX(0);
            mainStage.setY(0);
        }else{
            scene = new Scene(fxmlLoader.load());
        }
        scene.setUserData(fxmlLoader.getController());

        mainStage.setScene(scene);
        mainStage.setTitle(nomAfficherFichier);
    }


    public static Object getControllerFromStage(){
        return mainStage.getScene().getUserData();
    }



    public static void main(String[] args) {
        launch();
    }
}