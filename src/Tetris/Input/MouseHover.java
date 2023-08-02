package Tetris.Input;

import Tetris.Screens.Screen;
import Tetris.Screens.Screens;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MouseHover extends MouseMotionAdapter {

    @Override
    public void mouseMoved(MouseEvent e) {
        Screen screen = Screens.getCurrent();

        int btn = screen.buttonAt(e.getX(), e.getY());

        if(btn != -1)
            screen.selection = screen.getBtnbyID(btn).getSelectionID();
        else
            screen.selection = 0;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Screen screen = Screens.getCurrent();

        int btn = screen.buttonAt(e.getX(), e.getY());

        if(btn != -1)
            screen.selection = screen.getBtnbyID(btn).getSelectionID();
        else
            screen.selection = 0;
    }

}
