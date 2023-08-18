package main.java.tjirm.Tetris.Pieces;

import main.java.tjirm.Tetris.Pieces.PieceUtil.Direction;

public class Piece {

    private Block[][] pieceArray;

    private int startX;
    private int startY;

    private float RxOffset;
    private float RyOffset;

    private Direction startRotation = Direction.north;

    private int[][] rotationPointX = new int[4][5];
    private int[][] rotationPointY = new int[4][5];

    void setField(String[][] pieceArray) {
        Block[][] out = new Block[pieceArray.length][pieceArray.length];
        for(int y=0; y < pieceArray.length; y++)
            for (int x=0; x < pieceArray.length; x++)
                out[y][x] = Blocks.get(pieceArray[y][x]);
        switch (startRotation) {
            case east -> out = PieceUtil.rotateCopy(out, -1);
            case west -> out = PieceUtil.rotateCopy(out, 1);
            case south -> out = PieceUtil.rotateCopy(out, 2);
        }
        this.pieceArray = out;
    }

    protected void init() {

    }

    void setStartX(int startX) {
        this.startX = startX;
    }
    void setStartY(int startY) {
        this.startY = startY;
    }

    void setRxOffset(float rxOffset) {
        RxOffset = rxOffset;
    }
    void setRyOffset(float ryOffset) {
        RyOffset = ryOffset;
    }

    void setStartRotation(Direction startRotation) {
        this.startRotation = startRotation;
    }

    void setRotationPoint(Direction direction, int[][] points) {
        int directionId = direction.ordinal();
        assert points.length == rotationPointX[0].length && points[0].length == 2: "Invalid Rotation Points Table Size";
        for(int i=0; i<rotationPointX[0].length; i++) {
            rotationPointX[directionId][i] = points[i][0];
            rotationPointY[directionId][i] = points[i][1];
        }
    }

    public Piece() {
        init();
    }

    public Piece(String[][] pieceArray, int startX, int startY, int rxOffset, int ryOffset) {
        setField(pieceArray);
        this.startX = startX;
        this.startY = startY;
        this.RxOffset = rxOffset;
        this.RyOffset = ryOffset;
    }

    public int getRotationPointX(Direction direction, int pointId) {
        int diretionId = direction.ordinal();
        assert pointId <= rotationPointX[0].length && pointId >= 0 : "PointId out of Range";
        return rotationPointX[diretionId][pointId];
    }

    public int getRotationPointY(Direction direction, int pointId) {
        int directionId = direction.ordinal();
        assert pointId <= rotationPointY[0].length && pointId >= 0 : "PointId out of Range";
        return rotationPointY[directionId][pointId];
    }

    public Block[][] getField() {
        Block[][] out = new Block[pieceArray.length][pieceArray.length];
        for(int y=0; y<pieceArray.length; y++)
            for(int x=0; x<pieceArray.length; x++)
                out[y][x] = pieceArray[y][x].clone();
        return out;
    }

    public Block[][] getLook() {
        return pieceArray;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public float getRxOffset() {
        return RxOffset;
    }

    public float  getRyOffset() {
        return RyOffset;
    }

    public Direction getStartRotation() {
        return startRotation;
    }
}