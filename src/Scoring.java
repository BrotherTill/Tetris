public class Scoring {

    private static int score = 69;
    private static int level = 1;
    private static int lines = 0;

    public static void sendLine() {
        lines++;
        if(lines >= level * 5) {
            Board.resetField();
            nextLevel();
            if(level == 16) {
                Board.GameWin();
            }
        }
    }

    public static void nextLevel() {
        level++;
        Board.setFallRate((int) Math.round(1000 * (Math.pow(0.8d - ((level - 1d) * 0.007d), (level - 1d)))));
    }

    public static int getScore() {
        return score;
    }


    public static int getLevel() {
        return level;
    }

    public static void resetScore() {
        score = 0;
    }
}
