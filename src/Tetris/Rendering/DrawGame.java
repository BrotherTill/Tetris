package Tetris.Rendering;

import java.awt.*;

import Tetris.Pieces.Block;
import Tetris.Game.Board;
import Tetris.Pieces.FallingPiece;
import Tetris.Pieces.Piece;
import Tetris.Scoring;

public class DrawGame {

    private static Font textFont = RenderUtil.textFont;                         // = new Font("MinecraftRegular", Font.PLAIN, 30);
    private static Font textBigFont = RenderUtil.textBigFont;                   // = new Font("MinecraftRegular", Font.PLAIN, 45);
    private static FontMetrics fontMetrics = RenderUtil.fontMetrics;            // = getFontMetrics(textFont);
    private static FontMetrics bigFontMetrics = RenderUtil.bigFontMetrics;      // = getFontMetrics(textBigFont);
    private static int holdWidth = RenderUtil.holdWidth;                        // = fontMetrics.stringWidth("HOLD");
    private static int nextWidth = RenderUtil.nextWidth;                        // = fontMetrics.stringWidth("NEXT");
    private static int overWidth = RenderUtil.overWidth;                        // = bigFontMetrics.stringWidth("Game Over!");
    private static int winWidth = RenderUtil.winWidth;                          // = bigFontMetrics.stringWidth("You Win!");
    private static int scoreWidth = RenderUtil.scoreWidth;                      // = bigFontMetrics.stringWidth("SCORE:");
    private static int linesWidth = RenderUtil.linesWidth;                      // = FontMetrics.stringWidth("lines:");
    private static int fontHeight = RenderUtil.bigFontHeight;
    private static int bigFontHeight = RenderUtil.bigFontHeight;

    private static final Color Background = RenderUtil.Background;
    private static final Color Primary = RenderUtil.Primary;

    //To modify these Variables goto Tetris.Logic.Board
    private static final int blockHeight = RenderUtil.blockHeight;
    private static final int blockWidth = RenderUtil.blockWidth;
    private static final int blockPadding = RenderUtil.blockPadding;
    private static final int totalBlockHeight = RenderUtil.totalBlockHeight;
    private static final int totalBlockWidth = RenderUtil.totalBlockWidth;
    private static final int frameHeight = RenderUtil.frameHeight;                  //Calculated from the Block height and padding
    private static final int frameWidth = RenderUtil.frameWidth;                    //Calculated from the Block width and padding

    private static final int fieldHeight = Board.getFieldHeight();
    private static final int fieldWidth = Board.getFieldWidth();

    private static long GameOverOpacity = Render.GameOverOpacity;           //Used as a Counter to gradually fade ou the Tetris.Logic.Board when the game ends(you can modify it if you want to change the start Opacity)
    private static final float GameOverAlpha = Render.GameOverAlpha;        //The final Value of the Opacity when the game ends in Alpha
    private static final float fadeDuration = Render.fadeDuration;          //The time to fade out the Tetris.Logic.Board in Milliseconds

    private static FallingPiece fallingPiece;

    public static void paintGame(Graphics g) {
        //System.out.println("Starting paint");
        drawScreen(g);          //draw the whole Tetris.Logic.Board(Game Field, Score, Held pieces, and net queue)

        if(Board.GameOver) {
            drawGameOver(g);
        }
        if(Board.GameWon) {
            drawGameWon(g);
        }
    }

    public static void drawGameOver(Graphics g) {
        g.setColor(new Color(14, 14, 14, (int) GameOverOpacity));
        g.fillRect(0, 0, frameWidth, frameHeight);
        if(GameOverOpacity <= GameOverAlpha) {              //increase the Opacity of the overlay depending on deltaTime
            GameOverOpacity += (GameOverAlpha / fadeDuration) * (float) Render.deltaTime.toMillis();       //makes sure fadeDuration is consistent and correct
            return;                                         //return to skip the rest of the code
        }
        g.setColor(new Color(203, 5, 5));           //Text color
        g.setFont(textBigFont);
        g.drawString("GameOver!", (frameWidth / 2) - (overWidth / 2), (frameHeight / 2) - (frameHeight / 5) - blockPadding);
        g.setFont(textFont);
        g.drawString("Level: " + Scoring.getLevel(), (frameWidth / 2) - (RenderUtil.fontMetrics.stringWidth("Level: " + Scoring.getLevel()) / 2), (frameHeight / 2) - (fontHeight / 2) + blockPadding);
        g.drawString("Score: " + Scoring.getScore(), (frameWidth / 2) - (RenderUtil.fontMetrics.stringWidth("Score: " + Scoring.getScore()) / 2), (frameHeight / 2) + (fontHeight / 2) + blockPadding);
    }
    public static void drawGameWon(Graphics g) {
        g.setColor(new Color(14, 14, 14, (int) GameOverOpacity));
        g.fillRect(0, 0, frameWidth, frameHeight);
        if(GameOverOpacity <= GameOverAlpha) {              //increase the Opacity of the overlay depending on deltaTime
            GameOverOpacity += (GameOverAlpha / fadeDuration) * (float) Render.deltaTime.toMillis();       //makes sure fadeDuration is consistent and correct
            return;                                         //return to skip the rest of the code
        }
        g.setColor(new Color(81, 220, 11));         //Text color
        g.setFont(textBigFont);
        g.drawString("You Win!", (frameWidth / 2) - (winWidth / 2), (frameHeight / 2) - (bigFontHeight / 2));

    }

    public static void drawScreen(Graphics g) {
        fallingPiece = Board.getFallingPiece();
        int nextLength = fallingPiece.getNextLength();          //get the Next queue Length
        Piece tempPiece;

        g.setColor(Background);
        g.fillRect(0,0,frameWidth,frameHeight);

        ///////////////////////// draw the borders of the Frame
        g.setColor(Primary);
        g.fillRect(0,0, frameWidth, blockHeight + blockPadding);
        g.fillRect(0,0, blockWidth + blockPadding, frameHeight);
        g.fillRect(frameWidth - blockWidth - blockPadding,0, blockHeight + blockPadding, frameHeight);
        g.fillRect(0, frameHeight - blockHeight - blockPadding, frameWidth, blockHeight + blockPadding);
        g.fillRect(totalBlockWidth * 6 + blockPadding, 0, blockWidth, frameHeight);
        g.fillRect(blockWidth + blockPadding, totalBlockHeight * 6 + blockPadding, totalBlockWidth * 6 - blockWidth, totalBlockHeight * 15);
        g.fillRect(totalBlockWidth * 17 + blockPadding, 0, blockWidth, frameHeight);
        g.fillRect(totalBlockWidth * 18 - blockPadding, totalBlockHeight * 6 + blockPadding, totalBlockWidth * 6 - blockWidth, blockHeight);
        if(nextLength > (fieldHeight - 8) / 3) {            //make sure Next queue Length isn't above 4
            nextLength = (fieldHeight - 8) / 3;
        }
        g.fillRect(totalBlockWidth * 18 - blockPadding, (8 + (nextLength * 3)) * totalBlockHeight + blockPadding, totalBlockWidth * 6 - blockWidth, (13 - (nextLength * 3)) * totalBlockHeight);

        //////////////////////Draw Text     !!Important when changing the Displayed Text make sure to change the width variables in initFont() too!!
        g.setColor(Background);
        g.setFont(textFont);
        g.drawString("HOLD",(totalBlockWidth) + (totalBlockWidth * 5 / 2) - (holdWidth / 2), blockHeight - blockPadding);
        g.drawString("HOLD",(totalBlockWidth * 18) + (totalBlockWidth * 5 / 2) - (holdWidth / 2), blockHeight - blockPadding);
        g.drawString("NEXT",(totalBlockWidth * 18) + (totalBlockWidth * 5 / 2) - (nextWidth / 2),totalBlockHeight * 7 - blockPadding * 3);
        //draw Stats
        g.drawString("lines:",(totalBlockWidth) + (totalBlockWidth * 5 / 2) - (linesWidth / 2),totalBlockHeight * 16 - blockPadding * 3);
        g.setFont(textBigFont);
        g.drawString("LEVEL " + Scoring.getLevel(),(totalBlockWidth) + (totalBlockWidth * 5 / 2) - (bigFontMetrics.stringWidth("LEVEL " + Scoring.getLevel()) / 2),totalBlockHeight * 9 - blockPadding * 3);
        g.drawString("SCORE:",(totalBlockWidth) + (totalBlockWidth * 5 / 2) - (scoreWidth / 2),totalBlockHeight * 12 - blockPadding * 3);
        g.drawString(String.valueOf(Scoring.getScore()),(totalBlockWidth) + (totalBlockWidth * 5 / 2) - (bigFontMetrics.stringWidth(String.valueOf(Scoring.getScore())) / 2),(int) (totalBlockHeight * 13.5 - blockPadding * 3));
        g.setFont(textFont);
        g.drawString(String.valueOf(Scoring.getLines()),(totalBlockWidth) + (totalBlockWidth * 5 / 2) - (fontMetrics.stringWidth(String.valueOf(Scoring.getLines())) / 2),totalBlockHeight * 17 - blockPadding * 3);

        //

        g.setColor(Primary);

        drawField(Board.getField(), 7, -19, g);         //draw the Game Field in the middle

        drawField(fallingPiece.getPieceField(), (7 + fallingPiece.getX()), (1 + fallingPiece.getY()), g);       //draw the Falling Tetris.Pieces.Piece

        for(int i=0; i < nextLength; i++) {         //draw the Tetris.Pieces.Tetris.Pieces in the Next queue
            tempPiece = fallingPiece.getNextTypes()[i].getPiece();
            drawField(tempPiece.getField(), 18 + tempPiece.getRxOffset(), 6.5F + tempPiece.getRyOffset() + (i * 3), g);         //you can change the y value to change the position
        }

        ////////////////////draw the Held Tetris.Pieces.Tetris.Pieces
        tempPiece = Board.getHoldSlot1().getPiece();
        drawField(tempPiece.getField(), 1 + tempPiece.getRxOffset(), 1 + tempPiece.getRyOffset(), g);
        tempPiece = Board.getHoldSlot2().getPiece();
        drawField(tempPiece.getField(), 18 + tempPiece.getRxOffset(), 1 + tempPiece.getRyOffset(), g);

    }

    private static void drawField(Block[][] field, float x, float y, Graphics g) {
        for(int rY=0; rY < field.length; rY++) {
            for(int rX=0; rX < field[0].length; rX++) {
                if(field[rY][rX].isFilled()) {
                    drawFieldBlock(field, x, y, rX, rY, g);
                }
            }
        }
    }

    private static void drawFieldBlock(Block[][] field, float x, float y, int rX, int rY, Graphics g) {            //draw the Tetris.Pieces.Block of a field and connect it with adjacent ones
        g.fillRect((int) ((x + rX) * totalBlockWidth + blockPadding),(int) ((y + rY) * totalBlockHeight + blockPadding), blockWidth, blockHeight);

        if(rX != field[0].length - 1) {             //make sure it's not out of bounds
            if(field[rY][rX + 1].isFilled()) {      //connect with the Tetris.Pieces.Block to the right
                g.fillRect((int) ((x + rX + 1) * totalBlockWidth - blockPadding), (int) ((y + rY) * totalBlockHeight + blockPadding), blockPadding * 2, blockHeight);
            }
        }
        if(rY != field.length - 1) {                //make sure it's not out of bounds
            if(field[rY + 1][rX].isFilled()) {      //connect with the Tetris.Pieces.Block below
                g.fillRect((int) ((x + rX) * totalBlockWidth + blockPadding), (int) ((y + rY + 1) * totalBlockHeight - blockPadding), blockWidth, blockPadding * 2);
            }
        }
        if(rX != field[0].length - 1 && rY != field.length - 1) {           //make sure it's not out of bounds
            if(     field[rY + 1][rX + 1].isFilled() &&                     //connect with the block to the down-right diagonal
                    field[rY][rX + 1].isFilled() &&
                    field[rY + 1][rX].isFilled()) {
                g.fillRect((int) ((x + rX + 1) * totalBlockWidth -blockPadding), (int) ((y + rY + 1) * totalBlockHeight - blockPadding), blockPadding * 2, blockPadding * 2);
            }
        }
    }

}