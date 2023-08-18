package main.java.tjirm.Tetris.Pieces;

import main.java.tjirm.Tetris.Pieces.PieceUtil.Direction;

public class Pieces {

    public static final Piece EmptyPiece = new Piece(new String[0][0], 0, 0, 0, 0);

    //add your own pieces to the pool(also add them to Tetris.Pieces.PieceUtil.types)
    public static Piece IPiece;
    public static Piece OPiece;
    public static Piece LPiece;
    public static Piece JPiece;
    public static Piece SPiece;
    public static Piece ZPiece;
    public static Piece TPiece;
    public static void init() {

        IPiece = new Piece() {
            @Override
            public void init() {
                setStartRotation(Direction.east);
                setField(new String[][]{
                        { "empty", "empty", "I1", "empty"},
                        { "empty", "empty", "I2", "empty"},
                        { "empty", "empty", "I3", "empty"},
                        { "empty", "empty", "I4", "empty"}});
                setStartX(3);
                setStartY(-2);
                setRxOffset(0.5F);
                setRyOffset(1F);
                setRotationPoint(Direction.north, new int[][]{{1,1}, {0,1}, {3,1}, {0,1}, {3,1}});
                setRotationPoint(Direction.east, new int[][]{{1,1}, {2,1}, {2,1}, {2,0}, {2,3}});
                setRotationPoint(Direction.south, new int[][]{{1,1}, {3,1}, {0,1}, {3,2}, {0,2}});
                setRotationPoint(Direction.west, new int[][]{{1,1}, {1,1}, {1,1}, {1,3}, {1,0}});
            }
        };
        OPiece = new Piece() {
            @Override
            protected void init() {
                setField(new String[][]{
                        { "O1", "O2"},
                        { "O3", "O4"}});
                setStartX(4);
                setStartY(-2);
                setRxOffset(1.5F);
                setRyOffset(1.5F);
            }

        };
        LPiece = new Piece() {
            @Override
            protected void init() {
                setField(new String[][]{
                        { "empty", "empty",   "L1"},
                        {    "L2",    "L3",   "L4"},
                        { "empty", "empty", "empty"}});
                setStartX(3);
                setStartY(-2);
                setRxOffset(1);
                setRyOffset(1.5F);
                setRotationPoint(Direction.north, new int[][]{{1,1}, {1,1}, {1,1}, {1,1}, {1,1}});
                setRotationPoint(Direction.east, new int[][]{{1,1}, {2,1}, {2,2}, {1,-1}, {2,-1}});
                setRotationPoint(Direction.south, new int[][]{{1,1}, {1,1}, {1,1}, {1,1}, {1,1}});
                setRotationPoint(Direction.west, new int[][]{{1,1}, {0,1}, {0,2}, {1,-1}, {0,-1}});
            }
        };
        JPiece = new Piece() {
            @Override
            protected void init() {
                setStartRotation(Direction.south);
                setField(new String[][]{
                        { "empty", "empty", "empty"},
                        {    "J1",    "J2",   "J3"},
                        { "empty", "empty",   "J4"}});
                setStartX(3);
                setStartY(-2);
                setRxOffset(1);
                setRyOffset(1.5F);
                setRotationPoint(Direction.north, new int[][]{{1,1}, {1,1}, {1,1}, {1,1}, {1,1}});
                setRotationPoint(Direction.east, new int[][]{{1,1}, {2,1}, {2,2}, {1,-1}, {2,-1}});
                setRotationPoint(Direction.south, new int[][]{{1,1}, {1,1}, {1,1}, {1,1}, {1,1}});
                setRotationPoint(Direction.west, new int[][]{{1,1}, {0,1}, {0,2}, {1,-1}, {0,-1}});
            }
        };
        SPiece = new Piece() {
            @Override
            protected void init() {
                setField(new String[][]{
                        { "empty",    "S1",    "S2"},
                        {    "S3",    "S4", "empty"},
                        { "empty", "empty", "empty"}});
                setStartX(3);
                setStartY(-2);
                setRxOffset(1);
                setRyOffset(1.5F);
                setRotationPoint(Direction.north, new int[][]{{1,1}, {1,1}, {1,1}, {1,1}, {1,1}});
                setRotationPoint(Direction.east, new int[][]{{1,1}, {2,1}, {2,2}, {1,-1}, {2,-1}});
                setRotationPoint(Direction.south, new int[][]{{1,1}, {1,1}, {1,1}, {1,1}, {1,1}});
                setRotationPoint(Direction.west, new int[][]{{1,1}, {0,1}, {0,2}, {1,-1}, {0,-1}});
            }
        };
        ZPiece = new Piece() {
            @Override
            protected void init() {
                setField(new String[][]{
                        {    "Z1",    "Z2", "empty"},
                        { "empty",    "Z3",    "Z4"},
                        { "empty", "empty", "empty"}});
                setStartX(3);
                setStartY(-2);
                setRxOffset(1);
                setRyOffset(1.5F);
                setRotationPoint(Direction.north, new int[][]{{1,1}, {1,1}, {1,1}, {1,1}, {1,1}});
                setRotationPoint(Direction.east, new int[][]{{1,1}, {2,1}, {2,2}, {1,-1}, {2,-1}});
                setRotationPoint(Direction.south, new int[][]{{1,1}, {1,1}, {1,1}, {1,1}, {1,1}});
                setRotationPoint(Direction.west, new int[][]{{1,1}, {0,1}, {0,2}, {1,-1}, {0,-1}});
            }
        };
        TPiece = new Piece() {
            @Override
            protected void init() {
                setField(new String[][]{
                        { "empty",    "T1", "empty"},
                        {    "T2",    "T3",    "T4"},
                        { "empty", "empty", "empty"}});
                setStartX(3);
                setStartY(-2);
                setRxOffset(1);
                setRyOffset(1.5F);
                setRotationPoint(Direction.north, new int[][]{{1,1}, {1,1}, {1,1}, {1,1}, {1,1}});
                setRotationPoint(Direction.east, new int[][]{{1,1}, {2,1}, {2,2}, {1,-1}, {2,-1}});
                setRotationPoint(Direction.south, new int[][]{{1,1}, {1,1}, {1,1}, {1,1}, {1,1}});
                setRotationPoint(Direction.west, new int[][]{{1,1}, {0,1}, {0,2}, {1,-1}, {0,-1}});
            }
        };
    }

}