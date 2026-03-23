module com.example.ttmc2026 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.xml;

    /* Ouverture du package principal à JavaFX.
     * Cela permet à JavaFX de charger correctement les contrôleurs placés dans ce package via les fichiers FXML. */
    opens com.example.ttmc2026 to javafx.fxml;

    /* Ouverture du package des contrôleurs à JavaFX.
     * Les fichiers FXML utilisent ces contrôleurs, donc JavaFX doit pouvoir y accéder par réflexion. */
    opens com.example.ttmc2026.controllers to javafx.fxml;

    /* Ouverture des packages utilisés par Gson.
     * Gson lit les objets Java à partir des fichiers JSON. Il a donc besoin d'un accès par réflexion à ces classes. */
    opens com.example.ttmc2026.models to com.google.gson;
    opens com.example.ttmc2026.models.question to com.google.gson;

    /* Export des packages du projet.
     * Cela rend les classes publiques accessibles si nécessaire depuis l'extérieur du module. */
    exports com.example.ttmc2026;
    exports com.example.ttmc2026.controllers;
    exports com.example.ttmc2026.models;
    exports com.example.ttmc2026.models.entity;
    exports com.example.ttmc2026.models.question;
    exports com.example.ttmc2026.models.state;
}