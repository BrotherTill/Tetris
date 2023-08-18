package main.java.tjirm.Tetris.Pieces;

import main.java.tjirm.Tetris.Game.Board;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Blocks {

    public static Block[] fullLine;         //initialized in Tetris.Logic.Board

    private static final Map<String, Block> blocks = new HashMap<>();

    public static void init() {
        Blocks.fullLine = new Block[Board.getFieldWidth()];
        for(int i = 0; i < Board.getFieldWidth() ; i++)
            Blocks.fullLine[i] = new Block(true);
        blocks.put("empty", new Block(false));

        blocks.put("I1", new Block(true, new Color(96, 96, 96), "ErikI1", "GBi", true).doRotate());
        blocks.put("I2", new Block(true, new Color(96, 96, 96), "ErikI2", "GBi", true).doRotate());
        blocks.put("I3", new Block(true, new Color(96, 96, 96), "ErikI3", "GBi", true).doRotate());
        blocks.put("I4", new Block(true, new Color(96, 96, 96), "ErikI4", "GBi", true).doRotate());
        blocks.put("O1", new Block(true, new Color(96, 96, 96), "ErikO1", "GBo", true).doRotate());
        blocks.put("O2", new Block(true, new Color(96, 96, 96), "ErikO2", "GBo", true).doRotate());
        blocks.put("O3", new Block(true, new Color(96, 96, 96), "ErikO3", "GBo", true).doRotate());
        blocks.put("O4", new Block(true, new Color(96, 96, 96), "ErikO4", "GBo", true).doRotate());
        blocks.put("L1", new Block(true, new Color(96, 96, 96), "ErikL1", "GBl", true).doRotate());
        blocks.put("L2", new Block(true, new Color(96, 96, 96), "ErikL2", "GBl", true).doRotate());
        blocks.put("L3", new Block(true, new Color(96, 96, 96), "ErikL3", "GBl", true).doRotate());
        blocks.put("L4", new Block(true, new Color(96, 96, 96), "ErikL4", "GBl", true).doRotate());
        blocks.put("J1", new Block(true, new Color(96, 96, 96), "ErikJ1", "GBj", true).doRotate());
        blocks.put("J2", new Block(true, new Color(96, 96, 96), "ErikJ2", "GBj", true).doRotate());
        blocks.put("J3", new Block(true, new Color(96, 96, 96), "ErikJ3", "GBj", true).doRotate());
        blocks.put("J4", new Block(true, new Color(96, 96, 96), "ErikJ4", "GBj", true).doRotate());
        blocks.put("S1", new Block(true, new Color(96, 96, 96), "ErikS1", "GBs", true).doRotate());
        blocks.put("S2", new Block(true, new Color(96, 96, 96), "ErikS2", "GBs", true).doRotate());
        blocks.put("S3", new Block(true, new Color(96, 96, 96), "ErikS3", "GBs", true).doRotate());
        blocks.put("S4", new Block(true, new Color(96, 96, 96), "ErikS4", "GBs", true).doRotate());
        blocks.put("Z1", new Block(true, new Color(96, 96, 96), "ErikZ1", "GBz", true).doRotate());
        blocks.put("Z2", new Block(true, new Color(96, 96, 96), "ErikZ2", "GBz", true).doRotate());
        blocks.put("Z3", new Block(true, new Color(96, 96, 96), "ErikZ3", "GBz", true).doRotate());
        blocks.put("Z4", new Block(true, new Color(96, 96, 96), "ErikZ4", "GBz", true).doRotate());
        blocks.put("T1", new Block(true, new Color(96, 96, 96), "ErikT1", "GBt", true).doRotate());
        blocks.put("T2", new Block(true, new Color(96, 96, 96), "ErikT2", "GBt", true).doRotate());
        blocks.put("T3", new Block(true, new Color(96, 96, 96), "ErikT3", "GBt", true).doRotate());
        blocks.put("T4", new Block(true, new Color(96, 96, 96), "ErikT4", "GBt", true).doRotate());
    }

    public static Block get(String name) {
        return blocks.get(name).clone();
    }

}
