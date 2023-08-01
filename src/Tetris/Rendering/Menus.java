package Tetris.Rendering;

import Tetris.Game.Board;
import Tetris.Scoring;

import java.awt.*;

import static Tetris.Rendering.Render.Screen;
import static Tetris.Rendering.Render.deltaTime;
import static Tetris.Rendering.RenderUtil.ScreenState.LevelSelect;

public class Menus {

    private static final Font textFont = RenderUtil.textFont;                         // = new Font("MinecraftRegular", Font.BOLD, 30);
    private static final Font textBigFont = RenderUtil.textBigFont;                   // = new Font("MinecraftRegular", Font.BOLD, 45);
    private static final Font textHeaderFont = RenderUtil.textHeaderFont;                   // = new Font("MinecraftRegular", Font.BOLD, 70);
    private static final FontMetrics fontMetrics = RenderUtil.fontMetrics;            // = getFontMetrics(textFont);
    private static final FontMetrics bigFontMetrics = RenderUtil.bigFontMetrics;      // = getFontMetrics(textBigFont);
    private static final FontMetrics headerFontMetrics = RenderUtil.headerFontMetrics;      // = getFontMetrics(textBigFont);
    private static final int fontHeight = RenderUtil.bigFontHeight;
    private static final int bigFontHeight = RenderUtil.bigFontHeight;
    private static final int headerFontHeight = RenderUtil.headerFontHeight;

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
        switch (Render.Screen) {
            case TitleScreen:
                Render.Screen = RenderUtil.ScreenState.Menu;
                break;
            case Menu:
                switch (selection) {
                    case 1 -> {
                        Render.Screen = RenderUtil.ScreenState.Game;
                        Board.startGame(1);
                    }
                    case 2 -> {
                        switchScreen();
                        Render.Screen = LevelSelect;
                    }
                    case 3 -> {
                        switchScreen();
                        Render.Screen = RenderUtil.ScreenState.Credits;
                    }
                    case 4 -> System.exit(0);
                }
            break;
            case LevelSelect:
                if(selection != LevelSelect.getSelection()[0].length * LevelSelect.getSelection().length - 1) {
                    Render.Screen = RenderUtil.ScreenState.Game;
                    Board.startGame(selection);
                } else {
                    switchScreen();
                    Render.Screen = RenderUtil.ScreenState.Menu;
                }
                break;
            case Credits:
                switchScreen();
                Render.Screen = RenderUtil.ScreenState.Menu;
            break;
            case TryAgain:
                switch (selection) {
                    case 1 -> {
                        Render.Screen = RenderUtil.ScreenState.Game;
                        Board.startGame(1);
                    }
                    case 2 -> {
                        switchScreen();
                        Render.Screen = RenderUtil.ScreenState.Menu;
                    }
                }
        }
    }

    public static void paintMenu(Graphics g) {
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

    public static void paintLevelSelect(Graphics g) {
        int BLOCKWidth = blockWidth * 3;
        int TOTALBLOCKHeight = totalBlockHeight * 3;
        int TOTALBLOCKWidth = totalBlockWidth * 3;

        int MAXXSelection = LevelSelect.getSelection()[0].length;
        int MAXYSelection = LevelSelect.getSelection().length - 1;

        g.setColor(Primary);
        g.fillRect(0, 0, frameWidth, frameHeight);

        g.setColor(Background);

        g.setFont(textHeaderFont);
        g.drawString("Level Select", (frameWidth / 2) - (headerFontMetrics.stringWidth("Level Select") / 2), TOTALBLOCKHeight);

        g.setFont(textBigFont);

        for(int x=0; x <= MAXXSelection - 1; x++) {
            for(int y=0; y <= MAXYSelection - 1; y++) {
                g.setColor(Background);
                int i = x * MAXYSelection + y + 1;
                if(selection == i)
                    g.setColor(SELECTION);
                g.drawString(String.valueOf(i), (frameWidth - TOTALBLOCKWidth * 4) / (MAXXSelection - 1) * x + TOTALBLOCKWidth * 2 - bigFontMetrics.stringWidth(String.valueOf(i)), (frameHeight - TOTALBLOCKHeight * 3) / MAXYSelection * y + TOTALBLOCKHeight * 2);
            }
        }

        g.setFont(textFont);
        g.setColor(Color.BLACK);
        if(selection == MAXXSelection * MAXYSelection + 1)
            g.setColor(new Color(246, 46, 46, 255));
        g.drawString("Main Menu", frameWidth - BLOCKWidth - fontMetrics.stringWidth("Main Menu") / 2, TOTALBLOCKHeight * 7);
    }

    public static void paintTitleScreen(Graphics g) {
        int BLOCKHeight = blockHeight * 4;
        int BLOCKWidth = blockWidth * 4;
        int TOTALBLOCKHeight = totalBlockHeight * 4;
        int TOTALBLOCKWidth = totalBlockWidth * 4;
        int BLOCKPadding = blockPadding * 4;

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
        int BLOCKHeight = blockHeight * 4;
        int BLOCKWidth = blockWidth * 4;
        int TOTALBLOCKHeight = totalBlockHeight * 4;
        int TOTALBLOCKWidth = totalBlockWidth * 4;
        int BLOCKPadding = blockPadding * 4;

        g.setColor(Primary);
        g.fillRect(0, 0, frameWidth, frameHeight);

        g.setColor(Background);

        g.setFont(textHeaderFont);
        g.drawString("Tetris", (frameWidth / 2) - headerFontMetrics.stringWidth("Tetris") / 2, TOTALBLOCKHeight);
        g.setFont(textFont);
        g.drawString("by Till", (frameWidth / 2) - fontMetrics.stringWidth("by Till") / 2, TOTALBLOCKHeight * 5);
        g.drawString("made with Java", (frameWidth / 2) - fontMetrics.stringWidth("made with Java") / 2, TOTALBLOCKHeight * 5 + fontHeight - BLOCKPadding * 3);

        g.fillRect((int) (frameWidth / 2 - TOTALBLOCKWidth * 1.5f - BLOCKPadding), (int) (TOTALBLOCKHeight * 2.5f), TOTALBLOCKWidth * 3 - (BLOCKPadding * 2), BLOCKHeight);
        g.fillRect((int) (frameWidth / 2 + TOTALBLOCKWidth * 0.5f - BLOCKPadding), (int) ((TOTALBLOCKHeight * 2.5f) - BLOCKHeight + BLOCKPadding ), BLOCKWidth, BLOCKHeight + BLOCKPadding);
    }

    public static void paintTryAgain(Graphics g) {
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