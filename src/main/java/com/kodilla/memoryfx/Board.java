package com.kodilla.memoryfx;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
    private List board;
    private int revealed;

    public int getIndex() {
        return revealed;
    }

    public void setIndex(int revealed) {
        this.revealed = revealed;
    }

    public Board(int numberOfBlocks) {
        board = new ArrayList<>();
        revealed = 0;

        try {
            for(int i = 1; i <= (numberOfBlocks/2); i++) {
                Block blockA = new Block(i, false, false);
                Block blockB = new Block(i, true, false);
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

        return board.size() - revealed;
    }

    public void shuffle() {
        Collections.shuffle(board);
    }

    public void cleanBoard() {
        board.clear();
    }

    public List<Block> listBlocks() {
        int n = 0;
        System.out.println("Lista bloków:");

        for (Object block: board) {
            n++;
            System.out.println("Klocek " + n + " na planszy to: " + block);
        }

        return board;
    }

}
