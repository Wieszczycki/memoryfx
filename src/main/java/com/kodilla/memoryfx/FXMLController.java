package com.kodilla.memoryfx;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import static com.kodilla.memoryfx.MainApp.*;

public class FXMLController implements Initializable {

    public static int triedBlocks = 0;
    public static Block firstBlock = new Block(0,false,false);
    public static Button firstButton = new Button();
    public static Block lastBlock = new Block(0,false,false);
    public static Button lastButton = new Button();

    @FXML
    private Label labelInfo;
    @FXML
    private Label labelScore1;
    @FXML
    private Label labelScore2;
    public Button buttonNewGame = null;
    private int buttonNewGameClickCount = 0;
    @FXML
    private FlowPane boardPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        labelInfo.setText("JavaFX " + javafxVersion + " running on Java " + javaVersion + ".");
        boardPane.setHgap(5);
        boardPane.setVgap(5);
    }

    public void buttonNewGameClicked(Event e) throws FileNotFoundException {
        this.buttonNewGameClickCount++;

/*
        String text = "buttonNewGame clicked " + this.buttonNewGameClickCount + " times";
        System.out.println(text);
        buttonNewGame.setText(text);
*/

        try {
            lastBlock = new Block(0,false,false);
            lastButton = new Button();
            triedBlocks = 0;
            newGame();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void changeScore(){
        board.setIndex(board.getIndex() + 2);
    }

    public void refreshScore() {
        this.labelScore1.setText("Gracz A: " + score1);
        this.labelScore2.setText("Gracz B: " + score2);
        if (currentPlayer == 1){
            this.labelScore1.setTextFill(Color.RED);
            this.labelScore2.setTextFill(Color.BLACK);
        }
        if (currentPlayer == 2){
            this.labelScore1.setTextFill(Color.BLACK);
            this.labelScore2.setTextFill(Color.RED);
        }
    }

    public void displayBlocks(Board board) throws FileNotFoundException {
        int n = 0;
        boardPane.getChildren().clear();
        for (Block block: board.listBlocks()) {
            n++;
            FileInputStream input;
            if (block.isBlockRevealed()) {
                input = new FileInputStream("src/main/resources/com/kodilla/memoryfx/" + block.getBlockValue() + ".png");
            } else {
                input = new FileInputStream("src/main/resources/com/kodilla/memoryfx/0.png");
            }
            Image image = new Image(input);
            ImageView imageView = new ImageView(image);

            //Button button = new Button(block.toString(), imageView);
            Button button = new Button("", imageView);
            button.setEffect(new DropShadow());
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println();
                    System.out.println("DEBUG: kliknąłeś blok: " + block);
                    if (!block.isBlockRevealed()) {
                        if (lastBlock == null) {
                            lastBlock = block;
                        }
                        System.out.println("Block.value=" + block.getBlockValue().toString());
                        Image realImage = new Image("file:src/main/resources/com/kodilla/memoryfx/" + block.getBlockValue().toString() + ".png");
                        System.out.println("realImage=" + "file:src/main/resources/com/kodilla/memoryfx/" + block.getBlockValue().toString() + ".png");
                        Image lastImage = new Image("file:src/main/resources/com/kodilla/memoryfx/" + lastBlock.getBlockValue().toString() + ".png");
                        System.out.println("lastImage=" + "file:src/main/resources/com/kodilla/memoryfx/" + lastBlock.getBlockValue().toString() + ".png");
                        Image reverseImage = new Image("file:src/main/resources/com/kodilla/memoryfx/0.png");
                        System.out.println("reverseImage=" + "file:src/main/resources/com/kodilla/memoryfx/0.png");

                        if (triedBlocks > 1) {
                            triedBlocks = 0;
                            System.out.println("Zamykam dwa różne bloki: " + block.getBlockValue().toString() + " i " + lastBlock.getBlockValue().toString());
                            firstButton.setGraphic(new ImageView(reverseImage));
                            lastButton.setGraphic(new ImageView(reverseImage));
                            System.out.println("DEBUG: block=" + block + " lastBlock=" + lastBlock + " firstBlock=" + firstBlock + " triedBlocks=" + triedBlocks);
                        } else if (triedBlocks == 1) {
                            triedBlocks++;
                            if (block.getBlockValue().equals(lastBlock.getBlockValue())) {
                                triedBlocks = 0;
                                System.out.println("Takie same bloki! Wartość = " + block.getBlockValue().toString());
                                button.setGraphic(new ImageView(realImage));
                                block.setBlockRevealed(true);
                                lastButton.setGraphic(new ImageView(lastImage));
                                lastBlock.setBlockRevealed(true);
                                System.out.println("DEBUG: block=" + block + " lastBlock=" + lastBlock + " firstBlock=" + firstBlock + " triedBlocks=" + triedBlocks);
                            } else {
                                System.out.println("Różne bloki. Wartość = " + block.getBlockValue().toString() + " i " + lastBlock.getBlockValue().toString());
                                button.setGraphic(new ImageView(realImage));
                                lastBlock = block;
                                lastButton = button;
                                System.out.println("DEBUG: block=" + block + " lastBlock=" + lastBlock + " firstBlock=" + firstBlock + " triedBlocks=" + triedBlocks);

                                if (currentPlayer == 1) {
                                    score1++;
                                    currentPlayer = 2;
                                } else {
                                    score2++;
                                    currentPlayer = 1;
                                }
                                System.out.println("DEBUG: currentPlayer=" + currentPlayer);
                                refreshScore();
                            }
                        } else if (triedBlocks == 0) {
                            triedBlocks++;
                            System.out.println("Pierwszy blok to " + block.getBlockValue().toString());
                            lastBlock = block;
                            lastButton = button;
                            firstBlock = block;
                            firstButton = button;
                            button.setGraphic(new ImageView(realImage));
                            System.out.println("DEBUG: block=" + block + " lastBlock=" + lastBlock + " firstBlock=" + firstBlock + " triedBlocks=" + triedBlocks);
                        }
                    }
                }
            });

            boardPane.getChildren().add(button);
        }
        refreshScore();
    }

    public FlowPane getBoardPane() {
        return this.boardPane;
    }

}
