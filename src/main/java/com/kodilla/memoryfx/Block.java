package com.kodilla.memoryfx;

import javafx.scene.image.Image;

public class Block {

    private Integer blockValue;
    private boolean blockClone;
    private Image blockImage;
    private boolean blockRevealed;

    public Block(Integer blockValue, boolean blockClone, Image blockImage, boolean blockRevealed) {
        this.blockValue = blockValue;
        this.blockClone = blockClone;
        this.blockImage = blockImage;
        this.blockRevealed = blockRevealed;
    }

    public static String getFilename(Integer blockValue) {
        return "file:src/main/resources/com/kodilla/memoryfx/" + blockValue.toString() + ".png";
    }

    public Integer getBlockValue() {
        return blockValue;
    }

    public boolean isBlockClone() {
        return blockClone;
    }

    public Image getBlockImage() {
        return blockImage;
    }

    @Override
    public String toString() {
        String value, clone, revealed;

        value = "[" + blockValue.toString();
        if (blockClone) {
            clone = "A]";
        } else {
            clone = "B]";
        }
        if (blockRevealed) {
            revealed = "=X";
        } else {
            revealed = "=O";
        }

        return value + clone + revealed;
    }
}



