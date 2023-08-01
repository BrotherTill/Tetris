package Tetris.Input;

import Tetris.Game.Board;
import Tetris.Main;
import Tetris.Menus.Menus;
import Tetris.Rendering.Render;
import Tetris.Rendering.RenderUtil;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class Mouse extends MouseAdapter {

    @Override
    public void mouseClicked(MouseEvent e) {
        if(Render.Screen == RenderUtil.ScreenState.Game) {
            Board.stop();
            Menus.mainMenu.selection = 0;
            Render.Screen = RenderUtil.ScreenState.TryAgain;
            Main.render.setCurrentListener(new Menu());
        } else {
            Menus.getCurrent().selectionAction();
        }
    }

}
