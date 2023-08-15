package main.java.tjirm.Tetris.Screens.Elements;

import main.java.tjirm.Tetris.Rendering.Text;

import java.awt.*;

public class Toggle extends Element {

    public static final int normalFontSize = 0;
    public static final int bigFontSize = 1;
    public static final int headerFontSize = 2;

    private final String name;
    private final String text;
    private final Font font;
    private final int selectionID;
    private boolean active;
    private final Color color;
    private final Color selColor;
    private final Color activeColor;
    private final Color activeSelColor;

    private final int boundingBoxX1;
    private final int boundingBoxX2;
    private final int boundingBoxY1;
    private final int boundingBoxY2;

    private final int x;
    private final int y;
    private final int width;
    private final int height;

    public Toggle(String name, int fontSize, int selectID, int x, int y, boolean middle, boolean active) {
        this.name = name;
        this.selectionID = selectID;
        this.text = Text.getStr(name);
        switch (fontSize) {
            case bigFontSize:
                font = Text.textBigFont;
                width = Text.getBigWidth(name);
                height = Text.bigFontHeight;
                break;
            case headerFontSize:
                font = Text.textHeaderFont;
                width = Text.getHeadWidth(name);
                height = Text.headerFontHeight;
                break;
            default:
                System.out.println("Gave Illegal fontSize defaulting to \"normal\" Font Size");
            case normalFontSize:
                font = Text.textFont;
                width = Text.getWidth(name);
                height = Text.fontHeight;
                break;
        }
        int x1 = x;
        this.y = y;
        if(middle)
            x1 -= width / 2;
        this.x = x1;
        this.boundingBoxX1 = this.x - height / 3;
        this.boundingBoxY1 = this.y - height;
        this.boundingBoxX2 = this.x + width+ Text.fontHeight / 3;
        this.boundingBoxY2 = this.y + Text.fontHeight / 3;
        color = Text.color;
        selColor = Text.selectionColor;
        activeColor = Text.activeColor;
        activeSelColor = Text.activeSelectionColor;
        this.active = active;
    }

    @Override
    public void paint(Graphics g, int selection) {
        g.setFont(font);
        if(active)
            if(selection == selectionID)
                g.setColor(activeSelColor);
            else
                g.setColor(activeColor);
        else
            if(selection == selectionID)
                g.setColor(selColor);
            else
                g.setColor(color);

        g.drawString(text, x, y);
    }

    public boolean inBoundingBox(int x, int y) {
        return      boundingBoxX1 < x && boundingBoxX2 > x
                &&  boundingBoxY1 < y && boundingBoxY2 > y;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    public void toggleActive() {
        setActive(!active);
    }
    public boolean isActive() {
        return active;
    }

    public String getName() {
        return name;
    }
    public int getSelectionID() {
        return selectionID;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}
