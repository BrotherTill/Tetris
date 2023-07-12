public class Piece {

    private Block[][] pieceArray;

    private final int startX;
    private final int startY;

    private final float RxOffset;
    private final float RyOffset;

    private int startRotation;

    /**
     * create a Piece with a Field in the pieceArray. Make sure that the number of rows matches the number of lines.
     * the start Position Values are offsets for the 0 | 0 point(top left corner)
     * The Render Offsets are for the next queue and are also offsets from the top-left corner
     *
     * @author me
     */
    public Piece(Block[][] pieceArray, int startX, int startY, float RxOffset, float RyOffset) {
        this.pieceArray = pieceArray;
        this.startX = startX;
        this.startY = startY;
        this.RxOffset = RxOffset;
        this.RyOffset = RyOffset;
        this.startRotation = 0;
    }

    /**
     * create a Piece with a Field in the pieceArray. Make sure that the number of rows matches the number of lines.
     * the start Position Values are offsets for the 0 | 0 point(top left corner)
     * The Render Offsets are for the next queue and are also offsets from the top-left corner
     *
     * @author me
     */
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