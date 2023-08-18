package main.java.tjirm.Tetris;

import main.java.tjirm.Tetris.Rendering.RenderUtil;
import main.java.tjirm.Tetris.Rendering.Themes;

import java.awt.*;

public class Preferences {

    public static Themes.themes theme = Themes.themes.minimalistic;
    public static Color Frame = Color.decode("#5da9c6");
    public static Color Primary = Color.decode("#088080");
    public static Color Background = RenderUtil.Background;

    public static boolean showGhost = true;



}