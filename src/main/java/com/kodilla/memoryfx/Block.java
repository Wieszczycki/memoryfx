package com.kodilla.memoryfx;

public class Block {

    private Integer blockValue;
    private boolean blockClone;

    public void setBlockRevealed(boolean blockRevealed) {
        this.blockRevealed = blockRevealed;
    }

    private boolean blockRevealed;

    public Block(Integer blockValue, boolean blockClone, boolean blockRevealed) {
        this.blockValue = blockValue;
        this.blockClone = blockClone;
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

    public boolean isBlockRevealed() {
        return blockRevealed;
    }

    @Override
    public String toString() {
        String value, clone, revealed;

        value = "" + blockValue.toString();
        if (blockClone) {
            clone = "A";
        } else {
            clone = "B";
        }
        if (blockRevealed) {
            revealed = "=X";
        } else {
            revealed = "=O";
        }

        //return value + clone + revealed;
        return value + clone;
    }
}



