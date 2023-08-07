package Tetris.Pieces;

import java.util.Objects;

public class Block {

    private boolean filled;

    public Block() {
        filled = false;
    }

    public Block(boolean filled) {
        this.filled = filled;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    @Override
    public boolean equals(Object o) {       //not from StackOverflow *wink wink*
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Block block = (Block) o;
        return Objects.equals(filled, block.filled);
    }

}