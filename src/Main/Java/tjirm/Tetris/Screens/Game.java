package Main.Java.tjirm.Tetris.Screens;

import Main.Java.tjirm.Tetris.Game.Board;
import Main.Java.tjirm.Tetris.Game.GameLoop;
import Main.Java.tjirm.Tetris.Input.Menu;
import Main.Java.tjirm.Tetris.Main;
import Main.Java.tjirm.Tetris.Pieces.Block;
import Main.Java.tjirm.Tetris.Pieces.FallingPiece;
import Main.Java.tjirm.Tetris.Pieces.Piece;
import Main.Java.tjirm.Tetris.Preferences;
import Main.Java.tjirm.Tetris.Rendering.Render;
import Main.Java.tjirm.Tetris.Rendering.RenderUtil;
import Main.Java.tjirm.Tetris.Rendering.Text;
import Main.Java.tjirm.Tetris.Scoring;

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
        addBtn(new Button("Exit", 1, 1, 1, totalBlockWidth + totalBlockWidth * 5 / 2 - Text.exitWidth / 2 - Text.fontHeight / 3,
                totalBlockHeight * 21 - blockPadding * 3 - Text.fontHeight, totalBlockWidth + totalBlockWidth * 5 / 2 + Text.exitWidth / 2 + Text.fontHeight / 3,
                totalBlockHeight * 21 - blockPadding * 3 + Text.fontHeight / 3));
    }

    @Override
    public void selectionAction() {
        if (selection == 1)
            exitAction();
    }

    @Override
    public void exitAction() {
        selection = 0;
        Main.render.setCurrentListener(new Menu());
        GameLoop.game.stop();
        Render.Screen = RenderUtil.ScreenState.TryAgain;
    }

    @Override
    public void paint(Graphics g) {
        //System.out.println("Starting paint");
        drawScreen(g);          //draw the whole Tetris.Logic.Board(Game Field, Score, Held pieces, and net queue)

        if(Board.GameOver) {
            drawGameOver(g);
        }
        if(Board.GameWon) {
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
        g.drawString(Text.over, frameWidth / 2 - Text.overWidth / 2, frameHeight / 2 - frameHeight / 5 - blockPadding);
        g.setFont(Text.textFont);
        g.drawString(Text.overLevel + Scoring.getLevel(), frameWidth / 2 - (Text.overLevelWidth + Text.fontMetrics.stringWidth(String.valueOf(Scoring.getLevel()))) / 2, frameHeight / 2 - Text.fontHeight / 2 + blockPadding);
        g.drawString(Text.overScore + Scoring.getScore(), frameWidth / 2 - (Text.overScoreWidth + Text.fontMetrics.stringWidth(String.valueOf(Scoring.getScore()))) / 2, frameHeight / 2 + Text.fontHeight / 2 + blockPadding);
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
        g.drawString(Text.win, frameWidth / 2 - Text.winWidth / 2, frameHeight / 2 - Text.headerFontHeight / 2);
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
            drawField(tempPiece.getField(), 18 + tempPiece.getRxOffset(), 6.5F + tempPiece.getRyOffset() + (i * 3), g);         //you can change the y value to change the position
        }

        ////////////////////draw the Held Tetris.Pieces.Tetris.Pieces
        tempPiece = Board.getHoldSlot1().getPiece();
        drawField(tempPiece.getField(), 1 + tempPiece.getRxOffset(), 1 + tempPiece.getRyOffset(), g);
        tempPiece = Board.getHoldSlot2().getPiece();
        drawField(tempPiece.getField(), 18 + tempPiece.getRxOffset(), 1 + tempPiece.getRyOffset(), g);

        ///////////////////////// draw the borders of the Frame
        g.setColor(Preferences.Primary);
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
        g.drawString(Text.hold,totalBlockWidth + totalBlockWidth * 5 / 2 - Text.holdWidth / 2, blockHeight - blockPadding);
        g.drawString(Text.hold,totalBlockWidth * 18 + totalBlockWidth * 5 / 2 - Text.holdWidth / 2, blockHeight - blockPadding);
        g.drawString(Text.next,totalBlockWidth * 18 + totalBlockWidth * 5 / 2 - Text.nextWidth / 2,totalBlockHeight * 7 - blockPadding * 3);
        //draw Stats
        g.drawString(Text.lines,totalBlockWidth + totalBlockWidth * 5 / 2 - Text.linesWidth / 2, totalBlockHeight * 16 - blockPadding * 3);
        g.setFont(Text.textBigFont);
        if(Scoring.getLevel() == -1)
            g.drawString(Text.endless, totalBlockWidth + totalBlockWidth * 5 / 2 - Text.endlessWidth / 2,totalBlockHeight * 9 - blockPadding * 3);
        else
            g.drawString(Text.level + Scoring.getLevel(),totalBlockWidth + totalBlockWidth * 5 / 2 - (Text.levelWidth + Text.bigFontMetrics.stringWidth(String.valueOf(Scoring.getLevel()))) / 2,totalBlockHeight * 9 - blockPadding * 3);
        g.drawString(Text.score,totalBlockWidth + totalBlockWidth * 5 / 2 - Text.scoreWidth / 2,totalBlockHeight * 12 - blockPadding * 3);
        g.drawString(String.valueOf(Scoring.getScore()),totalBlockWidth + totalBlockWidth * 5 / 2 - Text.bigFontMetrics.stringWidth(String.valueOf(Scoring.getScore())) / 2,(int) (totalBlockHeight * 13.5 - blockPadding * 3));
        g.setFont(Text.textFont);
        g.drawString(String.valueOf(Scoring.getLines()),totalBlockWidth + totalBlockWidth * 5 / 2 - Text.fontMetrics.stringWidth(String.valueOf(Scoring.getLines())) / 2,totalBlockHeight * 17 - blockPadding * 3);

        if(selection == 1)
            g.setColor(Text.exitSelectionColor);
        g.drawString(Text.exit, totalBlockWidth + totalBlockWidth * 5 / 2 - Text.exitWidth / 2, totalBlockHeight * 21 - blockPadding * 3);

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

}