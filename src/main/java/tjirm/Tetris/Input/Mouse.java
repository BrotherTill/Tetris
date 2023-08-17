package main.java.tjirm.Tetris.Input;

import main.java.tjirm.Tetris.Screens.Screen;
import main.java.tjirm.Tetris.Screens.Screens;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouse extends MouseAdapter {

    private String selection = "null";

    @Override
    public void mousePressed(MouseEvent e) {
        selection = Screens.getCurrent().selection;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Screen screen = Screens.getCurrent();
        if(selection.equals(screen.selection))
            screen.clickAction();
    }

}
