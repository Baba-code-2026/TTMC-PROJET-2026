module com.example.ttmc2026 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.xml;

    // Tu avais déjà celui-ci pour GameBoard
    opens models to com.google.gson;

    // AJOUTE CETTE LIGNE pour la classe Question
    opens models.question to com.google.gson;

    opens controllers to javafx.fxml;
    exports controllers;


    exports Base to javafx.graphics;
    opens Base to javafx.fxml;



    /*opens views to javafx.fxml;
    exports views;
    exports test;
    exports;
    opens to;*/
}