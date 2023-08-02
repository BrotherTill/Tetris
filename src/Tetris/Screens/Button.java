package Tetris.Screens;

public class Button {

    private final String name;
    private final int selectionID;

    private final int boundingBoxX1;
    private final int boundingBoxX2;
    private final int boundingBoxY1;
    private final int boundingBoxY2;

    private final int x;
    private final int y;

    public Button(String name, int selectID, int x, int y, int boundingBoxX1, int boundingBoxY1, int boundingBoxX2, int boundingBoxY2) {
        this.name = name;
        this.selectionID = selectID;
        this.x = x;
        this.y = y;
        this.boundingBoxX1 = boundingBoxX1;
        this.boundingBoxY1 = boundingBoxY1;
        this.boundingBoxX2 = boundingBoxX2;
        this.boundingBoxY2 = boundingBoxY2;
    }

    public boolean inBoundingBox(int x, int y) {
        return      boundingBoxX1 < x && boundingBoxX2 > x
                &&  boundingBoxY1 < y && boundingBoxY2 > y;
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
