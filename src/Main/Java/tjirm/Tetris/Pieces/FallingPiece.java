package Main.Java.tjirm.Tetris.Pieces;

import Main.Java.tjirm.Tetris.Pieces.PieceUtil.Direction;
import Main.Java.tjirm.Tetris.Pieces.PieceUtil.Type;

public class FallingPiece {

    private int x;
    private int y;
    private Direction direction;

    private Type type;
    private Block[][] field;

    private Type[] nextTypes;
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

    public Direction getDirection() {
        return direction;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public Type[] getNextTypes() {
        return nextTypes;
    }

    public int getNextLength() {
        return nextLength;
    }

    public void setStartPos() {
        field = type.getPiece().getField();
        x = type.getPiece().getStartX();
        y = type.getPiece().getStartY();
        direction = Direction.north;
    }

    public Type nextType() {
        type = nextTypes[0];
        for(int i=0; i < (nextLength - 1); i++) {
            nextTypes[i] = nextTypes[i + 1];
        }
        nextTypes[nextLength - 1] = Type.randomType();

        setStartPos();
        return type;
    }

    private void initPiece(int queueGenLength) {
        nextLength = queueGenLength;
        nextTypes = new Type[nextLength];
        type = Type.randomType();
        for(int i=0; i < nextLength; i++) {
            nextTypes[i] = Type.randomType();
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
        direction = direction.getNextDirection();
    }

    public void rotateCCW() {
        field = PieceUtil.rotate(field, -1);
        direction = direction.getPreviousDirection();
    }

    public Block[][] getPieceField() {
        return field;
    }

    public Block[][] getPieceFieldbyIndex(int index) {
        return nextTypes[index - 1].getPiece().getField();
    }

}