module com.example.ttmc2026 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.xml;

    // Tu avais déjà celui-ci pour GameBoard
    opens com.example.ttmc2026.models to com.google.gson;

    // AJOUTE CETTE LIGNE pour la classe Question
    opens com.example.ttmc2026.models.question to com.google.gson;

    opens com.example.ttmc2026.controllers to javafx.fxml;
    exports com.example.ttmc2026.controllers;


    exports com.example.ttmc2026 to javafx.graphics;
    opens com.example.ttmc2026 to javafx.fxml;



    /*opens views to javafx.fxml;
    exports views;
    exports com.example.ttmc2026.test;
    exports;
    opens to;*/
}