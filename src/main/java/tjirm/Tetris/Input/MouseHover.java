package main.java.tjirm.Tetris.Input;

import main.java.tjirm.Tetris.Screens.Screen;
import main.java.tjirm.Tetris.Screens.Screens;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MouseHover extends MouseMotionAdapter {

    @Override
    public void mouseMoved(MouseEvent e) {
        Screen screen = Screens.getCurrent();

        int btn = screen.elementAt(e.getX(), e.getY());

        if(btn != -1)
            screen.selection = screen.getElembyID(btn).getSelectionID();
        else
            screen.selection = 0;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Screen screen = Screens.getCurrent();

        int btn = screen.elementAt(e.getX(), e.getY());

        if(btn != -1)
            screen.selection = screen.getElembyID(btn).getSelectionID();
        else
            screen.selection = 0;
    }

}
