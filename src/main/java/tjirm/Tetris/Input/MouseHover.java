package main.java.tjirm.Tetris.Input;

import main.java.tjirm.Tetris.Screens.Screen;
import main.java.tjirm.Tetris.Screens.Screens;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MouseHover extends MouseMotionAdapter {

    @Override
    public void mouseMoved(MouseEvent e) {
        Screen screen = Screens.getCurrent();

        screen.selection = screen.nameAt(e.getX(), e.getY());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Screen screen = Screens.getCurrent();

        screen.selection = screen.nameAt(e.getX(), e.getY());
    }

}
