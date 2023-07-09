public class PieceUtil {

    public enum types {
        O(Pieces.OPiece),
        I(Pieces.IPiece),
        L(Pieces.LPiece),
        J(Pieces.JPiece),
        S(Pieces.SPiece),
        Z(Pieces.ZPiece),
        T(Pieces.TPiece),
        empty(Pieces.EmptyPiece);

        private types(Piece piece) { this.obj = piece; }
        private Piece obj;

        public Piece getPiece() { return obj; }

        public static types randomtype()  {
            return values()[(int) (Math.random() * (values().length - 1))];
        }
    }

    public static String fieldtoString(Block[][] inputArray) {
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

    public static Block[][] rotate(Block[][] piece, int rotation) {
        int length = piece.length;
        Block[][] out = new Block[length][length];

        for(int y=0; y < length; y++) {
            for(int x=0; x < length; x++) {
                switch (Math.abs(rotation % 4)) {
                    case 0: return piece;
                    case 1: out[x][length - 1 - y]              = piece[y][x]; break;
                    case 2: out[length - 1 - y][length - 1 - x] = piece[y][x]; break;
                    case 3: out[length - 1 - x][y]              = piece[y][x]; break;
                }
            }
        }

        return out;
    }

    public static boolean inRange(int value, int min, int max) {
        return (value>= min) && (value< max);
    }

}