module com.example.ttmc2026 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.xml;

    // Tu avais déjà celui-ci pour GameBoard
    opens modele to com.google.gson;

    // AJOUTE CETTE LIGNE pour la classe Question
    opens modele.question to com.google.gson;

    opens vue to javafx.fxml;
    exports vue;
    exports test;
}