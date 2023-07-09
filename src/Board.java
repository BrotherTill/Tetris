import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Set;

public class Board implements ActionListener {

    private static final int blockHeight = 30;
    private static final int blockWidth = 30;
    private static final int blockPadding = 1;
    private static final int totalBlockHeight = blockHeight + blockPadding*2;
    private static final int totalBlockWidth = blockWidth + blockPadding*2;

    private static final int fieldHeight = 20;
    private static final int fieldExtra = 20;
    private static final int fieldWidth = 10;
    private static Block[][] field = new Block[fieldHeight + fieldExtra][fieldWidth];

    private static int frameHeight;
    private static int frameWidth;

    private static final int fallCallRate = 50;
    private static final int fallRate = 1000;
    static Timer fallCaller;
    private static boolean softFall = false;
    private static int fallCounter = 0;

    private static FallingPiece fallingPiece;

    private static PieceUtil.types holdSlot1 = PieceUtil.types.empty;
    private static boolean hold1Used = false;
    private static PieceUtil.types holdSlot2 = PieceUtil.types.empty;
    private static boolean hold2Used = false;

    public Board(int queueGenLength) {
        fallingPiece = new FallingPiece(queueGenLength);
        for(int i = 0; i < fieldHeight + fieldExtra; i++) {
            for(int j = 0; j < fieldWidth ; j++) {
                field[i][j] = new Block();
            }
        }

        Pieces.fullLine = new Block[fieldWidth];
        for(int i = 0; i < fieldWidth ; i++) {
            Pieces.fullLine[i] = new Block(true);
        }

        fallCaller = new Timer(fallCallRate, this);
        fallCaller.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //System.out.println("action Performing");
        if(softFall) {
            pieceFall();
            fallCounter = 0;
            return;
        }
        if(fallCounter == fallRate / fallCallRate) {
            pieceFall();
            fallCounter = 0;
        } else {
            fallCounter++;
        }

    }

    private static boolean pieceFall() {
        boolean out = false;
        if(!collide(0, 1)) {
            fallingPiece.addtoY(1);
        } else {
            out = true;
            placePiece();
            checkLines();
            generateNewPiece();
        }
        return out;
    }

    private static boolean collide(int xOffset, int yOffset) {
        Block[][] pieceField = fallingPiece.getPieceField();
        int length = pieceField.length;

        for(int y=0; y < length; y++) {
            for(int x=0; x < length; x++) {
                if(!pieceField[y][x].isFilled())
                    continue;

                if(     fallingPiece.getY() + y + fieldExtra + yOffset >= fieldHeight + fieldExtra ||
                        !PieceUtil.inRange(x + fallingPiece.getX() + xOffset, 0, fieldWidth)) {
                    return true;
                }
                if(field[fallingPiece.getY() + y + fieldExtra + yOffset][fallingPiece.getX() + x + xOffset].isFilled()) {
                    return true;
                }

            }
        }
        return false;
    }

    private static void placePiece() {
        Block[][] pieceField = fallingPiece.getPieceField();
        for(int y=0; y < pieceField.length; y++) {
            for(int x=0; x < pieceField[0].length; x++) {
                if(pieceField[y][x].isFilled()) {
                    field[fallingPiece.getY() + y + fieldExtra][fallingPiece.getX() + x].setFilled(true);
                }
            }
        }
    }

    private static void checkLines() {
        int length = fallingPiece.getPieceField().length;
        int y = fallingPiece.getY();
        for(int i=0; i < length; i++) {
            if(y + i + fieldExtra < fieldHeight + fieldExtra) {
                //System.out.println(Arrays.toString(Game_field[y + i + 20]));
                if (Arrays.equals(field[y + i + fieldExtra], Pieces.fullLine)) {
                    clear_line(y + i);
                }
            }
        }
    }

    private static void clear_line(int line) {
        for (int y = line; y > 0; y--) {
            field[y + fieldExtra] = field[y + fieldExtra - 1];
        }
    }

    private static void generateNewPiece() {
        fallingPiece.nextType();
        fallingPiece.setStartPos();
        hold1Used = false;
        hold2Used = false;
    }

    public static void Pause() {
        if(fallCaller.isRunning()) {
            fallCaller.stop();
        } else {
            fallCaller.restart();
        }
    }

    public static void Hold1() {
        if(hold1Used)
            return;

        PieceUtil.types temp = holdSlot1;
        holdSlot1 = fallingPiece.getType();

        if(temp != PieceUtil.types.empty) {
            fallingPiece.setType(temp);
        } else {
            fallingPiece.nextType();
        }
        fallingPiece.setStartPos();
        hold1Used = true;
    }
    public static void Hold2() {
        if(hold2Used)
            return;

        PieceUtil.types temp = holdSlot2;
        holdSlot2 = fallingPiece.getType();

        if(temp != PieceUtil.types.empty) {
            fallingPiece.setType(temp);
        } else {
            fallingPiece.nextType();
        }
        fallingPiece.setStartPos();
        hold2Used = true;
    }

    public static void rotateCCW() {
        fallingPiece.addtoRotation(1);
        if(collide(0,0)) {
            fallingPiece.addtoRotation(-1);
        }
    }
    public static void rotateCW() {
        fallingPiece.addtoRotation(-1);
        if(collide(0,0)) {
            fallingPiece.addtoRotation(1);
        }
    }

    private static final Runnable dropThread = new Runnable() {
        @Override
        public void run() {
            for (int i = 0; i < 50; i++) {
                if (pieceFall()) {
                    return;
                }
                try {
                    Thread.sleep(4);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            generateNewPiece();
            System.out.println("failed Drop");
        }
    };

    private static int ints = 0;

    public static void HardDrop() {
        Thread myThread = new Thread(dropThread);
        myThread.start();
    }

    public static void MoveLeft() {
        if(!collide(-1,0)) {
            fallingPiece.addtoX(-1);
        }
    }
    public static void MoveRight() {
        if(!collide(1,0)) {
            fallingPiece.addtoX(1);
        }
    }



    public static void startSoftDrop() {
        softFall = true;
    }
    public static void stopSoftDrop() {
        softFall = false;
    }

    public static FallingPiece getFallingPiece() {
        return fallingPiece;
    }
    public static Block[][] getField() {
        return field;
    }
    public static int getBlockHeight() {
        return blockHeight;
    }
    public static int getBlockWidth() {
        return blockWidth;
    }
    public static int getBlockPadding() {
        return blockPadding;
    }
    public static int getTotalBlockHeight() {
        return totalBlockHeight;
    }
    public static int getTotalBlockWidth() {
        return totalBlockWidth;
    }
    public static int getFieldHeight() {
        return fieldHeight;
    }
    public static int getFieldWidth() {
        return fieldWidth;
    }
    public static int getFieldExtra() {
        return fieldExtra;
    }
    public static PieceUtil.types getHoldSlot1() {
        return holdSlot1;
    }
    public static PieceUtil.types getHoldSlot2() {
        return holdSlot2;
    }

}