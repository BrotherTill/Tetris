package Main.Java.tjirm.Tetris.Pieces;

import Main.Java.tjirm.Tetris.Pieces.PieceUtil.Direction;

public class Piece {

    private Block[][] pieceArray;

    private int startX;
    private int startY;

    private float RxOffset;
    private float RyOffset;

    private Direction startRotation;

    private int[][] rotationPointX = new int[4][5];
    private int[][] rotationPointY = new int[4][5];

    void setField(Block[][] pieceArray) {
        this.pieceArray = pieceArray;
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
    }

    public Piece(Block[][] pieceArray, int startX, int startY, int rxOffset, int ryOffset) {
        this.pieceArray = pieceArray;
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