package com.kodilla.memoryfx;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

public class FXMLController implements Initializable {
    
    @FXML
    private Label labelInfo;
    public Button buttonNewGame = null;
    private int buttonNewGameClickCount = 0;
    @FXML
    private FlowPane boardPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        labelInfo.setText("JavaFX " + javafxVersion + "\nRunning on Java " + javaVersion + ".");
        boardPane.setHgap(5);
        boardPane.setVgap(5);
    }

    public void buttonClicked(Event e){
        this.buttonNewGameClickCount++;

        String text = "buttonNewGame clicked " + this.buttonNewGameClickCount + " times";
        System.out.println(text);
        buttonNewGame.setText(text);

        MainApp.newGame();
    }

    public void displayBoard (List<Block> board) {
        int n = 0;

        for (Object block: board) {
            n++;

            System.out.println("Klocek " + n + " na planszy to: " + block);
        }
    }

}
