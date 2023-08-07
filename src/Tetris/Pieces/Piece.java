package Tetris.Pieces;

public class Piece {

    private Block[][] pieceArray;

    private int startX;
    private int startY;

    private float RxOffset;
    private float RyOffset;

    private int startRotation;

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

    void setStartRotation(int startRotation) {
        this.startRotation = startRotation;
    }

    void setRotationPoint(int OrientationId, int[][] points) {
        assert points.length == 5;
        assert points[0].length == 2;
        rotationPointX[OrientationId][0] = points[0][0];
        rotationPointX[OrientationId][1] = points[1][0];
        rotationPointX[OrientationId][2] = points[2][0];
        rotationPointX[OrientationId][3] = points[3][0];
        rotationPointX[OrientationId][4] = points[4][0];
        rotationPointY[OrientationId][0] = points[0][1];
        rotationPointY[OrientationId][1] = points[1][1];
        rotationPointY[OrientationId][2] = points[2][1];
        rotationPointY[OrientationId][3] = points[3][1];
        rotationPointY[OrientationId][4] = points[4][1];
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

    public int getRotationPointX(int OrientationId, int pointId) {
        assert OrientationId <= 3 && OrientationId >= 0;
        assert pointId <= 4 && pointId >= 0;
        return rotationPointX[OrientationId][pointId];
    }

    public int getRotationPointY(int OrientationId, int pointId) {
        assert OrientationId <= 3 && OrientationId >= 0;
        assert pointId <= 5 && pointId >= 0;
        return rotationPointY[OrientationId][pointId];
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

    public int getStartRotation() {
        return startRotation;
    }

}