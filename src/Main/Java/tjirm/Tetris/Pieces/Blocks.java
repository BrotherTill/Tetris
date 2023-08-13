package Main.Java.tjirm.Tetris.Pieces;

import java.awt.*;

public class Blocks {

    public static final Block filled = new Block(true);        //used to make writing of Tetris.Pieces.Tetris.Pieces easier
    public static final Block empty = new Block(false);

    public static Block[] fullLine;         //initialized in Tetris.Logic.Board

    public static final Block GBi = new Block(true, new Color(96, 96, 96), "GBi", "GBfi", false).setType("I");
    public static final Block GBo = new Block(true, new Color(96, 96, 96), "GBo", "GBo", false).setType("O");
    public static final Block GBl = new Block(true, new Color(96, 96, 96), "GBl", "GBl", false).setType("L");
    public static final Block GBj = new Block(true, new Color(96, 96, 96), "GBj", "GBj", false).setType("J");
    public static final Block GBs = new Block(true, new Color(96, 96, 96), "GBs", "GBs", false).setType("S");
    public static final Block GBz = new Block(true, new Color(96, 96, 96), "GBz", "GBz", false).setType("Z");
    public static final Block GBt = new Block(true, new Color(96, 96, 96), "GBt", "GBt", false).setType("T");

}
