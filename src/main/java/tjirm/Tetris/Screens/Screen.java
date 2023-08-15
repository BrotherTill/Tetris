package main.java.tjirm.Tetris.Screens;

import main.java.tjirm.Tetris.Pieces.PieceUtil.Direction;
import main.java.tjirm.Tetris.Rendering.RenderUtil;
import main.java.tjirm.Tetris.Screens.Elements.Button;
import main.java.tjirm.Tetris.Screens.Elements.Element;

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

    private List<Button> buttonList = new ArrayList<>();
    private List<Element> elementList = new ArrayList<>();

    private int maxX = 0;
    private int maxY = 0;

    private HashMap<Integer, HashMap<Integer, Integer>> IdMap = new HashMap<>();

    public void addBtn(Button newButton) {
        buttonList.add(newButton);
        elementList.add(newButton);
        if(newButton.getX() > maxX)
            maxX = newButton.getX();
        if(newButton.getY() > maxY)
            maxY = newButton.getY();
        addToMap(newButton.getX(), newButton.getY(), newButton.getSelectionID());
    }

    private void addToMap(int x, int y, int value) {
        if(!IdMap.containsKey(y))
            IdMap.put(y, new HashMap<>());
        IdMap.get(y).put(x, value);
    }

    private int getFromMap(int x, int y) {
        if(!IdMap.containsKey(y))
            return -1;
        if(!IdMap.get(y).containsKey(x))
            return -1;
        return IdMap.get(y).get(x);
    }

    public int buttonAt(int x, int y) {
        for(int i=0; i< buttonList.size(); i++) {
            if(buttonList.get(i).inBoundingBox(x,y))
                return i;
        }
        return -1;
    }

    public int getSelectionId(Direction direction, int currentId) {
        if(buttonList.isEmpty())
            return 0;
        Button currentBtn = null;
        for (Button button : buttonList)
            if (button.getSelectionID() == currentId)
                currentBtn = button;
        if(currentBtn == null) {
            switch (direction) {
                case north -> { return findValid(direction, 1, 1); }
                case south -> { return findValid(direction, 1, frameHeight); }
                case west -> { return findValid(direction, 1, 1); }
                case east -> { return findValid(direction, frameWidth , 1); }
            }
        }
        return findValid(direction, currentBtn.getX(), currentBtn.getY());
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

    public Button getBtnbyID(int Id) {
        return buttonList.get(Id);
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
