package main.java.tjirm.Tetris.Screens.Elements;

import main.java.tjirm.Tetris.Rendering.Text;

import java.awt.*;

public class Button extends Element {

    private final String text;
    private Color color;
    private Color selColor;

    private boolean active = true;

    public Button(String name, String text, int fontSize, int x, int y, boolean middle, boolean quit) {
        this.name = name;
        this.text = Text.getStr(text);
        initFont(fontSize, text);
        init(x, y, middle, quit);
    }

    private void init(int x, int y, boolean middle, boolean quit) {
        int x1 = x;
        this.y = y;
        if(middle)
            x1 -= width / 2;
        this.x = x1;
        this.boundingBoxX1 = this.x - height / 3;
        this.boundingBoxY1 = this.y - height;
        this.boundingBoxX2 = this.x + width+ Text.fontHeight / 3;
        this.boundingBoxY2 = this.y + Text.fontHeight / 3;
        if(quit) {
            color = Text.exitColor;
            selColor = Text.exitSelectionColor;
        } else {
            color = Text.color;
            selColor = Text.selectionColor;
        }
    }

    public Button(String text, int fontSize, int x, int y, boolean middle) {
        this.name = "Special" + text;
        this.text = text;
        switch (fontSize) {
            case bigFontSize:
                font = Text.textBigFont;
                width = Text.getBigTextWidth(text);
                height = Text.bigFontHeight;
                break;
            case headerFontSize:
                font = Text.textHeaderFont;
                width = Text.getHeadTextWidth(text);
                height = Text.headerFontHeight;
                break;
            default:
                System.out.println("Gave Illegal fontSize defaulting to \"normal\" Font Size");
            case normalFontSize:
                font = Text.textFont;
                width = Text.getTextWidth(text);
                height = Text.fontHeight;
                break;
        }
        init(x, y, middle, false);
    }

    public boolean isActive() {
        return active;
    }
    public boolean doPaint() {
        return true;
    }
    @Override
    public void paint(Graphics g, String selection) {
        g.setFont(font);
        if(selection.equals(name))
            g.setColor(selColor);
        else
            g.setColor(color);
        g.drawString(text, x, y);
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}