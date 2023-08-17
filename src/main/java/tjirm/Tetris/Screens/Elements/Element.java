package main.java.tjirm.Tetris.Screens.Elements;

import main.java.tjirm.Tetris.Rendering.Text;

import java.awt.*;

public abstract class Element {

    public static final int normalFontSize = 0;
    public static final int bigFontSize = 1;
    public static final int headerFontSize = 2;

    final static Color color = Text.color;
    final static Color selColor = Text.selectionColor;
    final static Color activeColor = Text.activeColor;
    final static Color activeSelColor = Text.activeSelectionColor;

    protected String name;
    protected Font font;
    protected int x;
    protected int y;
    protected int width;
    protected int height;

    protected int boundingBoxX1;
    protected int boundingBoxX2;
    protected int boundingBoxY1;
    protected int boundingBoxY2;

    protected Element() {
        this.name = null;
        this.x = 0;
        this.y = 0;
        this.width = 0;
        this.height = 0;
    }

    protected void initFont(int fontSize, String name) {
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
    }

    public boolean doPaint() {
        return false;
    }
    public boolean isActive() {
        return false;
    }

    public void paint(Graphics g, String selection, String MSelection, String KSelection) {
        paint(g, selection);
    }
    public void paint(Graphics g, String selection) {
    }
    public String getName() {
        return name;
    }
    public boolean inBoundingBox(int x, int y) {
        return      boundingBoxX1 < x && boundingBoxX2 > x
                &&  boundingBoxY1 < y && boundingBoxY2 >= y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getMidX(){
        return x - width / 2;
    }
}