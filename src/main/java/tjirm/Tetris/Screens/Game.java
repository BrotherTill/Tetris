package main.java.tjirm.Tetris.Screens;

import main.java.tjirm.Tetris.Game.Board;
import main.java.tjirm.Tetris.Game.GameLoop;
import main.java.tjirm.Tetris.Input.Menu;
import main.java.tjirm.Tetris.Main;
import main.java.tjirm.Tetris.Pieces.Block;
import main.java.tjirm.Tetris.Pieces.FallingPiece;
import main.java.tjirm.Tetris.Pieces.Piece;
import main.java.tjirm.Tetris.Preferences;
import main.java.tjirm.Tetris.Rendering.Render;
import main.java.tjirm.Tetris.Rendering.Text;
import main.java.tjirm.Tetris.Scoring;
import main.java.tjirm.Tetris.Screens.Elements.Button;

import java.awt.*;

public class Game extends Screen {

    private static final int fieldHeight = Board.getFieldHeight();
    private static final int fieldWidth = Board.getFieldWidth();

    private static long GameOverOpacity = Render.GameOverOpacity;           //Used as a Counter to gradually fade ou the Tetris.Logic.Board when the game ends(you can modify it if you want to change the start Opacity)
    private static final float GameOverAlpha = Render.GameOverAlpha;        //The final Value of the Opacity when the game ends in Alpha
    private static final float fadeDuration = Render.fadeDuration;          //The time to fade out the Tetris.Logic.Board in Milliseconds

    private static FallingPiece fallingPiece;

    @Override
    public void init() {
        addBtn("Exit", "exit", Button.normalFontSize, totalBlockWidth + totalBlockWidth * 5 / 2, totalBlockHeight * 21 - blockPadding * 3, true, true);
    }

    @Override
    protected void selectionAction() {
        if (selection.equals("Exit"))
            exitAction();
    }

    @Override
    public void exitAction() {
        selection = "null";
        Main.render.setCurrentListener(new Menu());
        GameLoop.game.stop();
        Screens.setScreen(Screens.ScreenState.TryAgain);
    }

    @Override
    protected void draw(Graphics g) {
        //System.out.println("Starting paint");
        drawScreen(g);          //draw the whole Tetris.Logic.Board(Game Field, Score, Held pieces, and net queue)

        if(Board.GameOver) {
            ((Button) getElementbyName("Exit")).setActive(false);
            drawGameOver(g);
        }
        if(Board.GameWon) {
            ((Button) getElementbyName("Exit")).setActive(false);
            drawGameWon(g);
        }
    }

    private void drawGameOver(Graphics g) {
        g.setColor(new Color(14, 14, 14, (int) GameOverOpacity));
        g.fillRect(0, 0, frameWidth, frameHeight);
        if(GameOverOpacity <= GameOverAlpha) {              //increase the Opacity of the overlay depending on deltaTime
            GameOverOpacity += (long) ((GameOverAlpha / fadeDuration) * (float) Render.deltaTime.toMillis());       //makes sure fadeDuration is consistent and correct
            return;                                         //return to skip the rest of the code
        }
        g.setColor(new Color(203, 5, 5));           //Text color
        g.setFont(Text.textHeaderFont);
        g.drawString(Text.getStr("over"), frameWidth / 2 - Text.getHeadWidth("over") / 2, frameHeight / 2 - frameHeight / 5 - blockPadding);
        if(Scoring.getLevel() == Scoring.endlessLevel) {
            g.setFont(Text.textBigFont);
            g.drawString(Text.getStr("overScore") + Scoring.getScore(), frameWidth / 2 - (Text.getBigWidth("overScore") + Text.getBigTextWidth(Scoring.getScore())) / 2, frameHeight / 2 + blockPadding);
        } else {
            g.setFont(Text.textFont);
            g.drawString(Text.getStr("overLevel") + Scoring.getLevel(), frameWidth / 2 - (Text.getWidth("overLevel") + Text.getTextWidth(Scoring.getLevel())) / 2, frameHeight / 2 - Text.fontHeight / 2 + blockPadding);
            g.drawString(Text.getStr("overScore") + Scoring.getScore(), frameWidth / 2 - (Text.getWidth("overScore") + Text.getTextWidth(Scoring.getScore())) / 2, frameHeight / 2 + Text.fontHeight / 2 + blockPadding);
        }
    }
    private void drawGameWon(Graphics g) {
        g.setColor(new Color(14, 14, 14, (int) GameOverOpacity));
        g.fillRect(0, 0, frameWidth, frameHeight);
        if(GameOverOpacity <= GameOverAlpha) {              //increase the Opacity of the overlay depending on deltaTime
            GameOverOpacity += (long) ((GameOverAlpha / fadeDuration) * (float) Render.deltaTime.toMillis());       //makes sure fadeDuration is consistent and correct
            return;                                         //return to skip the rest of the code
        }
        g.setColor(new Color(81, 220, 11));         //Text color
        g.setFont(Text.textHeaderFont);
        g.drawString(Text.getStr("win"), frameWidth / 2 - Text.getHeadWidth("win") / 2, frameHeight / 2 - Text.headerFontHeight / 2);
    }

    private void drawScreen(Graphics g) {
        fallingPiece = Board.getFallingPiece();
        int nextLength = fallingPiece.getNextLength();          //get the Next queue Length
        Piece tempPiece;

        g.setColor(Preferences.Background);
        g.fillRect(0,0,frameWidth,frameHeight);

        ///////////////////////draw the Blocks and Pieces
        drawField(Board.getField(), 7, -19, g);         //draw the Game Field in the middle

        drawGhostField(fallingPiece.getPieceField(), 7 + fallingPiece.getX(), 1 + fallingPiece.getY(), g);        //draw the GhostPiece
        drawField(fallingPiece.getPieceField(), (7 + fallingPiece.getX()), (1 + fallingPiece.getY()), g);               //draw the Falling Piece

        for(int i=0; i < nextLength; i++) {         //draw the Tetris.Pieces.Tetris.Pieces in the Next queue
            tempPiece = fallingPiece.getNextTypes()[i].getPiece();
            drawField(tempPiece.getLook(), 18 + tempPiece.getRxOffset(), 6.5F + tempPiece.getRyOffset() + (i * 3), g);         //you can change the y value to change the position
        }

        ////////////////////draw the Held Tetris.Pieces.Tetris.Pieces
        tempPiece = Board.getHoldSlot1().getPiece();
        drawField(tempPiece.getField(), 1 + tempPiece.getRxOffset(), 1 + tempPiece.getRyOffset(), g);
        tempPiece = Board.getHoldSlot2().getPiece();
        drawField(tempPiece.getField(), 18 + tempPiece.getRxOffset(), 1 + tempPiece.getRyOffset(), g);

        ///////////////////////// draw the borders of the Frame
        g.setColor(Preferences.Frame);
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
        g.fillRect(totalBlockWidth * 18 - blockPadding, (8 + (nextLength * 3)) * totalBlockHeight + blockPadding, totalBlockWidth * 6 - blockWidth, (13 - nextLength * 3) * totalBlockHeight);

        //////////////////////Draw Text     !!Important when changing the Displayed Text make sure to change the width variables in initFont() too!!
        g.setColor(Text.color);
        g.setFont(Text.textFont);
        g.drawString(Text.getStr("hold"),totalBlockWidth + totalBlockWidth * 5 / 2 - Text.getWidth("hold") / 2, blockHeight - blockPadding);
        g.drawString(Text.getStr("hold"),totalBlockWidth * 18 + totalBlockWidth * 5 / 2 - Text.getWidth("hold") / 2, blockHeight - blockPadding);
        g.drawString(Text.getStr("next"),totalBlockWidth * 18 + totalBlockWidth * 5 / 2 - Text.getWidth("next") / 2,totalBlockHeight * 7 - blockPadding * 3);
        //draw Stats
        g.drawString(Text.getStr("lines"),totalBlockWidth + totalBlockWidth * 5 / 2 - Text.getWidth("lines") / 2, totalBlockHeight * 16 - blockPadding * 3);
        g.setFont(Text.textBigFont);
        if(Scoring.getLevel() == -1)
            g.drawString(Text.getStr("endless"), totalBlockWidth + totalBlockWidth * 5 / 2 - Text.getBigWidth("endless") / 2,totalBlockHeight * 9 - blockPadding * 3);
        else
            g.drawString(Text.getStr("level") + Scoring.getLevel(),totalBlockWidth + totalBlockWidth * 5 / 2 - (Text.getBigWidth("level") + Text.getBigTextWidth(Scoring.getLevel())) / 2,totalBlockHeight * 9 - blockPadding * 3);
        g.drawString(Text.getStr("score"),totalBlockWidth + totalBlockWidth * 5 / 2 - Text.getBigWidth("score") / 2,totalBlockHeight * 12 - blockPadding * 3);
        g.drawString(String.valueOf(Scoring.getScore()),totalBlockWidth + totalBlockWidth * 5 / 2 - Text.getBigTextWidth(Scoring.getScore()) / 2,(int) (totalBlockHeight * 13.5 - blockPadding * 3));
        g.setFont(Text.textFont);
        g.drawString(String.valueOf(Scoring.getLines()),totalBlockWidth + totalBlockWidth * 5 / 2 - Text.getTextWidth(Scoring.getLines()) / 2,totalBlockHeight * 17 - blockPadding * 3);

        //

    }

    private void drawField(Block[][] field, float x, float y, Graphics g) {
        for(int rY=0; rY < field.length; rY++) {
            for(int rX=0; rX < field[0].length; rX++) {
                field[rY][rX].paint(g, (int) ((x + rX) * totalBlockWidth), (int) ((y + rY) * totalBlockHeight), field, rX, rY);
            }
        }
    }

    private void drawGhostField(Block[][] field, float x, float y, Graphics g) {
        if(Board.blockInput || !Preferences.showGhost)
            return;
        for(int i=0; i<fieldHeight + 3; i++) {
            if(!Board.collide(0, i+1))
                continue;
            for (int rY = 0; rY < field.length; rY++) {
                for (int rX = 0; rX < field[0].length; rX++) {
                    field[rY][rX].paintGhost((Graphics2D) g, (int) ((x + rX) * totalBlockWidth), (int) ((y + rY + i) * totalBlockHeight), field, rX, rY);
                }
            }
            break;
        }

    }

    public static void resetOpacity() {
        GameOverOpacity = 0;
    }

}