package main.java.tjirm.Tetris.Screens;

import main.java.tjirm.Tetris.Game.Board;
import main.java.tjirm.Tetris.Game.GameLoop;
import main.java.tjirm.Tetris.Preferences;
import main.java.tjirm.Tetris.Rendering.Render;
import main.java.tjirm.Tetris.Rendering.RenderUtil;
import main.java.tjirm.Tetris.Rendering.Text;
import main.java.tjirm.Tetris.Scoring;
import main.java.tjirm.Tetris.Screens.Elements.Button;

import java.awt.*;

public class TryAgain extends Screen {

    @Override
    public void init() {
        int TOTALBLOCKHeight = totalBlockHeight * 3;

        addBtn(new Button("restart", Button.bigFontSize, 1, frameWidth / 2, TOTALBLOCKHeight * 6, true, false));
        addBtn(new Button("menu", Button.bigFontSize, 2, frameWidth / 2, TOTALBLOCKHeight * 7, true, false));
    }

    @Override
    public void selectionAction() {
        switch (selection) {
            case 1 -> {
                Render.Screen = RenderUtil.ScreenState.Game;
                if(Scoring.getLevel() == Scoring.endlessLevel)
                    GameLoop.game.startLevel(Scoring.endlessLevel);
                else
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
    public void draw(Graphics g) {
        int BLOCKHeight = blockHeight * 3;
        int BLOCKWidth = blockWidth * 3;
        int TOTALBLOCKHeight = totalBlockHeight * 3;
        int TOTALBLOCKWidth = totalBlockWidth * 3;
        int BLOCKPadding = blockPadding * 3;

        g.setColor(Preferences.Primary);
        g.fillRect(0, 0, frameWidth, frameHeight);

        g.setColor(Text.color);

        g.setFont(Text.textHeaderFont);
        g.drawString(Text.getStr("tetris"), frameWidth / 2 - Text.getHeadWidth("tetris") / 2, frameHeight / 9);

        if(Board.GameOver) {
            g.drawString(Text.getStr("over"), frameWidth / 2 - Text.getHeadWidth("over") / 2, TOTALBLOCKHeight * 3);
        } else if(Board.GameWon) {
            g.drawString(Text.getStr("win"), frameWidth / 2 - Text.getHeadWidth("win") / 2, TOTALBLOCKHeight * 3);
        } else {
            g.setColor(Preferences.Background);
            g.fillRect((int) (frameWidth / 2 - TOTALBLOCKWidth * 1.5f - BLOCKPadding), (int) (TOTALBLOCKHeight * 2.5f), TOTALBLOCKWidth * 3 - (BLOCKPadding * 2), BLOCKHeight);
            g.fillRect((int) (frameWidth / 2 + TOTALBLOCKWidth * 0.5f - BLOCKPadding), (int) ((TOTALBLOCKHeight * 2.5f) - BLOCKHeight + BLOCKPadding ), BLOCKWidth, BLOCKHeight + BLOCKPadding);
        }
        g.setColor(Text.color);
        if(Scoring.getLevel() == Scoring.endlessLevel) {
            g.setFont(Text.textBigFont);
            g.drawString(Text.getStr("overScore") + Scoring.getScore(), frameWidth / 2 - (Text.getBigWidth("overScore") + Text.getBigTextWidth(Scoring.getScore())) / 2, TOTALBLOCKHeight * 4);
        } else {
            g.setFont(Text.textFont);
            int totalWidth = Text.getWidth("overLevel") + Text.getTextWidth(Scoring.getLevel()) + Text.getWidth("overScore") + Text.getTextWidth(Scoring.getLevel()) + TOTALBLOCKWidth;
            g.drawString(Text.getStr("overLevel") + Scoring.getLevel(), frameWidth / 2 - totalWidth / 2, TOTALBLOCKHeight * 4);
            g.drawString(Text.getStr("overScore") + Scoring.getScore(), frameWidth / 2 + totalWidth / 2 - (Text.getWidth("overScore") + Text.getTextWidth(Scoring.getScore())), TOTALBLOCKHeight * 4);
        }
    }
}