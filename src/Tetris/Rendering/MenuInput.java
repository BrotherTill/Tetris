package Tetris.Rendering;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;

public class MenuInput extends KeyAdapter {

    private HashSet<Integer> heldKeys = new HashSet<>();

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        int maxXSelection = Render.Screen.getMaxXSelection();
        int maxYSelection = Render.Screen.getMaxYSelection();


        switch(key) {
            default -> {
                if(heldKeys.contains(key))
                    return;
                heldKeys.add(key);
            }
        }

        switch (key) {
            case KeyEvent.VK_DOWN:
                Menus.selection++;
                if(Menus.selection >= maxXSelection + 1)
                    Menus.selection = 1;
                break;
            case KeyEvent.VK_UP:
                Menus.selection--;
                if(Menus.selection <= 0)
                    Menus.selection = maxXSelection;
                break;
            case KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT:
                if(Menus.selection == maxXSelection + maxYSelection) {
                    Menus.selection = maxXSelection;
                } else {
                    Menus.selection = maxXSelection + maxYSelection;
                }
                break;
            case KeyEvent.VK_ENTER, KeyEvent.VK_SPACE:
                Menus.performSelection();
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
