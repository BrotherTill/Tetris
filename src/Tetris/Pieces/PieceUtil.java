package Tetris.Pieces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PieceUtil {

    private static ArrayList<types> GenBag = new ArrayList<>();

    public enum types {     //the types of pieces that exist(you can add you own)
        O(Pieces.OPiece),
        I(Pieces.IPiece),
        L(Pieces.LPiece),
        J(Pieces.JPiece),
        S(Pieces.SPiece),
        Z(Pieces.ZPiece),
        T(Pieces.TPiece),
        empty(Pieces.EmptyPiece);           //used for the Hold Slot

        types(Piece piece) { this.obj = piece; }        //store the corresponding Tetris.Pieces.Piece
        private final Piece obj;

        public Piece getPiece() { return obj; }

        public static types randomType()  {     //generate a random Tetris.Pieces.Piece according to the Tetris Guidelines
            if(GenBag.isEmpty()) {
                GenBag = new ArrayList<>(List.of(types.values()));
                GenBag.remove(empty);
                Collections.shuffle(GenBag);
            }
            types out = GenBag.get(0);
            GenBag.remove(0);
            return out;
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
                    case 1 -> out[x][length - 1 - y] = piece[y][x];
                    case 2 -> out[length - 1 - y][length - 1 - x] = piece[y][x];
                    case 3 -> out[length - 1 - x][y] = piece[y][x];
                }
            }
        }

        return out;
    }

}