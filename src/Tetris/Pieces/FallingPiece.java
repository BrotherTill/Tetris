package Tetris.Pieces;

public class FallingPiece {

    private int x;
    private int y;
    private int rotation;

    private PieceUtil.types type;
    private Block[][] field;

    private PieceUtil.types[] nextTypes;
    private int nextLength;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void addtoX(int adder) {
        this.x += adder;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void addtoY(int adder) {
        this.y += adder;
    }

    public int getRotation() {
        return rotation;
    }

    public void setType(PieceUtil.types type) {
        this.type = type;
    }

    public PieceUtil.types getType() {
        return type;
    }

    public PieceUtil.types[] getNextTypes() {
        return nextTypes;
    }

    public int getNextLength() {
        return nextLength;
    }

    public void setStartPos() {
        field = type.getPiece().getField();
        x = type.getPiece().getStartX();
        y = type.getPiece().getStartY();
        rotation = 0;
    }

    public PieceUtil.types nextType() {
        type = nextTypes[0];
        for(int i=0; i < (nextLength - 1); i++) {
            nextTypes[i] = nextTypes[i + 1];
        }
        nextTypes[nextLength - 1] = PieceUtil.types.randomType();

        setStartPos();
        return type;
    }

    private void initPiece(int queueGenLength) {
        nextLength = queueGenLength;
        nextTypes = new PieceUtil.types[nextLength];
        type = PieceUtil.types.randomType();
        for(int i=0; i < nextLength; i++) {
            nextTypes[i] = PieceUtil.types.randomType();
        }
        setStartPos();
    }

    public FallingPiece() {
        initPiece(10);
    }
    public FallingPiece(int queueGenLength) {
        initPiece(queueGenLength);
    }
    public FallingPiece(int x, int y) {
        initPiece(10);
        setX(x);
        setY(y);
    }
    public FallingPiece(int queueGenLength, int x, int y) {
        initPiece(queueGenLength);
        setX(x);
        setY(y);
    }

    public void rotateCW() {
        field = PieceUtil.rotate(field, 1);
        rotation++;
    }

    public void rotateCCW() {
        field = PieceUtil.rotate(field, -1);
        rotation--;
    }

    public Block[][] getPieceField() {
        return field;
    }

    public Block[][] getPieceFieldbyIndex(int index) {
        return nextTypes[index - 1].getPiece().getField();
    }

}