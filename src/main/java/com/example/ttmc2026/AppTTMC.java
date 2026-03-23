package com.example.ttmc2026;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import com.example.ttmc2026.models.question.Theme;

public class AppTTMC extends Application {
    public void start(Stage stage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/GameBoardView.fxml"));
    }
    public static void main(String[] args) { launch(args); }

    public Theme readQuestion(){
        return null;
    }
    //sss
}
