package Tetris.Input;

import Tetris.Menus.Menus;
import Tetris.Rendering.Render;
import Tetris.Rendering.RenderUtil;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MouseHover extends MouseMotionAdapter {

    @Override
    public void mouseMoved(MouseEvent e) {
        if(Render.Screen == RenderUtil.ScreenState.Game)
            return;
        Tetris.Menus.Menu menu = Menus.getCurrent();

        int btn = menu.buttonAt(e.getX(), e.getY());

        if(btn != -1)
            menu.selection = menu.getBtnbyID(btn).getSelectionID();
        else
            menu.selection = 0;
    }

}
