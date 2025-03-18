module appli.todolistfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jdk.jshell;
    requires spring.security.crypto;


    opens appli.accueil to javafx.fxml;
    exports appli.accueil;
    exports appli;
    opens appli to javafx.fxml;
}