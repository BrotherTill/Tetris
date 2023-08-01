package Tetris.Menus;

import Tetris.Game.Board;
import Tetris.Rendering.Render;
import Tetris.Rendering.RenderUtil;
import Tetris.Scoring;

import java.awt.*;

public class TryAgain extends Menu {

    @Override
    public void init() {
        addBtn(new Button("Game", 1, 1, 1, 10, 20 ,10, 20));
        addBtn(new Button("Menu", 2, 1, 2, 10, 20 ,10, 20));
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
                Render.Screen = RenderUtil.ScreenState.Menu;
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        int TOTALBLOCKHeight = totalBlockHeight * 3;
        int TOTALBLOCKWidth = totalBlockWidth * 3;

        g.setColor(Primary);
        g.fillRect(0, 0, frameWidth, frameHeight);

        g.setColor(Background);

        g.setFont(textHeaderFont);
        g.drawString("Tetris", frameWidth / 2 - headerFontMetrics.stringWidth("Tetris") / 2, frameHeight / 9);

        if(Board.GameOver) {
            g.drawString("Game Over", frameWidth / 2 - headerFontMetrics.stringWidth("Game Over") / 2, TOTALBLOCKHeight * 3);
        }
        if(Board.GameWon) {
            g.drawString("You Won!", frameWidth / 2 - headerFontMetrics.stringWidth("You Won!") / 2, TOTALBLOCKHeight * 3);
        }
        g.setFont(textFont);
        g.drawString("Level: " + Scoring.getLevel(), frameWidth / 2 - TOTALBLOCKWidth - fontMetrics.stringWidth("Level: " + Scoring.getLevel()) / 2, TOTALBLOCKHeight * 4);
        g.drawString("Score: " + Scoring.getScore(), frameWidth / 2 + TOTALBLOCKWidth - fontMetrics.stringWidth("Score: " + Scoring.getLevel()) / 2, TOTALBLOCKHeight * 4);

        g.setFont(textBigFont);
        g.drawString("Restart Game", (frameWidth / 2) - (bigFontMetrics.stringWidth("Restart Game") / 2), TOTALBLOCKHeight * 6);
        g.drawString("Main Menu", (frameWidth / 2) - (bigFontMetrics.stringWidth("Main Menu") / 2), TOTALBLOCKHeight * 7);

        g.setColor(SELECTION);
        switch (selection) {
            case 1 ->   g.drawString("Restart Game", (frameWidth / 2) - (bigFontMetrics.stringWidth("Restart Game") / 2), TOTALBLOCKHeight * 6);
            case 2 ->   g.drawString("Main Menu", (frameWidth / 2) - (bigFontMetrics.stringWidth("Main Menu") / 2), TOTALBLOCKHeight * 7);
        }
    }
}