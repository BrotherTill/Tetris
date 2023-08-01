package Tetris.Menus;

import Tetris.Game.Board;
import Tetris.Rendering.Render;
import Tetris.Rendering.RenderUtil;

import java.awt.*;

import static Tetris.Rendering.RenderUtil.ScreenState.LevelSelect;

public class MainMenu extends Menu {

    @Override
    public void init() {
        addBtn(new Button("Game", 1, 1, 1, 10, 20 ,10, 20));
        addBtn(new Button("Levels", 2, 1, 2, 10, 20 ,10, 20));
        addBtn(new Button("Credits", 3, 1, 3, 10, 20 ,10, 20));
        addBtn(new Button("Quit", 4, 2, 3, 10, 20 ,10, 20));
    }

    @Override
    public void performAction() {
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
    public void paint(Graphics g) {
        int BLOCKHeight = blockHeight * 3;
        int BLOCKWidth = blockWidth * 3;
        int TOTALBLOCKHeight = totalBlockHeight * 3;
        int TOTALBLOCKWidth = totalBlockWidth * 3;
        int BLOCKPadding = blockPadding * 3;

        g.setColor(Primary);
        g.fillRect(0, 0, frameWidth, frameHeight);

        g.setColor(Background);

        g.setFont(textHeaderFont);
        g.drawString("Tetris", (frameWidth / 2) - (headerFontMetrics.stringWidth("Tetris") / 2), TOTALBLOCKHeight);

        g.fillRect((int) (frameWidth / 2 - TOTALBLOCKWidth * 1.5f - BLOCKPadding), (int) (TOTALBLOCKHeight * 2.5f), TOTALBLOCKWidth * 3 - (BLOCKPadding * 2), BLOCKHeight);
        g.fillRect((int) (frameWidth / 2 + TOTALBLOCKWidth * 0.5f - BLOCKPadding), (int) ((TOTALBLOCKHeight * 2.5f) - BLOCKHeight + BLOCKPadding ), BLOCKWidth, BLOCKHeight + BLOCKPadding);

        g.setFont(textBigFont);
        g.drawString("Start Game", frameWidth / 2 - bigFontMetrics.stringWidth("Start Game") / 2, TOTALBLOCKHeight * 5);
        g.drawString("Select Level", frameWidth / 2 - bigFontMetrics.stringWidth("Select Level") / 2, TOTALBLOCKHeight * 6);
        g.drawString("Credits", frameWidth / 2 - bigFontMetrics.stringWidth("Credits") / 2, TOTALBLOCKHeight * 7);
        g.setColor(Color.BLACK);
        g.setFont(textFont);
        g.drawString("Quit", frameWidth - BLOCKWidth - fontMetrics.stringWidth("Quit") / 2, TOTALBLOCKHeight * 7);
        g.setFont(textBigFont);

        g.setColor(SELECTION);
        switch (selection) {
            case 1 ->   g.drawString("Start Game", frameWidth / 2 - bigFontMetrics.stringWidth("Start Game") / 2, TOTALBLOCKHeight * 5);
            case 2 ->   g.drawString("Select Level", frameWidth / 2 - bigFontMetrics.stringWidth("Select Level") / 2, TOTALBLOCKHeight * 6);
            case 3 ->   g.drawString("Credits", frameWidth / 2 - bigFontMetrics.stringWidth("Credits") / 2, TOTALBLOCKHeight * 7);
            case 4 -> {
                g.setColor(new Color(246, 46, 46, 255));
                g.setFont(textFont);
                g.drawString("Quit", frameWidth - BLOCKWidth - fontMetrics.stringWidth("Quit") / 2, TOTALBLOCKHeight * 7);
            }
        }
    }

}