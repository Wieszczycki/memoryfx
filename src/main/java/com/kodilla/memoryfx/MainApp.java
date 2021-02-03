package com.kodilla.memoryfx;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;


public class MainApp extends Application {

    private final Image imageback = new Image("file:src/main/resources/com/kodilla/memoryfx/background-memory.png");
    static int NUMBER_OF_BLOCKS = 10;
    @FXML
    private FlowPane boardPane = null;

    public static void newGame() {
        Board board = new Board(NUMBER_OF_BLOCKS);
        board.cleanBoard();
        board.shuffle();
        board.listBlocks();
    }

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("scene.fxml"));

        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageback, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        //GridPane grid = new GridPane();
        //grid.setBackground(background);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());


        //Keyboard listener
        stage.addEventHandler(KeyEvent.KEY_PRESSED,  (event) -> {
            System.out.println("Key pressed: " + event.toString());

            switch(event.getCode().getCode()) {
                case 27 : { // 27 = ESC key
                    stage.close();
                    break;
                }
                case 10 : { // 10 = Return
                    stage.setWidth( stage.getWidth() * 2);
                }
                default:  {
                    System.out.println("Unrecognized key");
                }
            }
        });

        stage.setTitle("MemoryFX");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
