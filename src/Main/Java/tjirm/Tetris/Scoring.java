package Main.Java.tjirm.Tetris;

import Main.Java.tjirm.Tetris.Game.Board;
import Main.Java.tjirm.Tetris.Game.GameLoop;

public class Scoring {

    private static int score = 69;
    private static int level = 1;
    private static int lines = 0;

    public static void sendLines(int lines) {
        if(level == -1)
            sendEndLines(lines);
        else
            sendLevLines(lines);
    }

    private static void sendLevLines(int lines) {
        Scoring.lines += lines;
        switch (lines) {
            case 1 -> score += 100 * level;
            case 2 -> score += 300 * level;
            case 3 -> score += 500 * level;
            case 4 -> score += 800 * level;
        }
        if(Scoring.lines >= level * 5) {
            if(level == 15) {
                GameLoop.game.GameWin();
            }
            nextLevel();
            Board.resetField();
        }
    }

    private static void sendEndLines(int lines) {
        Scoring.lines += lines;
        switch (lines) {
            case 1 -> score += 100;
            case 2 -> score += 300;
            case 3 -> score += 500;
            case 4 -> score += 800;
        }
    }

    public static void sendSoftDrop(int lines) {
        score += lines;
    }
    public static void sendHardDrop(int lines) {
        score += lines * 2;
    }

    public static void nextLevel() {
        level++;
        lines = 0;
        Board.setFallRate((int) Math.round(1000 * (Math.pow(0.8d - ((level - 1d) * 0.007d), (level - 1d)))));
    }

    public static int getScore() {
        return score;
    }

    public static int getLevel() {
        return level;
    }

    public static int getLines() {
        return lines;
    }

    public static void resetScore() {
        score = 0;
        lines = 0;
    }

    public static void setLevel(int level) {
        Scoring.level = level;
    }
}