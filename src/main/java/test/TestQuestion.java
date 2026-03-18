package test;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modele.question.Theme;
import java.util.ArrayList;
import java.util.Optional;

public class TestQuestion extends Application {

    private int totalScore = 0;
    private Label scoreLabel;
    private ArrayList<Theme> themes;

    @Override
    public void start(Stage stage) {

        themes = Theme.initAllThemes();

        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: white; -fx-padding: 20;");

        // 2. Initialisation du Label de score
        scoreLabel = new Label("Score: " + totalScore);
        scoreLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Button btnJoueur1 = new Button("Poser une question");
        btnJoueur1.setOnAction(e -> poserQuestion());

        HBox quest = new HBox(20, btnJoueur1);
        quest.setAlignment(Pos.CENTER);
        root.getChildren().addAll(scoreLabel, quest);

        Scene scene = new Scene(root, 400, 400);
        stage.setTitle("Interface de Jeu");
        stage.setScene(scene);
        stage.show();
    }

    public void poserQuestion() {

        ChoiceDialog<Theme> themeDialog = new ChoiceDialog<>(themes.getFirst(), themes);
        themeDialog.setTitle("Sélection du Thème");
        themeDialog.setHeaderText("Choisissez un thème");

        Optional<Theme> result = themeDialog.showAndWait();

        result.ifPresent(selectedTheme -> {

            ChoiceDialog<Integer> difficultyDialog = new ChoiceDialog<>(1, 1, 2, 3, 4);
            difficultyDialog.setTitle("Difficulty");
            difficultyDialog.setContentText("Your difficulty (1, 2, 3, 4) :");

            Optional<Integer> diff = difficultyDialog.showAndWait();
            diff.ifPresent(selectedDifficulty -> {

                String questionText = selectedTheme.askQuestion(selectedDifficulty - 1);

                ChoiceDialog<String> answerDialog = new ChoiceDialog<>("a", "a", "b", "c", "d");
                answerDialog.setTitle("Question");
                answerDialog.setHeaderText(questionText);

                Optional<String> answer = answerDialog.showAndWait();

                answer.ifPresent(userChoice -> {
                    if (selectedTheme.checkAnswer(userChoice)) {
                        totalScore += selectedDifficulty;
                        scoreLabel.setText("Score: " + totalScore);
                        System.out.println("Correct ! +" + selectedDifficulty + " points.");
                    } else {
                        System.out.println("Faux ! Pas de points.");
                    }
                });
            });
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}