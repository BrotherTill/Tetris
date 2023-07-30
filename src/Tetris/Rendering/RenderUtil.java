package Tetris.Rendering;

import Tetris.Logic.Board;

import java.awt.*;
import java.io.IOException;

public class RenderUtil {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////************  All the Variables above the Code are modifiable to whatever you desire  *****************/////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //Font Variables cant be modified here, modify them in initFont()
    public static Font textFont;                        // = new Font("MinecraftRegular", Font.BOLD, 30);
    public static Font textBigFont;                     // = new Font("MinecraftRegular", Font.BOLD, 45);
    public static Font textHeaderFont;                 // = new Font("MinecraftRegular", Font.BOLD, 70);
    public static FontMetrics fontMetrics;              // = getFontMetrics(textFont);
    public static FontMetrics bigFontMetrics;           // = getFontMetrics(textBigFont);
    public static FontMetrics headerFontMetrics;       // = getFontMetrics(textBigFont);
    public static int holdWidth;                        // = fontMetrics.stringWidth("HOLD");
    public static int nextWidth;                        // = fontMetrics.stringWidth("NEXT");
    public static int overWidth;                        // = bigFontMetrics.stringWidth("Game Over!");
    public static int winWidth;                         // = bigFontMetrics.stringWidth("You Win!");
    public static int scoreWidth;                       // = bigFontMetrics.stringWidth("SCORE:");
    public static int linesWidth;                       // = FontMetrics.stringWidth("lines:");
    public static int fontHeight;
    public static int bigFontHeight;
    public static int headerFontHeight;

    public static final Color Background = Color.DARK_GRAY;
    public static final Color Primary = Color.WHITE;

    //To modify these Variables goto Tetris.Logic.Board
    public static final int blockHeight = Board.getBlockHeight();
    public static final int totalBlockHeight = Board.getTotalBlockHeight();
    public static final int totalBlockWidth = Board.getTotalBlockWidth();
    private static final int fieldHeight = Board.getFieldHeight();
    private static final int fieldWidth = Board.getFieldWidth();
    public static int frameHeight;                  //Calculated from the Tetris.Pieces.Block height and padding
    public static int frameWidth;                   //Calculated from the Tetris.Pieces.Block width and padding


    public static void init() {           //initialize Font Variables (in own method because I read form a File)
        frameWidth = totalBlockWidth * fieldWidth + totalBlockWidth * 14;
        frameHeight = totalBlockHeight * fieldHeight + totalBlockHeight * 2;
        try {
            textFont = Font.createFont(Font.TRUETYPE_FONT, Render.class.getResourceAsStream("Fonts/Minecraft.otf")).deriveFont(Font.BOLD, blockHeight);                  //DON'T TOUCH THIS IS VERY FRAGILE CODE
            textBigFont = Font.createFont(Font.TRUETYPE_FONT, Render.class.getResourceAsStream("Fonts/Minecraft.otf")).deriveFont(Font.BOLD, blockHeight + 15f);    //I don't know why it's fragile
            textHeaderFont = Font.createFont(Font.TRUETYPE_FONT, Render.class.getResourceAsStream("Fonts/Minecraft.otf")).deriveFont(Font.BOLD, blockHeight + 40f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(textFont);                          //I think it adds it to a list of Tetris.Rendering.Fonts somewhere(from StackOverflow)
            ge.registerFont(textBigFont);
            ge.registerFont(textHeaderFont);
        } catch (IOException | FontFormatException e) {
            throw new RuntimeException(e);                      //catch exception if the File is missing
        }                                                       //initialize other Font Variables(you can modify them if you want)
        fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(textFont);
        bigFontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(textBigFont);
        headerFontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(textHeaderFont);
        fontHeight = fontMetrics.getHeight();
        bigFontHeight = bigFontMetrics.getHeight();
        headerFontHeight = headerFontMetrics.getHeight();
        holdWidth = fontMetrics.stringWidth("HOLD");            //you need to change these text width Variables if you change the Text displayed
        nextWidth = fontMetrics.stringWidth("NEXT");
        overWidth = bigFontMetrics.stringWidth("Game Over");
        winWidth = bigFontMetrics.stringWidth("You Win!");
        scoreWidth = bigFontMetrics.stringWidth("SCORE:");
        linesWidth = fontMetrics.stringWidth("lines:");
    }

    public enum ScreenState {
        TitleScreen,
        Menu,
        Game,
        TryAgain;
    }
}