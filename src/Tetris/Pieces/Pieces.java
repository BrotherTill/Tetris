package Tetris.Pieces;

public class Pieces {

    private static final Block filled = new Block(true);        //used to make writing of Tetris.Pieces.Tetris.Pieces easier
    private static final Block empty = new Block(false);

    public static Block[] fullLine;         //initialized in Tetris.Logic.Board

    public static final Piece EmptyPiece = new Piece(new Block[0][0], 0, 0, 0, 0);

    //add your own pieces to the pool(also add them to Tetris.Pieces.PieceUtil.types)
    public static final Piece OPiece = new Piece(new Block[][]{
            {filled, filled},
            {filled, filled}
    }, 4, -2, 1.5F, 1.5F);

    public static final Piece IPiece = new Piece(new Block[][]{
            { empty,  empty,  empty,  empty},
            { empty,  empty,  empty,  empty},
            {filled, filled, filled, filled},
            { empty,  empty,  empty,  empty}
    }, 3, -3, 0.5F, 0);

    public static final Piece LPiece = new Piece(new Block[][]{
            { empty,  empty, filled},
            {filled, filled, filled},
            { empty,  empty,  empty}
    }, 3, -2, 1, 1.5F);

    public static final Piece JPiece = new Piece(new Block[][]{
            {filled,  empty,  empty},
            {filled, filled, filled},
            { empty,  empty,  empty}
    }, 3, -2, 1, 1.5F);

    public static final Piece SPiece = new Piece(new Block[][]{
            { empty, filled, filled},
            {filled, filled,  empty},
            { empty,  empty,  empty}
    }, 3, -2, 1, 1.5F);

    public static final Piece ZPiece = new Piece(new Block[][]{
            {filled, filled,  empty},
            { empty, filled, filled},
            { empty,  empty,  empty}
    }, 3, -2, 1, 1.5F);

    public static final Piece TPiece = new Piece(new Block[][]{
            { empty, filled,  empty},
            {filled, filled, filled},
            { empty,  empty,  empty}
    }, 3, -2, 1, 1.5F);
    
}