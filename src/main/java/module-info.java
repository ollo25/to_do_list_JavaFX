module appli.todolistfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jdk.jshell;
    requires spring.security.crypto;
    requires java.desktop;
    requires java.mail;


    opens appli.accueil to javafx.fxml;
    exports appli.accueil;
    exports appli;
    opens appli to javafx.fxml;
    opens model to javafx.base;
    exports appli.accueil.admin;
    opens appli.accueil.admin to javafx.fxml;
    exports appli.accueil.user;
    opens appli.accueil.user to javafx.fxml;
    exports appli.accueil.user.tache;
    opens appli.accueil.user.tache to javafx.fxml;
    exports appli.accueil.user.liste;
    opens appli.accueil.user.liste to javafx.fxml;
    exports appli.accueil.mdpGestion;
    opens appli.accueil.mdpGestion to javafx.fxml;
    exports appli.accueil.security;
    opens appli.accueil.security to javafx.fxml;
}