package main.java.tjirm.Tetris.Screens;

import main.java.tjirm.Tetris.Game.GameLoop;
import main.java.tjirm.Tetris.Preferences;
import main.java.tjirm.Tetris.Rendering.Text;
import main.java.tjirm.Tetris.Scoring;
import main.java.tjirm.Tetris.Screens.Elements.Button;

import java.awt.*;

public class MainMenu extends Screen {

    @Override
    public void init() {
        int BLOCKWidth = blockWidth * 3;
        int TOTALBLOCKHeight = totalBlockHeight * 3;

        addBtn("Start", "start", Button.bigFontSize, frameWidth / 2, (int) (TOTALBLOCKHeight * 4.5F), true,false);
        addBtn("Endless", "endlessMd", Button.bigFontSize, frameWidth / 2,  (int) (TOTALBLOCKHeight * (4.5F + 2.5F / 3F)), true, false);
        addBtn("Levels", "levels", Button.bigFontSize, frameWidth / 2, (int) (TOTALBLOCKHeight * (4.5F + (2.5F / 3F) * 2)), true, false);
        addBtn("Options", "options", Button.bigFontSize, frameWidth / 2, TOTALBLOCKHeight * 7, true, false);
        addBtn("Credits", "credits", Button.normalFontSize, BLOCKWidth / 2, TOTALBLOCKHeight * 7, false, false);
        addBtn("Quit", "quit", Button.normalFontSize, frameWidth - BLOCKWidth, TOTALBLOCKHeight * 7, true, true);
    }

    @Override
    protected void selectionAction() {
        switch (selection) {
            case "Start" -> {
                Screens.setScreen(Screens.ScreenState.Game);
                GameLoop.game.start();
            }
            case "Endless" -> {
                Screens.setScreen(Screens.ScreenState.Game);
                GameLoop.game.startLevel(Scoring.endlessLevel);
            }
            case "Levels" -> {
                selection = "null";
                Screens.setScreen(Screens.ScreenState.LevelSelect);
            }
            case "Options" -> {
                selection = "null";
                Screens.setScreen(Screens.ScreenState.Options);
            }
            case "Credits" -> {
                selection = "null";
                Screens.setScreen(Screens.ScreenState.Credits);
            }
            case "Quit" -> exitAction();
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

        g.setColor(Preferences.Frame);
        g.fillRect(0, 0, frameWidth, frameHeight);

        g.setColor(Preferences.Background);
        g.fillRect((int) (frameWidth / 2 - TOTALBLOCKWidth * 1.5f - BLOCKPadding), (int) (TOTALBLOCKHeight * 2.5f), TOTALBLOCKWidth * 3 - (BLOCKPadding * 2), BLOCKHeight);
        g.fillRect((int) (frameWidth / 2 + TOTALBLOCKWidth * 0.5f - BLOCKPadding), (int) ((TOTALBLOCKHeight * 2.5f) - BLOCKHeight + BLOCKPadding ), BLOCKWidth, BLOCKHeight + BLOCKPadding);

        g.setColor(Text.color);
        g.setFont(Text.textHeaderFont);
        g.drawString(Text.getStr("tetris"), frameWidth / 2 - Text.getHeadWidth("tetris") / 2, TOTALBLOCKHeight);

    }

}