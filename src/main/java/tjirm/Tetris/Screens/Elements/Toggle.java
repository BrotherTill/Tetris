package main.java.tjirm.Tetris.Screens.Elements;

import main.java.tjirm.Tetris.Rendering.Text;

import java.awt.*;

public class Toggle extends Element {

    private final String text;
    private boolean active;

    public Toggle(String name, String text, int fontSize, int x, int y, boolean middle, boolean active) {
        this.name = name;
        this.text = Text.getStr(text);
        initFont(fontSize, text);
        int x1 = x;
        this.y = y;
        if(middle)
            x1 -= width / 2;
        this.x = x1;
        this.boundingBoxX1 = this.x - height / 3;
        this.boundingBoxY1 = this.y - height;
        this.boundingBoxX2 = this.x + width+ Text.fontHeight / 3;
        this.boundingBoxY2 = this.y + Text.fontHeight / 3;
        this.active = active;
    }

    public boolean isActive() {
        return true;
    }
    public boolean doPaint() {
        return true;
    }
    @Override
    public void paint(Graphics g, String selection) {
        g.setFont(font);
        if(active)
            if(selection.equals(name))
                g.setColor(activeSelColor);
            else
                g.setColor(activeColor);
        else
            if(selection.equals(name))
                g.setColor(selColor);
            else
                g.setColor(color);

        g.drawString(text, x, y);
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    public void toggleActive() {
        setActive(!active);
    }
    public boolean getActive() {
        return active;
    }
}
