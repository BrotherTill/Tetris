package Tetris.Screens;

import Tetris.Game.Board;
import Tetris.Game.GameLoop;
import Tetris.Rendering.Render;
import Tetris.Rendering.RenderUtil;
import Tetris.Rendering.Text;
import Tetris.Scoring;

import java.awt.*;

public class TryAgain extends Screen {

    @Override
    public void init() {
        int TOTALBLOCKHeight = totalBlockHeight * 3;

        addBtn(new Button("Game", 1, 1, 1, (frameWidth / 2) - Text.restartWidth / 2 - Text.bigFontHeight / 3,
                TOTALBLOCKHeight * 6 - Text.bigFontHeight,(frameWidth / 2) + Text.restartWidth / 2 + Text.bigFontHeight / 3,
                TOTALBLOCKHeight * 6 + Text.bigFontHeight / 3));
        addBtn(new Button("Menu", 2, 1, 2, (frameWidth / 2) - Text.menuWidth / 2  - Text.bigFontHeight / 3,
                TOTALBLOCKHeight * 7 - Text.bigFontHeight,(frameWidth / 2) + Text.menuWidth / 2 + Text.bigFontHeight / 3,
                TOTALBLOCKHeight * 7 + Text.bigFontHeight / 3));
    }

    @Override
    public void selectionAction() {
        switch (selection) {
            case 1 -> {
                Render.Screen = RenderUtil.ScreenState.Game;
                GameLoop.game.start();
            }
            case 2 -> {
                selection = 0;
                Render.Screen = RenderUtil.ScreenState.Menu;
            }
        }
    }

    @Override
    public void exitAction() {
        Render.Screen = RenderUtil.ScreenState.Menu;
    }

    @Override
    public void paint(Graphics g) {
        int BLOCKHeight = blockHeight * 3;
        int BLOCKWidth = blockWidth * 3;
        int TOTALBLOCKHeight = totalBlockHeight * 3;
        int TOTALBLOCKWidth = totalBlockWidth * 3;
        int BLOCKPadding = blockPadding * 3;

        g.setColor(RenderUtil.Primary);
        g.fillRect(0, 0, frameWidth, frameHeight);

        g.setColor(RenderUtil.Background);

        g.setFont(Text.textHeaderFont);
        g.drawString(Text.tetris, frameWidth / 2 - Text.tetrisWidth / 2, frameHeight / 9);

        if(Board.GameOver) {
            g.drawString(Text.over, frameWidth / 2 - Text.overWidth / 2, TOTALBLOCKHeight * 3);
        } else if(Board.GameWon) {
            g.drawString(Text.win, frameWidth / 2 - Text.winWidth / 2, TOTALBLOCKHeight * 3);
        } else {
            g.fillRect((int) (frameWidth / 2 - TOTALBLOCKWidth * 1.5f - BLOCKPadding), (int) (TOTALBLOCKHeight * 2.5f), TOTALBLOCKWidth * 3 - (BLOCKPadding * 2), BLOCKHeight);
            g.fillRect((int) (frameWidth / 2 + TOTALBLOCKWidth * 0.5f - BLOCKPadding), (int) ((TOTALBLOCKHeight * 2.5f) - BLOCKHeight + BLOCKPadding ), BLOCKWidth, BLOCKHeight + BLOCKPadding);
        }
        g.setFont(Text.textFont);
        g.drawString(Text.overLevel + Scoring.getLevel(), frameWidth / 2 - TOTALBLOCKWidth - (Text.overLevelWidth + Text.fontMetrics.stringWidth(String.valueOf(Scoring.getLevel()))) / 2, TOTALBLOCKHeight * 4);
        g.drawString(Text.overScore + Scoring.getScore(), frameWidth / 2 + TOTALBLOCKWidth - (Text.overScoreWidth + Text.fontMetrics.stringWidth(String.valueOf(Scoring.getLevel()))) / 2, TOTALBLOCKHeight * 4);

        g.setFont(Text.textBigFont);
        g.drawString(Text.restart, (frameWidth / 2) - (Text.restartWidth / 2), TOTALBLOCKHeight * 6);
        g.drawString(Text.menu, (frameWidth / 2) - (Text.menuWidth / 2), TOTALBLOCKHeight * 7);

        g.setColor(RenderUtil.SELECTION);
        switch (selection) {
            case 1 ->   g.drawString(Text.restart, (frameWidth / 2) - (Text.restartWidth / 2), TOTALBLOCKHeight * 6);
            case 2 ->   g.drawString(Text.menu, (frameWidth / 2) - (Text.menuWidth / 2), TOTALBLOCKHeight * 7);
        }
    }
}