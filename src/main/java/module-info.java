module appli.todolistfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens appli.accueil to javafx.fxml;
    exports appli.accueil;
    exports appli;
    opens appli to javafx.fxml;
}