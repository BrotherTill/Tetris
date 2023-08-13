package Main.Java.tjirm.Tetris.Input;

import Main.Java.tjirm.Tetris.Screens.Screen;
import Main.Java.tjirm.Tetris.Screens.Screens;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;

public class Menu extends KeyAdapter {

    private final HashSet<Integer> heldKeys = new HashSet<>();

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        Screen screen = Screens.getCurrent();

        if(heldKeys.contains(key) || screen == null)
            return;
        heldKeys.add(key);

        switch (key) {
            case KeyEvent.VK_DOWN -> screen.selection = screen.getSelectionId("down", screen.selection);
            case KeyEvent.VK_UP -> screen.selection = screen.getSelectionId("up", screen.selection);
            case KeyEvent.VK_LEFT -> screen.selection = screen.getSelectionId("left", screen.selection);
            case KeyEvent.VK_RIGHT -> screen.selection = screen.getSelectionId("right", screen.selection);
            case KeyEvent.VK_ENTER, KeyEvent.VK_SPACE -> screen.selectionAction();
            case KeyEvent.VK_ESCAPE -> screen.exitAction();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if(!heldKeys.contains(key))
            return;
        heldKeys.remove(key);

        switch (key) {
        }
    }

}
