public class Scoring {

    private static int score = 69;
    private static int level = 1;
    private static int lines = 0;

    public static void sendLines(int lines) {
        Scoring.lines += lines;
        switch (lines) {
            case 1: score += 100 * level;    break;
            case 2: score += 300 * level;    break;
            case 3: score += 500 * level;    break;
            case 4: score += 800 * level;    break;
        }
        if(Scoring.lines >= level * 5) {
            nextLevel();
            Board.resetField();
            if(level == 16) {
                Board.GameWin();
            }
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
    }
}
