package Tetris.Rendering;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Vector;

import static Tetris.Rendering.Menus.selection;

public class MenuInput extends KeyAdapter {

    private final HashSet<Integer> heldKeys = new HashSet<>();

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();


        if(heldKeys.contains(key))
            return;
        heldKeys.add(key);

        int[][] selectionPage = Render.Screen.getSelection();
        int x = 0;
        int y = -1;
        for(int i=0; i<selectionPage[0].length; i++){
            for(int j=0; j<selectionPage.length; j++){
                if(selectionPage[j][i] == selection) {
                    x = i;
                    y = j;
                }
            }
        }

        switch (key) {
            case KeyEvent.VK_DOWN -> {
                y++;
                selection = findValid("down", selectionPage, x, y);
            }
            case KeyEvent.VK_UP -> {
                y--;
                selection = findValid("up", selectionPage, x, y);
            }
            case KeyEvent.VK_RIGHT -> {
                x++;
                selection = findValid("right", selectionPage, x, y);
            }
            case KeyEvent.VK_LEFT -> {
                x--;
                selection = findValid("left", selectionPage, x, y);
            }
            case KeyEvent.VK_ENTER, KeyEvent.VK_SPACE -> Menus.performSelection();
        }
    }

    private int findValid(String direction, int[][] page, int x, int y) {
        int out = -1;
        while (out == -1){
            x = loopAround(x, page[0].length);
            y = loopAround(y, page.length);
            if(page[y][x] != -1)
                out = page[y][x];
            switch (direction) {
                case "up" -> y--;
                case "down" -> y++;
                case "left" -> x--;
                case "right" -> x++;
            }
        }
        return out;
    }

    private int loopAround(int x, int max) {
        if(0 > x) {
            x = max - 1;
        }
        if(max <= x) {
            x = 0;
        }
        return x;
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
