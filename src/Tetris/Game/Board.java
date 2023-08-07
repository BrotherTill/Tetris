package Tetris.Game;

import Tetris.Input.Game;
import Tetris.Main;
import Tetris.Pieces.Block;
import Tetris.Pieces.FallingPiece;
import Tetris.Pieces.PieceUtil;
import Tetris.Pieces.Pieces;
import Tetris.Scoring;

import java.util.Arrays;

public class Board {

    private static final int fieldHeight = 20;
    private static final int fieldExtra = 20;
    private static final int fieldWidth = 10;
    private static final Block[][] field = new Block[fieldHeight + fieldExtra][fieldWidth];

    private static long fallRate = 1000_000000;
    private static boolean softFall = false;
    private static final long softFallRate = 50_000000;
    private static int softFallCounter = 0;
    private static long elapsedTime = 0;

    private static FallingPiece fallingPiece;
    private static int queueGenLength;

    private static PieceUtil.Type holdSlot1 = PieceUtil.Type.empty;
    private static boolean hold1Used = false;
    private static PieceUtil.Type holdSlot2 = PieceUtil.Type.empty;
    private static boolean hold2Used = false;

    public static boolean GameOver = false;
    public static boolean GameWon = false;
    public static boolean hardDropping = false;
    public static boolean blockInput = false;


    public static void initStart(int level) {
        fallingPiece = new FallingPiece(queueGenLength);
        holdSlot1 = PieceUtil.Type.empty;
        holdSlot2 = PieceUtil.Type.empty;
        generateNewPiece();

        Scoring.resetScore();
        Scoring.setLevel(level);
        for(int i = 0; i < fieldHeight + fieldExtra; i++) {
            for(int j = 0; j < fieldWidth ; j++)
                field[i][j] = new Block();
        }
        GameOver = false;
        GameWon = false;
        Main.render.setCurrentListener(new Game());
        setFallRate((int) Math.round(1000 * (Math.pow(0.8d - ((level - 1d) * 0.007d), (level - 1d)))));
        blockInput = false;
    }

    public Board(int queueGenLength) {
        Board.queueGenLength = queueGenLength;

        Pieces.fullLine = new Block[fieldWidth];
        for(int i = 0; i < fieldWidth ; i++)
            Pieces.fullLine[i] = new Block(true);
    }

    public static void resetField() {
        blockInput = true;
        fallingPiece = new FallingPiece(queueGenLength);
        holdSlot1 = PieceUtil.Type.empty;
        holdSlot2 = PieceUtil.Type.empty;
        generateNewPiece();

        //Scoring.resetScore();
        for(int i=0; i < fieldHeight; i++) {
            clear_line(fieldHeight - 1);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        for(int i = 0; i < fieldHeight + fieldExtra; i++) {
            for(int j = 0; j < fieldWidth ; j++)
                field[i][j] = new Block();
        }
        blockInput = false;
    }

    public static void updateGame(long deltaLoopTime) {
        //System.out.println("action Performing");
        System.out.print("delta: " + deltaLoopTime + "     total: ");
        elapsedTime += deltaLoopTime;
        if (softFall) {
            if (elapsedTime >= softFallRate) {
                System.out.println(elapsedTime);
                if (pieceFall()) {
                    Scoring.sendSoftDrop(softFallCounter);
                    softFallCounter = 0;
                } else
                    softFallCounter++;
                elapsedTime = 0;
            } else
                System.out.println("--");

        } else {
            if (elapsedTime >= fallRate) {
                System.out.println(elapsedTime);
                if (pieceFall()) {
                    Scoring.sendSoftDrop(softFallCounter);
                    softFallCounter = 0;
                }
                elapsedTime = 0;
            } else
                System.out.println("--");
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
            if(collide(0,0))
                GameLoop.game.GameOver();
        }
        return out;
    }

    public static boolean collide(int xOffset, int yOffset) {
        Block[][] pieceField = fallingPiece.getPieceField();
        int length = pieceField.length;

        for(int y=0; y < length; y++) {
            for(int x=0; x < length; x++) {
                if(!pieceField[y][x].isFilled())
                    continue;

                if(     fallingPiece.getY() + y + fieldExtra + yOffset >= fieldHeight + fieldExtra ||
                        !inRange(x + fallingPiece.getX() + xOffset, 0, fieldWidth))
                    return true;

                if(field[fallingPiece.getY() + y + fieldExtra + yOffset][fallingPiece.getX() + x + xOffset].isFilled())
                    return true;
            }
        }
        return false;
    }

    private static void placePiece() {
        Block[][] pieceField = fallingPiece.getPieceField();
        for(int y=0; y < pieceField.length; y++) {
            for(int x=0; x < pieceField.length; x++) {
                if(pieceField[y][x].isFilled())
                    field[fallingPiece.getY() + y + fieldExtra][fallingPiece.getX() + x].setFilled(true);
            }
        }
    }

    private static void checkLines() {
        int lines = 0;
        int y = fallingPiece.getY();
        for(int i=0; i < fallingPiece.getPieceField().length; i++) {
            if(y + i + fieldExtra < fieldHeight + fieldExtra) {
                //System.out.println(Arrays.toString(Game_field[y + i + 20]));
                if (Arrays.equals(field[y + i + fieldExtra], Pieces.fullLine)) {
                    lines++;
                    clear_line(y + i);
                }
            }
        }
        if(lines != 0)
            Scoring.sendLines(lines);
    }

    private static void clear_line(int line) {
        for (int y = line; y > 0; y--) {
            for(int i=0; i < fieldWidth; i++) {
                field[y + fieldExtra][i].setFilled(
                        field[y + fieldExtra - 1][i].isFilled());
            }
        }
    }

    private static void generateNewPiece() {
        fallingPiece.nextType();
        fallingPiece.setStartPos();
        hold1Used = false;
        hold2Used = false;
    }

    public static void Hold1() {
        if(hold1Used | blockInput)
            return;

        PieceUtil.Type temp = holdSlot1;
        holdSlot1 = fallingPiece.getType();

        fallingPiece.setType(temp);
        if(temp == PieceUtil.Type.empty)
            fallingPiece.nextType();
        fallingPiece.setStartPos();

        hold1Used = true;
    }
    public static void Hold2() {
        if(hold2Used | blockInput)
            return;

        PieceUtil.Type temp = holdSlot2;
        holdSlot2 = fallingPiece.getType();

        fallingPiece.setType(temp);
        if(temp == PieceUtil.Type.empty)
            fallingPiece.nextType();
        fallingPiece.setStartPos();

        hold2Used = true;
    }

    public static void rotateCCW() {
        RotationSystem.rotateCCW();
    }
    public static void rotateCW() {
        RotationSystem.rotateCW();
    }

    private static final Runnable dropRunnable = () -> {
        for (int i = 0; i < 50; i++) {
            if (pieceFall()) {
                Scoring.sendHardDrop(i + 1);
                hardDropping = false;
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
        hardDropping = false;
    };

    public static void HardDrop() {
        if(!hardDropping && !blockInput) {
            Thread myThread = new Thread(dropRunnable);
            myThread.start();
            hardDropping = true;
        }
    }

    public static void MoveLeft() {
        if(blockInput)
            return;
        if(!collide(-1,0)) {
            fallingPiece.addtoX(-1);
        }
    }
    public static void MoveRight() {
        if(blockInput)
            return;
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

    public static boolean inRange(int value, int min, int max) {
        return (value>= min) && (value< max);
    }

    public static void setFallRate(int fallRate) {
        System.out.println(fallRate);
        Board.fallRate = fallRate * 1000_000L;
    }

    public static FallingPiece getFallingPiece() {
        return fallingPiece;
    }
    public static Block[][] getField() {
        return field;
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
    public static PieceUtil.Type getHoldSlot1() {
        return holdSlot1;
    }
    public static PieceUtil.Type getHoldSlot2() {
        return holdSlot2;
    }

}