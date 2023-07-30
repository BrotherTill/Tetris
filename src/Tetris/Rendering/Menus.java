package Tetris.Rendering;

import Tetris.Game.Board;
import Tetris.Scoring;

import java.awt.*;

import static Tetris.Rendering.Render.deltaTime;

public class Menus {

    private static Font textFont = RenderUtil.textFont;                         // = new Font("MinecraftRegular", Font.BOLD, 30);
    private static Font textBigFont = RenderUtil.textBigFont;                   // = new Font("MinecraftRegular", Font.BOLD, 45);
    private static Font textHeaderFont = RenderUtil.textHeaderFont;                   // = new Font("MinecraftRegular", Font.BOLD, 70);
    private static FontMetrics fontMetrics = RenderUtil.fontMetrics;            // = getFontMetrics(textFont);
    private static FontMetrics bigFontMetrics = RenderUtil.bigFontMetrics;      // = getFontMetrics(textBigFont);
    private static FontMetrics headerFontMetrics = RenderUtil.headerFontMetrics;      // = getFontMetrics(textBigFont);
    private static int fontHeight = RenderUtil.bigFontHeight;
    private static int bigFontHeight = RenderUtil.bigFontHeight;
    private static int headerFontHeight = RenderUtil.headerFontHeight;

    private static final Color Background = RenderUtil.Background;
    private static final Color SELECTION = RenderUtil.SELECTION;
    private static final Color Primary = RenderUtil.Primary;

    //To modify these Variables goto Tetris.Logic.Board
    private static final int blockHeight = RenderUtil.blockHeight;
    private static final int blockWidth = RenderUtil.blockWidth;
    private static final int blockPadding = RenderUtil.blockPadding;
    private static final int totalBlockHeight = RenderUtil.totalBlockHeight;
    private static final int totalBlockWidth = RenderUtil.totalBlockWidth;
    private static final int frameHeight = RenderUtil.frameHeight;                  //Calculated from the Tetris.Pieces.Block height and padding
    private static final int frameWidth = RenderUtil.frameWidth;                    //Calculated from the Tetris.Pieces.Block width and padding

    public static int selection = 0;
    public static long elapsedTime = 0;

    public static void switchScreen() {
        selection = 0;
    }

    public static void performSelection() {
        switch(Render.Screen) {
            case TitleScreen:

            case Menu:
                switch(selection) {
                    case 1 -> {
                        Render.Screen = RenderUtil.ScreenState.Game;
                        Board.startGame();
                    }
                    case 2 ->   System.out.println("Placeholder Level Select");
                    case 3 ->   {
                        switchScreen();
                        Render.Screen = RenderUtil.ScreenState.Credits;
                    }
                    case 4 ->   System.exit(0);
                }
                break;
            case Credits:
                switchScreen();
                Render.Screen = RenderUtil.ScreenState.Menu;
                break;
            case TryAgain:
                switch(selection) {
                    case 1 ->   {
                        Render.Screen = RenderUtil.ScreenState.Game;
                        Board.startGame();
                    }
                    case 2 ->   {
                        switchScreen();
                        Render.Screen = RenderUtil.ScreenState.Menu;
                    }
                }
        }
    }

    public static void paintMenu(Graphics g) {
        int BLOCKHeight = RenderUtil.blockHeight * 3;
        int BLOCKWidth = RenderUtil.blockWidth * 3;
        int TOTALBLOCKHeight = RenderUtil.totalBlockHeight * 3;
        int TOTALBLOCKWidth = RenderUtil.totalBlockWidth * 3;
        int BLOCKPadding = RenderUtil.blockPadding * 3;

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

    public static void paintTitleScreen(Graphics g) {
        int BLOCKHeight = RenderUtil.blockHeight * 4;
        int BLOCKWidth = RenderUtil.blockWidth * 4;
        int TOTALBLOCKHeight = RenderUtil.totalBlockHeight * 4;
        int TOTALBLOCKWidth = RenderUtil.totalBlockWidth * 4;
        int BLOCKPadding = RenderUtil.blockPadding * 4;

        g.setColor(Primary);
        g.fillRect(0, 0, frameWidth, frameHeight);

        g.setColor(Background);

        g.setFont(textHeaderFont);
        g.drawString("Tetris", (frameWidth / 2) - headerFontMetrics.stringWidth("Tetris") / 2, TOTALBLOCKHeight);
        g.setFont(textFont);
        g.drawString("by Till", (frameWidth / 2) - fontMetrics.stringWidth("by Till") / 2, TOTALBLOCKHeight * 5);


        g.fillRect((int) (frameWidth / 2 - TOTALBLOCKWidth * 1.5f - BLOCKPadding), (int) (TOTALBLOCKHeight * 2.5f), TOTALBLOCKWidth * 3 - (BLOCKPadding * 2), BLOCKHeight);
        g.fillRect((int) (frameWidth / 2 + TOTALBLOCKWidth * 0.5f - BLOCKPadding), (int) ((TOTALBLOCKHeight * 2.5f) - BLOCKHeight + BLOCKPadding ), BLOCKWidth, BLOCKHeight + BLOCKPadding);

        elapsedTime += deltaTime.toMillis();
        if(elapsedTime <= 3000)
            return;
        Render.Screen = RenderUtil.ScreenState.Menu;
    }

    public static void paintCredits(Graphics g) {
        int BLOCKHeight = RenderUtil.blockHeight * 4;
        int BLOCKWidth = RenderUtil.blockWidth * 4;
        int TOTALBLOCKHeight = RenderUtil.totalBlockHeight * 4;
        int TOTALBLOCKWidth = RenderUtil.totalBlockWidth * 4;
        int BLOCKPadding = RenderUtil.blockPadding * 4;

        g.setColor(Primary);
        g.fillRect(0, 0, frameWidth, frameHeight);

        g.setColor(Background);

        g.setFont(textHeaderFont);
        g.drawString("Tetris", (frameWidth / 2) - headerFontMetrics.stringWidth("Tetris") / 2, TOTALBLOCKHeight);
        g.setFont(textFont);
        g.drawString("by Till", (frameWidth / 2) - fontMetrics.stringWidth("by Till") / 2, TOTALBLOCKHeight * 5);

        g.fillRect((int) (frameWidth / 2 - TOTALBLOCKWidth * 1.5f - BLOCKPadding), (int) (TOTALBLOCKHeight * 2.5f), TOTALBLOCKWidth * 3 - (BLOCKPadding * 2), BLOCKHeight);
        g.fillRect((int) (frameWidth / 2 + TOTALBLOCKWidth * 0.5f - BLOCKPadding), (int) ((TOTALBLOCKHeight * 2.5f) - BLOCKHeight + BLOCKPadding ), BLOCKWidth, BLOCKHeight + BLOCKPadding);
    }

    public static void paintTryAgain(Graphics g) {
        int TOTALBLOCKHeight = RenderUtil.totalBlockHeight * 3;
        int TOTALBLOCKWidth = RenderUtil.totalBlockWidth * 3;

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