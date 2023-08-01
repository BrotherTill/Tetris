package Tetris.Input;

import Tetris.Menus.Menus;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;

public class Menu extends KeyAdapter {

    private final HashSet<Integer> heldKeys = new HashSet<>();

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        Tetris.Menus.Menu menu = Menus.getCurrent();

        if(heldKeys.contains(key))
            return;
        heldKeys.add(key);

        switch (key) {
            case KeyEvent.VK_DOWN -> menu.selection = menu.getSelectionId("down", menu.selection);
            case KeyEvent.VK_UP -> menu.selection = menu.getSelectionId("up", menu.selection);
            case KeyEvent.VK_LEFT -> menu.selection = menu.getSelectionId("left", menu.selection);
            case KeyEvent.VK_RIGHT -> menu.selection = menu.getSelectionId("right", menu.selection);
            case KeyEvent.VK_ENTER, KeyEvent.VK_SPACE -> menu.selectionAction();
            case KeyEvent.VK_ESCAPE -> menu.exitAction();
        }
        System.out.println(menu.selection);
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
