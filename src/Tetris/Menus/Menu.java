package Tetris.Menus;

import Tetris.Rendering.RenderUtil;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Menu {

    static final Font textFont = RenderUtil.textFont;                         // = new Font("MinecraftRegular", Font.BOLD, 30);
    static final Font textBigFont = RenderUtil.textBigFont;                   // = new Font("MinecraftRegular", Font.BOLD, 45);
    static final Font textHeaderFont = RenderUtil.textHeaderFont;                   // = new Font("MinecraftRegular", Font.BOLD, 70);
    static final FontMetrics fontMetrics = RenderUtil.fontMetrics;            // = getFontMetrics(textFont);
    static final FontMetrics bigFontMetrics = RenderUtil.bigFontMetrics;      // = getFontMetrics(textBigFont);
    static final FontMetrics headerFontMetrics = RenderUtil.headerFontMetrics;      // = getFontMetrics(textBigFont);
    static final int fontHeight = RenderUtil.bigFontHeight;
    static final int bigFontHeight = RenderUtil.bigFontHeight;
    static final int headerFontHeight = RenderUtil.headerFontHeight;

    static final Color Background = RenderUtil.Background;
    static final Color SELECTION = RenderUtil.SELECTION;
    static final Color Primary = RenderUtil.Primary;

    //To modify these Variables goto Tetris.Logic.Board
    static final int blockHeight = RenderUtil.blockHeight;
    static final int blockWidth = RenderUtil.blockWidth;
    static final int blockPadding = RenderUtil.blockPadding;
    static final int totalBlockHeight = RenderUtil.totalBlockHeight;
    static final int totalBlockWidth = RenderUtil.totalBlockWidth;
    static final int frameHeight = RenderUtil.frameHeight;                  //Calculated from the Tetris.Pieces.Block height and padding
    static final int frameWidth = RenderUtil.frameWidth;                    //Calculated from the Tetris.Pieces.Block width and padding

    public int selection = 0;

    public void selectionAction() {
    }

    public void exitAction() {}

    public void paint(Graphics g) {
    }


    public Menu() {
        init();
    }

    private List<Button> buttonList = new ArrayList<>();

    private int maxX = 0;
    private int maxY = 0;

    private HashMap<Integer, HashMap<Integer, Integer>> IdMap = new HashMap<>();

    public void addBtn(Button newButton) {
        buttonList.add(newButton);
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

    public int getSelectionId(String direction, int currentId) {
        if(buttonList.isEmpty())
            return 0;
        Button currentBtn = null;
        for (Button button : buttonList) {
            if (button.getSelectionID() == currentId)
                currentBtn = button;
        }
        if(currentBtn == null) {
            switch (direction) {
                case "up" -> { return findValid(direction, 1, 1); }
                case "down" -> { return findValid(direction, 1, maxY); }
                case "left" -> { return findValid(direction, 1, 1); }
                case "right" -> { return findValid(direction, maxX , 1); }
            }
        }
        return findValid(direction, currentBtn.getX(), currentBtn.getY());
    }

    private int findValid(String direction, int x, int y) {
        switch (direction) {
            case "up" -> y--;
            case "down" -> y++;
            case "left" -> x--;
            case "right" -> x++;
        }
        int out = -1;
        while (out == -1){
            x = loopAround(x, maxX);
            y = loopAround(y, maxY);
            if(getFromMap(x, y) != -1)
                out = getFromMap(x, y);
            switch (direction) {
                case "up" -> y--;
                case "down" -> y++;
                case "left" -> x--;
                case "right" -> x++;
            }
        }
        return out;
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

    public void init() {

    }

}
