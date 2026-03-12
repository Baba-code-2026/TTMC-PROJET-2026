module com.example.ttmc2026 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens com.example.ttmc2026 to javafx.fxml;
    opens modele to com.google.gson;
    opens modele.question to com.google.gson;

    exports com.example.ttmc2026;
    exports test;
}