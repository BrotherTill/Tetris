package main.java.tjirm.Tetris.Rendering;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Text {

    //Font Variables cant be modified here, modify them in initFont()
    public static Font textFont;                        // = new Font("MinecraftRegular", Font.BOLD, 30);
    public static Font textBigFont;                     // = new Font("MinecraftRegular", Font.BOLD, 45);
    public static Font textHeaderFont;                 // = new Font("MinecraftRegular", Font.BOLD, 70);
    private static FontMetrics fontMetrics;              // = getFontMetrics(textFont);
    private static FontMetrics bigFontMetrics;           // = getFontMetrics(textBigFont);
    private static FontMetrics headerFontMetrics;       // = getFontMetrics(textBigFont);
    public static int fontHeight;
    public static int bigFontHeight;
    public static int headerFontHeight;

    public static Color color = Color.DARK_GRAY;
    public static Color selectionColor = Color.GRAY;
    public static Color activeColor = new Color(12, 159, 38);
    public static Color activeSelectionColor = new Color(22, 206, 43);
    public static Color exitColor = Color.BLACK;
    public static Color exitSelectionColor = new Color(246, 46, 46, 255);

    private static final HashMap<String, String> strings = new HashMap<>();
    private static final HashMap<String, Integer> width = new HashMap<>();
    private static final HashMap<String, Integer> bigWidth = new HashMap<>();
    private static final HashMap<String, Integer> headWidth = new HashMap<>();

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

        strings.put("default", "MISSING");

        strings.put("hold", "HOLD");
        strings.put("next", "NEXT");
        strings.put("over", "Game Over!");
        strings.put("win", "You Win");
        strings.put("level", "Level ");
        strings.put("overLevel", "Level: ");
        strings.put("score", "Score");
        strings.put("overScore", "Score: ");
        strings.put("lines", "lines");
        strings.put("overLines", "lines: ");
        strings.put("tetris", "Tetris");
        strings.put("exit", "Exit(Esc)");
        strings.put("start", "Start game");
        strings.put("restart", "Restart Game");
        strings.put("levels", "Level Select");
        strings.put("credits", "Credits");
        strings.put("quit", "Quit");
        strings.put("menu", "Main Menu");
        strings.put("endlessMd", "Endless Mode");
        strings.put("endless", "Endless");
        strings.put("options", "Options");
        strings.put("mini", "Minimalistic");
        strings.put("color", "Color");
        strings.put("pattern", "Pattern");
        strings.put("retro", "Retro");
        strings.put("ghost", "Ghost Piece");

        fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(textFont);
        bigFontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(textBigFont);
        headerFontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(textHeaderFont);
        fontHeight = fontMetrics.getHeight();
        bigFontHeight = bigFontMetrics.getHeight();
        headerFontHeight = headerFontMetrics.getHeight();

        for(Map.Entry<String, String> entry : strings.entrySet()) {
            String text = entry.getValue();
            width.put(entry.getKey(), fontMetrics.stringWidth(text));
            bigWidth.put(entry.getKey(), bigFontMetrics.stringWidth(text));
            headWidth.put(entry.getKey(), headerFontMetrics.stringWidth(text));
        }
    }

    public static String getStr(String name) {
        if(!strings.containsKey(name))
            return strings.get("default");
        return strings.get(name);
    }
    public static int getWidth(String name) {
        if(!width.containsKey(name))
            return width.get("default");
        return width.get(name);
    }
    public static int getBigWidth(String name) {
        if(!bigWidth.containsKey(name))
            return bigWidth.get("default");
        return bigWidth.get(name);
    }
    public static int getHeadWidth(String name) {
        if(!headWidth.containsKey(name))
            return headWidth.get("default");
        return headWidth.get(name);
    }
    public static int getTextWidth(String name) {
        return fontMetrics.stringWidth(name);
    }
    public static int getBigTextWidth(String name) {
        return bigFontMetrics.stringWidth(name);
    }
    public static int getHeadTextWidth(String name) {
        return headerFontMetrics.stringWidth(name);
    }
    public static int getTextWidth(int name) {
        return fontMetrics.stringWidth(String.valueOf(name));
    }
    public static int getBigTextWidth(int name) {
        return bigFontMetrics.stringWidth(String.valueOf(name));
    }
    public static int getHeadTextWidth(int name) {
        return headerFontMetrics.stringWidth(String.valueOf(name));
    }

}
