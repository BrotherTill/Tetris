package main.java.tjirm.Tetris.Rendering;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class Text {

    //Font Variables cant be modified here, modify them in initFont()
    public static Font textFont;                        // = new Font("MinecraftRegular", Font.BOLD, 30);
    public static Font textBigFont;                     // = new Font("MinecraftRegular", Font.BOLD, 45);
    public static Font textHeaderFont;                 // = new Font("MinecraftRegular", Font.BOLD, 70);
    public static FontMetrics fontMetrics;              // = getFontMetrics(textFont);
    public static FontMetrics bigFontMetrics;           // = getFontMetrics(textBigFont);
    public static FontMetrics headerFontMetrics;       // = getFontMetrics(textBigFont);
    public static int fontHeight;
    public static int bigFontHeight;
    public static int headerFontHeight;

    public static Color color = Color.DARK_GRAY;
    public static Color selectionColor = Color.GRAY;
    public static Color exitColor = Color.BLACK;
    public static Color exitSelectionColor = new Color(246, 46, 46, 255);

    public static int holdWidth;
    public static int nextWidth;
    public static int overWidth;
    public static int winWidth;
    public static int levelWidth;
    public static int overLevelWidth;
    public static int scoreWidth;
    public static int overScoreWidth;
    public static int bigOverScoreWidth;
    public static int linesWidth;
    public static int overLinesWidth;
    public static int exitWidth;
    public static int tetrisWidth;
    public static int startWidth;
    public static int restartWidth;
    public static int levelsWidth;
    public static int creditsWidth;
    public static int quitWidth;
    public static int menuWidth;
    public static int exitMenuWidth;
    public static int endlessMdWidth;
    public static int endlessWidth;
    public static int optionsWidth;
    public static int headOptionsWidth;

    public static String hold = "HOLD";
    public static String next = "NEXT";
    public static String over = "Game Over!";
    public static String win = "You Win";
    public static String level = "Level ";
    public static String overLevel = "Level: ";
    public static String score = "Score";
    public static String overScore = "Score: ";
    public static String bigOverScore = "Score: ";
    public static String lines = "lines";
    public static String overLines = "lines: ";
    public static String tetris = "Tetris";
    public static String exit = "Exit(Esc)";
    public static String start = "Start Game";
    public static String restart = "Restart Game";
    public static String levels = "Level Select";
    public static String credits = "Credits";
    public static String quit = "Quit";
    public static String menu = "Main Menu";
    public static String exitMenu = "Main Menu";
    public static String endlessMd = "Endless Mode";
    public static String endless = "Endless";
    public static String options = "Options";

    public static void init() {
        try {
            InputStream fontFile = Render.class.getResourceAsStream("/main/resources/Fonts/Minecraft.otf");     //Paths are painful

            Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            textFont = font.deriveFont(Font.BOLD, RenderUtil.blockHeight);                  //DON'T TOUCH THIS IS VERY FRAGILE CODE
            textBigFont = font.deriveFont(Font.BOLD, RenderUtil.blockHeight + 15f);
            textHeaderFont = font.deriveFont(Font.BOLD, RenderUtil.blockHeight + 40f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(textFont);                          //I think it adds it to a list of Tetris.Rendering.Fonts somewhere(from StackOverflow)
            ge.registerFont(textBigFont);
            ge.registerFont(textHeaderFont);
        } catch (IOException | FontFormatException e) {
            JOptionPane.showMessageDialog(null,
            "The Font File is missing",
                    "Critical Error",
                    JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);                      //catch exception if the File is missing
        }                                                       //initialize other Font Variables(you can modify them if you want)

        fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(textFont);
        bigFontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(textBigFont);
        headerFontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(textHeaderFont);
        fontHeight = fontMetrics.getHeight();
        bigFontHeight = bigFontMetrics.getHeight();
        headerFontHeight = headerFontMetrics.getHeight();
        holdWidth = fontMetrics.stringWidth(hold);
        nextWidth = fontMetrics.stringWidth(next);
        overWidth = headerFontMetrics.stringWidth(over);
        winWidth = headerFontMetrics.stringWidth(win);
        levelWidth = bigFontMetrics.stringWidth(level);
        overLevelWidth = fontMetrics.stringWidth(overLevel);
        scoreWidth = bigFontMetrics.stringWidth(score);
        overScoreWidth = fontMetrics.stringWidth(overScore);
        bigOverScoreWidth = bigFontMetrics.stringWidth(bigOverScore);
        linesWidth = fontMetrics.stringWidth(lines);
        overLinesWidth = fontMetrics.stringWidth(overLines);
        exitWidth = fontMetrics.stringWidth(exit);
        tetrisWidth = headerFontMetrics.stringWidth(tetris);
        startWidth = bigFontMetrics.stringWidth(start);
        restartWidth = bigFontMetrics.stringWidth(restart);
        levelsWidth = bigFontMetrics.stringWidth(levels);
        creditsWidth = fontMetrics.stringWidth(credits);
        menuWidth = bigFontMetrics.stringWidth(menu);
        quitWidth = fontMetrics.stringWidth(quit);
        exitMenuWidth = fontMetrics.stringWidth(exitMenu);
        endlessMdWidth = bigFontMetrics.stringWidth(endlessMd);
        endlessWidth = bigFontMetrics.stringWidth(endless);
        optionsWidth = bigFontMetrics.stringWidth(options);
        headOptionsWidth = headerFontMetrics.stringWidth(options);
    }

}
