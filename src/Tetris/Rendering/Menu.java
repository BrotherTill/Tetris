package Tetris.Rendering;

import Tetris.Logic.Board;

import java.awt.*;

public class Menu {

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
    private static final Color Primary = RenderUtil.Primary;

    //To modify these Variables goto Tetris.Logic.Board
    private static final int blockHeight = Board.getBlockHeight();
    private static final int blockWidth = Board.getBlockWidth();
    private static final int blockPadding = Board.getBlockPadding();
    private static final int totalBlockHeight = Board.getTotalBlockHeight();
    private static final int totalBlockWidth = Board.getTotalBlockWidth();
    private static final int frameHeight = RenderUtil.frameHeight;                  //Calculated from the Tetris.Pieces.Block height and padding
    private static final int frameWidth = RenderUtil.frameWidth;                    //Calculated from the Tetris.Pieces.Block width and padding

    public static void paintMenu(Graphics g) {
        g.setColor(Background);
        g.fillRect(0, 0, frameWidth, frameHeight);
        g.setColor(Primary);
        g.fillRect(0, 0, frameWidth, frameHeight);

        g.setColor(Background);

        g.setFont(textHeaderFont);
        g.drawString("Tetris", (frameWidth / 2) - (headerFontMetrics.stringWidth("Tetris") / 2), frameHeight / 9);
    }

    public static void paintTitleScreen(Graphics g) {

    }

    public static void paintTryAgain(Graphics g) {

    }

}