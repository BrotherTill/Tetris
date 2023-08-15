package main.java.tjirm.Tetris.Screens.Elements;

import java.awt.*;

public abstract class Element {

    public abstract void paint(Graphics g, int selection);

    public abstract int getSelectionID();
    public abstract int getX();
    public abstract int getY();
}
