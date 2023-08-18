package main.java.tjirm.Tetris.Pieces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PieceUtil {

    private static ArrayList<Type> GenBag = new ArrayList<>();

    public enum Type {     //the types of pieces that exist(you can add you own)
        O,
        I,
        L,
        J,
        S,
        Z,
        T,
        empty;           //used for the Hold Slot

        public Piece getPiece() {
            switch (ordinal()) {
                case 0 -> {
                    return Pieces.OPiece;
                }
                case 1 -> {
                    return Pieces.IPiece;
                }
                case 2 -> {
                    return Pieces.LPiece;
                }
                case 3 -> {
                    return Pieces.JPiece;
                }
                case 4 -> {
                    return Pieces.SPiece;
                }
                case 5 -> {
                    return Pieces.ZPiece;
                }
                case 6 -> {
                    return Pieces.TPiece;
                }
            }
            return Pieces.EmptyPiece;
        }

        public static Type randomType()  {     //generate a random Piece according to the Tetris Guidelines
            if(GenBag.isEmpty()) {
                GenBag = new ArrayList<>(List.of(Type.values()));
                GenBag.remove(empty);
                Collections.shuffle(GenBag);
            }
            Type out = GenBag.get(0);
            GenBag.remove(0);
            return out;
        }
    }

    public enum Direction {
        north(0),
        east(90),
        south(180),
        west(270);

        private final int angle;
        Direction(int angle) {
            this.angle = angle;
        }
        public int getAngle() {
            return angle;
        }
        public Direction getNextDirection() {
            return values()[(ordinal() + 1) % 4];
        }
        public Direction getPreviousDirection() {
            return values()[Math.abs((ordinal() - 1) % 4)];
        }
    }

    public static String fieldtoString(Block[][] inputArray) {      //used for debugging
        StringBuilder out = new StringBuilder();
        out.append("{");
        for(int y=0; y < inputArray[0].length; y++) {
            for(int x=0; x < inputArray.length; x++) {
                if (inputArray[y][x].isFilled()) {
                    out.append("#, ");
                } else {
                    out.append(" , ");
                }
            }
            out.delete(out.length() - 2, out.length());
            out.append("},\n{");
        }
        out.delete(out.length() - 3, out.length());
        return out.toString();
    }

    public static Block[][] rotate(Block[][] piece, int rotation) {         //rotate a Tetris.Pieces.Piece based on a rotation "index"
        int length = piece.length;
        Block[][] out = new Block[length][length];

        for(int y=0; y < length; y++) {
            for(int x=0; x < length; x++) {
                switch (Math.abs(Math.floorMod(rotation, 4))) {
                    case 0 -> {
                        return piece;
                    }
                    case 1 -> {
                        out[x][length - 1 - y] = piece[y][x];
                        out[x][length - 1 - y].rotateCW();
                    }
                    case 2 -> {
                        out[length - 1 - y][length - 1 - x] = piece[y][x];
                        out[length - 1 - y][length - 1 - x].rotateCW();
                        out[length - 1 - y][length - 1 - x].rotateCW();
                    }
                    case 3 -> {
                        out[length - 1 - x][y] = piece[y][x];
                        out[length - 1 - x][y].rotateCCW();
                    }
                }
            }
        }

        return out;
    }

    public static Block[][] rotateCopy(Block[][] piece, int rotation) {         //rotate a Tetris.Pieces.Piece based on a rotation "index"
        int length = piece.length;
        Block[][] out = new Block[length][length];

        for(int y=0; y < length; y++) {
            for(int x=0; x < length; x++) {
                switch (Math.abs(Math.floorMod(rotation, 4))) {
                    case 0 -> {
                        return piece.clone();
                    }
                    case 1 -> {
                        out[x][length - 1 - y] = piece[y][x].clone();
                        out[x][length - 1 - y].rotateCW();
                    }
                    case 2 -> {
                        out[length - 1 - y][length - 1 - x] = piece[y][x].clone();
                        out[length - 1 - y][length - 1 - x].rotateCW();
                        out[length - 1 - y][length - 1 - x].rotateCW();
                    }
                    case 3 -> {
                        out[length - 1 - x][y] = piece[y][x].clone();
                        out[length - 1 - x][y].rotateCCW();
                    }
                }
            }
        }

        return out;
    }

}