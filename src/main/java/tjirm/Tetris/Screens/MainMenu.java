package main.java.tjirm.Tetris.Screens;

import main.java.tjirm.Tetris.Game.GameLoop;
import main.java.tjirm.Tetris.Preferences;
import main.java.tjirm.Tetris.Rendering.Render;
import main.java.tjirm.Tetris.Rendering.RenderUtil;
import main.java.tjirm.Tetris.Rendering.Text;
import main.java.tjirm.Tetris.Scoring;
import main.java.tjirm.Tetris.Screens.Elements.Button;

import java.awt.*;

public class MainMenu extends Screen {

    @Override
    public void init() {
        int BLOCKWidth = blockWidth * 3;
        int TOTALBLOCKHeight = totalBlockHeight * 3;

        addBtn(new Button("start", Button.bigFontSize, 1, frameWidth / 2, (int) (TOTALBLOCKHeight * 4.5F), true,false));
        addBtn(new Button("endlessMd", Button.bigFontSize, 2, frameWidth / 2,  (int) (TOTALBLOCKHeight * (4.5F + 2.5F / 3F)), true, false));
        addBtn(new Button("levels", Button.bigFontSize, 3, frameWidth / 2, (int) (TOTALBLOCKHeight * (4.5F + (2.5F / 3F) * 2)), true, false));
        addBtn(new Button("options", Button.bigFontSize, 4, frameWidth / 2, TOTALBLOCKHeight * 7, true, false));
        addBtn(new Button("credits", Button.normalFontSize, 5, BLOCKWidth / 2, TOTALBLOCKHeight * 7, false, false));
        addBtn(new Button("quit", Button.normalFontSize, 6, frameWidth - BLOCKWidth, TOTALBLOCKHeight * 7, true, true));
    }

    @Override
    public void selectionAction() {
        switch (selection) {
            case 1 -> {
                Render.Screen = RenderUtil.ScreenState.Game;
                GameLoop.game.start();
            }
            case 2 -> {
                Render.Screen = RenderUtil.ScreenState.Game;
                GameLoop.game.startLevel(Scoring.endlessLevel);
            }
            case 3 -> {
                selection = 0;
                Render.Screen = RenderUtil.ScreenState.LevelSelect;
            }
            case 4 -> {
                selection = 0;
                Render.Screen = RenderUtil.ScreenState.Options;
            }
            case 5 -> {
                selection = 0;
                Render.Screen = RenderUtil.ScreenState.Credits;
            }
            case 6 -> exitAction();
        }
    }

    @Override
    public void exitAction() {
        System.exit(0);
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

        g.setColor(Preferences.Background);
        g.fillRect((int) (frameWidth / 2 - TOTALBLOCKWidth * 1.5f - BLOCKPadding), (int) (TOTALBLOCKHeight * 2.5f), TOTALBLOCKWidth * 3 - (BLOCKPadding * 2), BLOCKHeight);
        g.fillRect((int) (frameWidth / 2 + TOTALBLOCKWidth * 0.5f - BLOCKPadding), (int) ((TOTALBLOCKHeight * 2.5f) - BLOCKHeight + BLOCKPadding ), BLOCKWidth, BLOCKHeight + BLOCKPadding);

        g.setColor(Text.color);
        g.setFont(Text.textHeaderFont);
        g.drawString(Text.getStr("tetris"), frameWidth / 2 - Text.getHeadWidth("tetris") / 2, TOTALBLOCKHeight);

    }

}