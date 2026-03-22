package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import models.Game;
import models.Tile;
import models.question.Theme;


public class GameBoardController {


    //Attributs
    @FXML
    private Pane boardPane;

    private Game game = new Game();


    public void drawTiles() {
        //game.getGBoard().getTiles().clear();
        Theme tourism = new Theme("Tourism");
        Theme informatic = new  Theme("Informatic");
        Theme entertainment = new Theme("Entertainment");
        game.getGBoard().getTiles().add(new Tile(1,14,14,tourism));
        game.getGBoard().getTiles().add(new Tile(2,74,14, informatic));
        game.getGBoard().getTiles().add(new Tile(3,134,14, entertainment));
        game.getGBoard().getTiles().add(new Tile(4,134,74, tourism));
        // Créer chaque case met à sa position et met sa couleur de thème
        for (Tile tile :  game.getGBoard().getTiles()) {
            Rectangle r = new Rectangle(60, 60);
            r.setLayoutX(tile.getPositionX());
            r.setLayoutY(tile.getPositionY());
            r.setArcWidth(10);
            r.setArcHeight(10);

            r.setFill(themeToColor(tile.getTheme().getThemeName()));
            r.setStroke(Color.BLACK);

            boardPane.getChildren().add(r);
        }
    }

    private Paint themeToColor(String themeName) {
        //Va donner la couleur à la case en focntion du thème
        switch (themeName) {
            case "Tourism":
                return Color.RED;
            case "Informatic":
                return Color.BLUE;
            case "Entertainment":
                return Color.GREEN;
            case "Mystery":
                return Color.YELLOW;
            default: // Pour les cases Turbos
                return Color.WHITE;
        }
    }


    public void accessLevelQuestion(ActionEvent actionEvent) {
    }
}
