module com.example.ttmc2026 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.xml;

    // Tu avais déjà celui-ci pour GameBoard
    opens models to com.google.gson;

    // AJOUTE CETTE LIGNE pour la classe Question
    opens models.question to com.google.gson;

    opens vue to javafx.fxml;
    exports vue;
    exports test;
    exports;
    opens to
}