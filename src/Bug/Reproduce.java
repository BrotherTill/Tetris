package Bug;

import java.util.Arrays;

public class Reproduce {

    //"The best solution to a problem is usually the easiest one." - GLaDOS
    // True

    private static boolean[][] field = new boolean[40][10];

    private static int xOffset = 4;
    private static int yOffset = 10;

    private static boolean[][] piece = new boolean[][] {
            {false, false, true},
            {true, true, true},
            {false, false, false}
    };

    private static void init() {
        for(int y=0; y < field.length; y++) {
            for(int x=0; x < field[0].length; x++) {
                field[y][x] = false;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        init();

        while (true) {
            placePiece();
            checkBug();

            Thread.sleep(5);
        }

    }

    private static int increment = 0;
    private static void placePiece() {
        System.out.println(increment++);
        boolean[][] pieceField = piece;
        for(int y=0; y < pieceField.length; y++) {
            for(int x=0; x < pieceField[0].length; x++) {
                if(pieceField[y][x]) {
                    field[yOffset + y + 20][xOffset + x] = true;
                }
            }
        }
    }

    private static void checkBug() {
        if (field[29][5]) {
            StringBuilder out = new StringBuilder();
            out.append("{");
            for (int y = 0; y < field[0].length; y++) {
                for (int x = 0; x < field.length; x++) {
                    if (field[y][x]) {
                        out.append("#, ");
                    } else {
                        out.append(" , ");
                    }
                }
                out.delete(out.length() - 2, out.length());
                out.append("},\n{");
            }
            out.delete(out.length() - 3, out.length());
            System.out.println(out.toString());
        }
    }
}
