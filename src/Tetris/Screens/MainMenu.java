package Tetris.Screens;

import Tetris.Game.Board;
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
                TOTALBLOCKHeight * 5 - Text.bigFontHeight,frameWidth / 2 + Text.startWidth / 2 + Text.bigFontHeight / 3,
                TOTALBLOCKHeight * 5 + Text.bigFontHeight / 3));
        addBtn(new Button("Levels", 2, 1, 2, frameWidth / 2 - Text.levelsWidth / 2 - Text.bigFontHeight / 3,
                TOTALBLOCKHeight * 6 - Text.bigFontHeight,frameWidth / 2 + Text.levelsWidth / 2 + Text.bigFontHeight / 3,
                TOTALBLOCKHeight * 6 + Text.bigFontHeight / 3));
        addBtn(new Button("Credits", 3, 1, 3, frameWidth / 2 - Text.creditsWidth / 2 - Text.bigFontHeight / 3,
                TOTALBLOCKHeight * 7 - Text.bigFontHeight,frameWidth / 2 + Text.creditsWidth / 2 + Text.bigFontHeight / 3,
                TOTALBLOCKHeight * 7 + Text.bigFontHeight / 3));
        addBtn(new Button("Quit", 4, 2, 3, frameWidth - BLOCKWidth - Text.quitWidth / 2 - Text.fontHeight / 3,
                TOTALBLOCKHeight * 7 - Text.fontHeight,frameWidth - BLOCKWidth + Text.quitWidth / 2 + Text.fontHeight / 3,
                TOTALBLOCKHeight * 7 + Text.fontHeight / 3));
    }

    @Override
    public void selectionAction() {
        switch (selection) {
            case 1 -> {
                Render.Screen = RenderUtil.ScreenState.Game;
                Board.startGame(1);
            }
            case 2 -> {
                selection = 0;
                Render.Screen = LevelSelect;
            }
            case 3 -> {
                selection = 0;
                Render.Screen = RenderUtil.ScreenState.Credits;
            }
            case 4 -> System.exit(0);
        }
    }

    @Override
    public void exitAction() {
        System.exit(0);
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
        g.drawString(Text.start, frameWidth / 2 - Text.startWidth / 2, TOTALBLOCKHeight * 5);
        g.drawString(Text.levels, frameWidth / 2 - Text.levelsWidth / 2, TOTALBLOCKHeight * 6);
        g.drawString(Text.credits, frameWidth / 2 - Text.creditsWidth / 2, TOTALBLOCKHeight * 7);
        g.setColor(Text.exitColor);
        g.setFont(Text.textFont);
        g.drawString(Text.quit, frameWidth - BLOCKWidth - Text.quitWidth / 2, TOTALBLOCKHeight * 7);
        g.setFont(Text.textBigFont);

        g.setColor(Text.selectionColor);
        switch (selection) {
            case 1 ->   g.drawString(Text.start, frameWidth / 2 - Text.startWidth / 2, TOTALBLOCKHeight * 5);
            case 2 ->   g.drawString(Text.levels, frameWidth / 2 - Text.levelsWidth / 2, TOTALBLOCKHeight * 6);
            case 3 ->   g.drawString(Text.credits, frameWidth / 2 - Text.creditsWidth / 2, TOTALBLOCKHeight * 7);
            case 4 -> {
                g.setColor(Text.exitSelectionColor);
                g.setFont(Text.textFont);
                g.drawString(Text.quit, frameWidth - BLOCKWidth - Text.quitWidth / 2, TOTALBLOCKHeight * 7);
            }
        }
    }

}