package main.java.tjirm.Tetris.Screens.Elements;

import main.java.tjirm.Tetris.Rendering.Text;

import java.awt.*;

public class Option<Value> extends Element implements Comparable<Option<Value>> {
    private final String text;
    private final Font font;
    protected final int Id;

    private boolean prevIN = false;

    private final Value value;
    private final DropDown<Value> reference;
    protected boolean selected;

    public Option(String name, int fontSize, int Id, Value value, DropDown<Value> reference) {
        this.name = name;
        this.text = Text.getStr(name);
        this.Id = Id;
        this.value = value;
        this.reference = reference;
        this.y = reference.y;
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
        return selected || reference.active;
    }

    @Override
    public void paint(Graphics g, String selection, String MSelection, String KSelection) {
        g.setFont(font);
        if(reference.selected.equals(name))
            if(selection.equals(getName()))
                g.setColor(Text.activeSelectionColor);
            else
                g.setColor(Text.activeColor);
        else
            if(selection.equals(getName()))
                g.setColor(Text.selectionColor);
            else
                g.setColor(Text.color);
        g.drawString(text, reference.x - width / 2, y);
    }
    public Value getValue() {
        return value;
    }

    public void setBounds(int[] bounds) {
        assert bounds.length == 4 : "not good bounds";
        //System.out.print("setting bounding Box ");
        boundingBoxX1 = bounds[0];
        boundingBoxY1 = bounds[1];
        boundingBoxX2 = bounds[2];
        boundingBoxY2 = bounds[3];
        //System.out.println("from x: " + boundingBoxX1 + "  y: " + boundingBoxY1 + "    to x: " + boundingBoxX2 + "  y: " + boundingBoxY2);
    }

    public boolean isActive() {
        return selected || reference.active;
    }
    @Override
    public boolean inBoundingBox(int x, int y) {
        boolean b = boundingBoxX1 < x && boundingBoxX2 > x
                &&  boundingBoxY1 < y && boundingBoxY2 >= y;
        if(b && !prevIN)
            reference.enteredBounds(name);
        else if(!b && prevIN)
            reference.leftBounds(name);
        prevIN = b;
        return      boundingBoxX1 < x && boundingBoxX2 > x
                 && boundingBoxY1 < y && boundingBoxY2 >= y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    @Override
    public String getName() {
        return reference.name + name.toUpperCase();
    }

    @Override
    public int compareTo(Option option) {
        return (this.Id - option.Id);
    }
}