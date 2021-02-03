package com.kodilla.memoryfx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
    private List board;
    private int index;

    public Board(int numberOfBlocks) {
        board = new ArrayList<>();
        index = 0;

        try {
            for(int i = 1; i <= (numberOfBlocks/2); i++) {
                Image blockImage = new Image(Block.getFilename(i));
                Block blockA = new Block(i, false, blockImage, false);
                Block blockB = new Block(i, true, blockImage, false);
                addBlock(blockA);
                addBlock(blockB);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void addBlock(Block block) {
        board.add(block);
    }

    public int getSizeOfBoard() {
        return board.size();
    }

    public int getNumberOfBlocksRevealed() {

        return board.size() - index;
    }

    public void shuffle() {
        Collections.shuffle(board);
    }

    public void cleanBoard() {
        board.clear();
    }

    public List<Block> listBlocks() {
        int n = 0;

        for (Object block: board) {
            n++;
            System.out.println("Klocek " + n + " na planszy to: " + block);
        }

        return board;
    }

}
