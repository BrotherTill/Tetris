package main.java.tjirm.Tetris.Screens;

import main.java.tjirm.Tetris.Pieces.PieceUtil.Direction;
import main.java.tjirm.Tetris.Rendering.RenderUtil;
import main.java.tjirm.Tetris.Screens.Elements.Button;
import main.java.tjirm.Tetris.Screens.Elements.DropDown;
import main.java.tjirm.Tetris.Screens.Elements.Element;
import main.java.tjirm.Tetris.Screens.Elements.Toggle;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public abstract class Screen {

    //To modify these Variables goto Tetris.Logic.Board
    final int blockHeight = RenderUtil.blockHeight;
    final int blockWidth = RenderUtil.blockWidth;
    final int blockPadding = RenderUtil.blockPadding;
    final int totalBlockHeight = RenderUtil.totalBlockHeight;
    final int totalBlockWidth = RenderUtil.totalBlockWidth;
    final int frameHeight = RenderUtil.frameHeight;                  //Calculated from the Tetris.Pieces.Block height and padding
    final int frameWidth = RenderUtil.frameWidth;                    //Calculated from the Tetris.Pieces.Block width and padding

    public String selection = "null";
    public String Mselection = "null";
    public String Kselection = "null";

    public void clickAction() {
        selectionAction();
    }
    public void pressAction() {
        selectionAction();
    }

    protected void selectionAction() {
    }

    public void exitAction() {}

    public void paint(Graphics g) {
        draw(g);
        for(Map.Entry<String, Element> entry : elementMap.entrySet()) {
            if(entry.getValue().doPaint())
                entry.getValue().paint(g, selection, Mselection, Kselection);
        }
    }

    protected abstract void draw(Graphics g);

    public Screen() {
        init();
    }

    private Map<String, Element> elementMap = new HashMap<>();
    private Map<String, Button> buttonMap = new HashMap<>();
    private Map<String, Toggle> toggleMap = new HashMap<>();
    private Map<String, DropDown> dropDownMap = new HashMap<>();

    public void addBtn(String name, String text, int fontSize, int x, int y, boolean middle, boolean quit) {
        elementMap.put(name, new Button(name, text, fontSize, x, y, middle, quit));
        buttonMap.put(name, new Button(name, text, fontSize, x, y, middle, quit));
    }

    public void addBtn(String text, int fontSize, int x, int y, boolean middle) {
        elementMap.put("Special" + text, new Button(text, fontSize, x, y, middle));
        buttonMap.put("Special" + text, new Button(text, fontSize, x, y, middle));
    }

    public void addTgl(String name, String text, int fontSize, int x, int y, boolean middle, boolean active) {
        elementMap.put(name, new Toggle(name, text, fontSize, x, y, middle, active));
        toggleMap.put(name, new Toggle(name, text, fontSize, x, y, middle, active));
    }
    public <Value> void addDropDown(DropDown<Value> newDropDown) {
        elementMap.put(newDropDown.getName(), newDropDown);
        dropDownMap.put(newDropDown.getName(), newDropDown);
        newDropDown.getOptions().forEach((key, value) -> elementMap.put(value.getName(), value));
    }

    public Element elementAt(int x, int y) {
        for(Map.Entry<String, Element> entry : elementMap.entrySet()) {
            Element elem = entry.getValue();
            if(elem.isActive() && elem.inBoundingBox(x,y))
                return elem;
        }
        return null;
    }

    public String nameAt(int x, int y) {
        for(Map.Entry<String, Element> entry : elementMap.entrySet()) {
            Element elem = entry.getValue();
            if(elem.isActive() && elem.inBoundingBox(x,y))
                return entry.getKey();
        }
        return "null";
    }

    public String getName(Direction direction, String currentName) {
        if(elementMap.isEmpty())
            return "null";
        Element currentElem = elementMap.get(currentName);
        if(currentElem == null) {
            switch (direction) {
                case north -> { return findValid(direction, 1, 1); }
                case south -> { return findValid(direction, 1, frameHeight); }
                case west -> { return findValid(direction, 1, 1); }
                case east -> { return findValid(direction, frameWidth , 1); }
            }
        }
        return findValid(direction, currentElem.getMidX(), currentElem.getY());
    }

    private String findValid(Direction direction, int x, int y) {
        Element product = null;
        double minDistance = -1;
        for(Map.Entry<String, Element> entry : elementMap.entrySet()) {
            Element elem = entry.getValue();
            if(!elem.doPaint() || !elem.isActive())
                continue;
            double distance = 0;
            switch (direction) {
                case north -> {
                    distance = y > elem.getY() ? y - elem.getY() : y + frameHeight - elem.getY();
                    distance = Math.sqrt(Math.abs(x - elem.getMidX()) + distance);
                }
                case south -> {
                    distance = y < elem.getY() ? elem.getY() - y : frameHeight - y + elem.getY();
                    distance = Math.sqrt(Math.abs(x - elem.getMidX()) + distance);
                }
                case east -> {
                    distance = x < elem.getMidX() ? elem.getMidX() - x : frameHeight - x + elem.getMidX();
                    distance = Math.sqrt(Math.abs(y - elem.getY()) * 2 + distance);
                }
                case west -> {
                    distance = x > elem.getMidX() ? x - elem.getMidX() : x + frameHeight - elem.getMidX();
                    distance = Math.sqrt(Math.abs(y - elem.getY()) * 2 + distance);
                }
            }
            if(product == null || distance < minDistance)
                product = elem;
            if(minDistance == -1 || distance < minDistance)
                minDistance = distance;
        }
        return product.getName();
    }

    public Element getElementbyName(String name) {
        return elementMap.get(name);
    }
    public Button getButtonbyName(String name) {
        return buttonMap.get(name);
    }
    public Toggle getTogglebyName(String name) {
        return toggleMap.get(name);
    }
    public DropDown getDropDownbyName(String name) {
        return dropDownMap.get(name);
    }

    private int loopAround(int i, int max) {
        if(0 > i)
            i = max;
        if(max < i)
            i = 0;
        return i;
    }

    public abstract void init();
}