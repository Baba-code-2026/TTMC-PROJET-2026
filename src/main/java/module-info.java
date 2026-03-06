module com.example.ttmc2026 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ttmc2026 to javafx.fxml;
    exports com.example.ttmc2026;
}