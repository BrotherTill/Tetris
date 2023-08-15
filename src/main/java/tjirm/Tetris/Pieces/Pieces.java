package main.java.tjirm.Tetris.Pieces;

import main.java.tjirm.Tetris.Pieces.PieceUtil.Direction;

import static main.java.tjirm.Tetris.Pieces.Blocks.*;

public class Pieces {

    public static final Piece EmptyPiece = new Piece(new Block[0][0], 0, 0, 0, 0);

    //add your own pieces to the pool(also add them to Tetris.Pieces.PieceUtil.types)
    public static final Piece IPiece = new iPiece();
    private static class iPiece extends Piece {
        public iPiece() {
            setField(new Block[][]{
                    { empty,  empty,  empty,  empty},
                    {   GBi,    GBi,    GBi,    GBi},
                    { empty,  empty,  empty,  empty},
                    { empty,  empty,  empty,  empty}});
            setStartX(3);
            setStartY(-2);
            setRxOffset(0.5F);
            setRyOffset(1F);
            setRotationPoint(Direction.north, new int[][]{{1,1}, {0,1}, {3,1}, {0,1}, {3,1}});
            setRotationPoint(Direction.east, new int[][]{{1,1}, {2,1}, {2,1}, {2,0}, {2,3}});
            setRotationPoint(Direction.south, new int[][]{{1,1}, {3,1}, {0,1}, {3,2}, {0,2}});
            setRotationPoint(Direction.west, new int[][]{{1,1}, {1,1}, {1,1}, {1,3}, {1,0}});
        }
    }

    public static final Piece OPiece = new oPiece();
    private static class oPiece extends Piece {
        public oPiece() {
            setField(new Block[][]{
                    {   GBo,    GBo},
                    {   GBo,    GBo}});
            setStartX(4);
            setStartY(-2);
            setRxOffset(1.5F);
            setRyOffset(1.5F);
        }
    }

    public static final Piece LPiece = new lPiece();
    private static class lPiece extends Piece {
        public lPiece() {
            setField(new Block[][]{
                    { empty,  empty,    GBl},
                    {   GBl,    GBl,    GBl},
                    { empty,  empty,  empty}});
            setStartX(3);
            setStartY(-2);
            setRxOffset(1);
            setRyOffset(1.5F);
            setRotationPoint(Direction.north, new int[][]{{1,1}, {1,1}, {1,1}, {1,1}, {1,1}});
            setRotationPoint(Direction.east, new int[][]{{1,1}, {2,1}, {2,2}, {1,-1}, {2,-1}});
            setRotationPoint(Direction.south, new int[][]{{1,1}, {1,1}, {1,1}, {1,1}, {1,1}});
            setRotationPoint(Direction.west, new int[][]{{1,1}, {0,1}, {0,2}, {1,-1}, {0,-1}});
        }
    }

    public static final Piece JPiece = new jPiece();
    private static class jPiece extends Piece {
        public jPiece() {
            setField(new Block[][]{
                    {   GBj,  empty,  empty},
                    {   GBj,    GBj,    GBj},
                    { empty,  empty,  empty}});
            setStartX(3);
            setStartY(-2);
            setRxOffset(1);
            setRyOffset(1.5F);
            setRotationPoint(Direction.north, new int[][]{{1,1}, {1,1}, {1,1}, {1,1}, {1,1}});
            setRotationPoint(Direction.east, new int[][]{{1,1}, {2,1}, {2,2}, {1,-1}, {2,-1}});
            setRotationPoint(Direction.south, new int[][]{{1,1}, {1,1}, {1,1}, {1,1}, {1,1}});
            setRotationPoint(Direction.west, new int[][]{{1,1}, {0,1}, {0,2}, {1,-1}, {0,-1}});
        }
    }

    public static final Piece SPiece = new sPiece();
    private static class sPiece extends Piece {
        public sPiece() {
            setField(new Block[][]{
                    { empty,    GBs,    GBs},
                    {   GBs,    GBs,  empty},
                    { empty,  empty,  empty}});
            setStartX(3);
            setStartY(-2);
            setRxOffset(1);
            setRyOffset(1.5F);
            setRotationPoint(Direction.north, new int[][]{{1,1}, {1,1}, {1,1}, {1,1}, {1,1}});
            setRotationPoint(Direction.east, new int[][]{{1,1}, {2,1}, {2,2}, {1,-1}, {2,-1}});
            setRotationPoint(Direction.south, new int[][]{{1,1}, {1,1}, {1,1}, {1,1}, {1,1}});
            setRotationPoint(Direction.west, new int[][]{{1,1}, {0,1}, {0,2}, {1,-1}, {0,-1}});
        }
    }

    public static final Piece ZPiece = new zPiece();
    private static class zPiece extends Piece {
        public zPiece() {
            setField(new Block[][]{
                    {   GBz,    GBz,  empty},
                    { empty,    GBz,    GBz},
                    { empty,  empty,  empty}});
            setStartX(3);
            setStartY(-2);
            setRxOffset(1);
            setRyOffset(1.5F);
            setRotationPoint(Direction.north, new int[][]{{1,1}, {1,1}, {1,1}, {1,1}, {1,1}});
            setRotationPoint(Direction.east, new int[][]{{1,1}, {2,1}, {2,2}, {1,-1}, {2,-1}});
            setRotationPoint(Direction.south, new int[][]{{1,1}, {1,1}, {1,1}, {1,1}, {1,1}});
            setRotationPoint(Direction.west, new int[][]{{1,1}, {0,1}, {0,2}, {1,-1}, {0,-1}});
        }
    }

    public static final Piece TPiece = new tPiece();
    private static class tPiece extends Piece {
        public tPiece() {
            setField(new Block[][]{
                    { empty,    GBt,  empty},
                    {   GBt,    GBt,    GBt},
                    { empty,  empty,  empty}});
            setStartX(3);
            setStartY(-2);
            setRxOffset(1);
            setRyOffset(1.5F);
            setRotationPoint(Direction.north, new int[][]{{1,1}, {1,1}, {1,1}, {1,1}, {1,1}});
            setRotationPoint(Direction.east, new int[][]{{1,1}, {2,1}, {2,2}, {1,-1}, {2,-1}});
            setRotationPoint(Direction.south, new int[][]{{1,1}, {1,1}, {1,1}, {1,1}, {1,1}});
            setRotationPoint(Direction.west, new int[][]{{1,1}, {0,1}, {0,2}, {1,-1}, {0,-1}});
        }
    }
    
}