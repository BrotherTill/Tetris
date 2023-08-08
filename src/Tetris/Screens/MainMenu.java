package Tetris.Screens;

import Tetris.Game.GameLoop;
import Tetris.Rendering.Render;
import Tetris.Rendering.RenderUtil;
import Tetris.Rendering.Text;

import java.awt.*;

import static Tetris.Rendering.RenderUtil.ScreenState.LevelSelect;

public class MainMenu extends Screen {

    @Override
    public void init() {
        int BLOCKWidth = blockWidth * 3;
        int TOTALBLOCKHeight = totalBlockHeight * 3;

        addBtn(new Button("Game", 1, 1, 1, frameWidth / 2 - Text.startWidth / 2 - Text.bigFontHeight / 3,
                (int) (TOTALBLOCKHeight * 4.5F) - Text.bigFontHeight,frameWidth / 2 + Text.startWidth / 2 + Text.bigFontHeight / 3,
                (int) (TOTALBLOCKHeight * 4.5F) + Text.bigFontHeight / 3));
        addBtn(new Button("Endless", 2, 1, 2, frameWidth / 2 - Text.endlessMdWidth / 2 - Text.bigFontHeight / 3,
                (int) (TOTALBLOCKHeight * (4.5F + 2.5F / 3F)) - Text.bigFontHeight,frameWidth / 2 + Text.endlessMdWidth / 2 + Text.bigFontHeight / 3,
                (int) (TOTALBLOCKHeight * (4.5F + 2.5F / 3F)) + Text.bigFontHeight / 3));
        addBtn(new Button("Levels", 3, 1, 3, frameWidth / 2 - Text.levelsWidth / 2 - Text.bigFontHeight / 3,
                        (int) (TOTALBLOCKHeight * (4.5F + (2.5F / 3F) * 2)) - Text.bigFontHeight,frameWidth / 2 + Text.levelsWidth / 2 + Text.bigFontHeight / 3,
                (int) (TOTALBLOCKHeight * (4.5F + (2.5F / 3F) * 2)) + Text.bigFontHeight / 3));
        addBtn(new Button("Credits", 4, 1, 4, frameWidth / 2 - Text.creditsWidth / 2 - Text.bigFontHeight / 3,
                TOTALBLOCKHeight * 7 - Text.bigFontHeight,frameWidth / 2 + Text.creditsWidth / 2 + Text.bigFontHeight / 3,
                TOTALBLOCKHeight * 7 + Text.bigFontHeight / 3));
        addBtn(new Button("Quit", 5, 2, 4, frameWidth - BLOCKWidth - Text.quitWidth / 2 - Text.fontHeight / 3,
                TOTALBLOCKHeight * 7 - Text.fontHeight,frameWidth - BLOCKWidth + Text.quitWidth / 2 + Text.fontHeight / 3,
                TOTALBLOCKHeight * 7 + Text.fontHeight / 3));
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
                GameLoop.game.startLevel(-1);
            }
            case 3 -> {
                selection = 0;
                Render.Screen = LevelSelect;
            }
            case 4 -> {
                selection = 0;
                Render.Screen = RenderUtil.ScreenState.Credits;
            }
            case 5 -> System.exit(69);
        }
    }

    @Override
    public void exitAction() {
        System.exit(69);
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

        g.setColor(Text.color);
        g.setFont(Text.textHeaderFont);
        g.drawString(Text.tetris, frameWidth / 2 - Text.tetrisWidth / 2, TOTALBLOCKHeight);

        g.fillRect((int) (frameWidth / 2 - TOTALBLOCKWidth * 1.5f - BLOCKPadding), (int) (TOTALBLOCKHeight * 2.5f), TOTALBLOCKWidth * 3 - (BLOCKPadding * 2), BLOCKHeight);
        g.fillRect((int) (frameWidth / 2 + TOTALBLOCKWidth * 0.5f - BLOCKPadding), (int) ((TOTALBLOCKHeight * 2.5f) - BLOCKHeight + BLOCKPadding ), BLOCKWidth, BLOCKHeight + BLOCKPadding);

        g.setFont(Text.textBigFont);
        g.drawString(Text.start, frameWidth / 2 - Text.startWidth / 2, (int) (TOTALBLOCKHeight * 4.5F));
        g.drawString(Text.endlessMd, frameWidth / 2 - Text.endlessMdWidth / 2, (int) (TOTALBLOCKHeight * (4.5F + 2.5F / 3F)));
        g.drawString(Text.levels, frameWidth / 2 - Text.levelsWidth / 2, (int) (TOTALBLOCKHeight * (4.5F + (2.5F / 3F) * 2)));
        g.drawString(Text.credits, frameWidth / 2 - Text.creditsWidth / 2, TOTALBLOCKHeight * 7);
        g.setColor(Text.exitColor);
        g.setFont(Text.textFont);
        g.drawString(Text.quit, frameWidth - BLOCKWidth - Text.quitWidth / 2, TOTALBLOCKHeight * 7);
        g.setFont(Text.textBigFont);

        g.setColor(Text.selectionColor);
        switch (selection) {
            case 1 ->   g.drawString(Text.start, frameWidth / 2 - Text.startWidth / 2, (int) (TOTALBLOCKHeight * 4.5F));
            case 2 ->   g.drawString(Text.endlessMd, frameWidth / 2 - Text.endlessMdWidth / 2, (int) (TOTALBLOCKHeight * (4.5F + 2.5F / 3F)));
            case 3 ->   g.drawString(Text.levels, frameWidth / 2 - Text.levelsWidth / 2, (int) (TOTALBLOCKHeight * (4.5F + (2.5F / 3F) * 2)));
            case 4 ->   g.drawString(Text.credits, frameWidth / 2 - Text.creditsWidth / 2, TOTALBLOCKHeight * 7);
            case 5 -> {
                g.setColor(Text.exitSelectionColor);
                g.setFont(Text.textFont);
                g.drawString(Text.quit, frameWidth - BLOCKWidth - Text.quitWidth / 2, TOTALBLOCKHeight * 7);
            }
        }
    }

}