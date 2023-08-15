package main.java.tjirm.Tetris.Screens;

import main.java.tjirm.Tetris.Pieces.PieceUtil.Direction;
import main.java.tjirm.Tetris.Rendering.RenderUtil;
import main.java.tjirm.Tetris.Screens.Elements.Button;
import main.java.tjirm.Tetris.Screens.Elements.Element;
import main.java.tjirm.Tetris.Screens.Elements.Toggle;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Screen {

    //To modify these Variables goto Tetris.Logic.Board
    final int blockHeight = RenderUtil.blockHeight;
    final int blockWidth = RenderUtil.blockWidth;
    final int blockPadding = RenderUtil.blockPadding;
    final int totalBlockHeight = RenderUtil.totalBlockHeight;
    final int totalBlockWidth = RenderUtil.totalBlockWidth;
    final int frameHeight = RenderUtil.frameHeight;                  //Calculated from the Tetris.Pieces.Block height and padding
    final int frameWidth = RenderUtil.frameWidth;                    //Calculated from the Tetris.Pieces.Block width and padding

    public int selection = 0;

    public void selectionAction() {
    }

    public void exitAction() {}

    public void paint(Graphics g) {
        draw(g);
        for(Element elem : elementList) {
            elem.paint(g, selection);
        }
    }

    protected abstract void draw(Graphics g);

    public Screen() {
        init();
    }

    private List<Element> elementList = new ArrayList<>();

    public void addBtn(Button newButton) {
        elementList.add(newButton);
    }

    public void addTgl(Toggle newToggle) {
        elementList.add(newToggle);
    }

    public int elementAt(int x, int y) {
        for(int i=0; i< elementList.size(); i++) {
            if(elementList.get(i).inBoundingBox(x,y))
                return i;
        }
        return -1;
    }

    public int getSelectionId(Direction direction, int currentId) {
        if(elementList.isEmpty())
            return 0;
        Element currentElem = null;
        for (Element elem : elementList)
            if (elem.getSelectionID() == currentId)
                currentElem = elem;
        if(currentElem == null) {
            switch (direction) {
                case north -> { return findValid(direction, 1, 1); }
                case south -> { return findValid(direction, 1, frameHeight); }
                case west -> { return findValid(direction, 1, 1); }
                case east -> { return findValid(direction, frameWidth , 1); }
            }
        }
        return findValid(direction, currentElem.getX(), currentElem.getY());
    }

    private int findValid(Direction direction, int x, int y) {
        Element product = null;
        double minDistance = -1;
        for(Element elem : elementList) {
            double distance = 0;
            switch (direction) {
                case north -> {
                    distance = y > elem.getY() ? y - elem.getY() : y + frameHeight - elem.getY();
                    distance = Math.sqrt(Math.abs(x - elem.getX()) * 2 + distance);
                }
                case south -> {
                    distance = y < elem.getY() ? elem.getY() - y : frameHeight - y + elem.getY();
                    distance = Math.sqrt(Math.abs(x - elem.getX()) * 2 + distance);
                }
                case east -> {
                    distance = x < elem.getX() ? elem.getX() - x : frameHeight - x + elem.getX();
                    distance = Math.sqrt(Math.abs(y - elem.getY()) * 3 + distance);
                }
                case west -> {
                    distance = x > elem.getX() ? x - elem.getX() : x + frameHeight - elem.getX();
                    distance = Math.sqrt(Math.abs(y - elem.getY()) * 3 + distance);
                }
            }
            if(product == null || distance < minDistance)
                product = elem;
            if(minDistance == -1 || distance < minDistance)
                minDistance = distance;
        }
        return product.getSelectionID();
    }

    public Element getElembyID(int Id) {
        return elementList.get(Id);
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