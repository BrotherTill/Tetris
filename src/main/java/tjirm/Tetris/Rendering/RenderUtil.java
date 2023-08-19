package main.java.tjirm.Tetris.Rendering;

import main.java.tjirm.Tetris.Game.Board;
import main.java.tjirm.Tetris.Pieces.Blocks;
import main.java.tjirm.Tetris.Pieces.Pieces;
import main.java.tjirm.Tetris.Screens.Screens;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class RenderUtil {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////************  All the Variables above the Code are modifiable to whatever you desire  *****************/////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static final Color Background = Color.DARK_GRAY;
    public static final Color SELECTION = Color.LIGHT_GRAY;
    public static final Color Primary = Color.WHITE;

    //To modify these Variables goto Tetris.Logic.Board
    public static final int fieldHeight = Board.getFieldHeight();
    public static final int fieldWidth = Board.getFieldWidth();
    public static final int blockHeight = 30;
    public static final int blockWidth = 30;
    public static final int blockPadding = 1;
    public static final int totalBlockHeight = blockHeight + blockPadding*2;
    public static final int totalBlockWidth = blockWidth + blockPadding*2;
    public static int frameHeight;                  //Calculated from the Tetris.Pieces.Block height and padding
    public static int frameWidth;                   //Calculated from the Tetris.Pieces.Block width and padding

    public static final int LevelSelectionX = 3;
    public static final int LevelSelectionY = 5;
    private static final int[][] LevelSelectionPage = new int[LevelSelectionY + 1][LevelSelectionX];

    ////////////////////////////////////////////VERY IMPORTANT//////////////////////////////////////////////////////
    public static Path recourcesPath;
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public static void init() throws IOException {           //initialize Font Variables (in own method because I read form a File)
        for(int x=0; x<LevelSelectionPage[0].length; x++){
            for (int y=0; y<LevelSelectionPage.length; y++){
                LevelSelectionPage[y][x] = -1;
                if(x < LevelSelectionX && y < LevelSelectionY)
                    LevelSelectionPage[y][x] = x * LevelSelectionY + y + 1;
            }
        }
        LevelSelectionPage[LevelSelectionY][LevelSelectionX - 1] = LevelSelectionX * LevelSelectionY + 1;
        frameWidth = totalBlockWidth * fieldWidth + totalBlockWidth * 14;
        frameHeight = totalBlockHeight * fieldHeight + totalBlockHeight * 2;

        initPath();

        Themes.init();
        Blocks.init();
        Pieces.init();
        Text.init();
        Screens.init();
        System.out.println("finished");
    }

    private static void initPath() throws IOException {
        try {
            File IDEPath = new File("src/main/resources");
            if(IDEPath.exists() || IDEPath.isDirectory()) {
                System.out.println("Running in IDE");
                recourcesPath = IDEPath.toPath();
                return;
            } else {
                System.out.println("Didn't find files in IDE Path (src/main/resources)");
            }
        } catch (NullPointerException e) {
            System.out.println("Didn't find files in IDE Path (src/main/resources)");
        }
        try {
            File JARPath = new File("resources");
            if(JARPath.exists() || JARPath.isDirectory()) {
                System.out.println("Running in JAR");
                recourcesPath = JARPath.toPath();
                return;
            } else {
                System.out.println("Didn't find files in JAR Path (resources)");
            }
        } catch (NullPointerException e) {
            System.out.println("Didn't find files in JAR Path (resources)");
        }
        System.out.println("didn't find resources");
        throw new IOException("resources not Found");
    }

}