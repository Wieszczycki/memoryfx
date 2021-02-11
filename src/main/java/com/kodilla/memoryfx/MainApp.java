package com.kodilla.memoryfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MainApp extends Application {

    private final Image imageBack = new Image("file:src/main/resources/com/kodilla/memoryfx/background-memory.png");
    public static int NUMBER_OF_BLOCKS = 16;

    static FXMLController controller;
    public static Board board = null;
    private FlowPane testFlow;

    public static int score1 = 0;
    public static int score2 = 0;
    public static int currentPlayer = 1;

    public static void newGame() throws FileNotFoundException {
        score1 = 0;
        score2 = 0;
        currentPlayer = new Random().nextInt(2) + 1;
        board = new Board(NUMBER_OF_BLOCKS);
        board.shuffle();
        controller.displayBlocks(board);
    }

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("scene.fxml"));
        Parent root = loader.load();
        controller = loader.getController();

        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageBack, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

/*
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
*/
        stage.setTitle("MemoryFX");
        stage.setScene(scene);
        stage.show();

        //Button b1 = new Button("testowy");
        //testFlow = controller.getBoardPane();
        //testFlow.getChildren().add(b1);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
