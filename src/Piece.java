public class Piece {

    private Block[][] pieceArray;

    private final int startX;
    private final int startY;

    private final float RxOffset;
    private final float RyOffset;

    private int startRotation;

    public Piece(Block[][] pieceArray, int startX, int startY, float RxOffset, float RyOffset) {
        this.pieceArray = pieceArray;
        this.startX = startX;
        this.startY = startY;
        this.RxOffset = RxOffset;
        this.RyOffset = RyOffset;
        this.startRotation = 0;
    }

    public Piece(Block[][] pieceArray, int startX, int startY, float RxOffset, float RyOffset, int startRotation) {
        this.pieceArray = pieceArray;
        this.startX = startX;
        this.startY = startY;
        this.RxOffset = RxOffset;
        this.RyOffset = RyOffset;
        this.startRotation = startRotation;
    }

    public Block[][] getField() {
        return pieceArray;
    }

    public void setPiece(Block[][] pieceArray) {
        this.pieceArray = pieceArray;
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

    public void setStartRotation(int startRotation) {
        this.startRotation = startRotation;
    }

}