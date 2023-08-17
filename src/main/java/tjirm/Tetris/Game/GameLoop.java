package main.java.tjirm.Tetris.Game;

import main.java.tjirm.Tetris.Input.Menu;
import main.java.tjirm.Tetris.Main;
import main.java.tjirm.Tetris.Rendering.Render;
import main.java.tjirm.Tetris.Screens.Screens;

public class GameLoop {

    private static final int fallCallDivider = 20;

    // if the game is running, default = true
    // if the game is paused, default = false
    private boolean isRunning;
    private volatile boolean isPaused;

    // execution time for the last cycle of game loop
    private long lastLoopTime;
    private long currentLoopTime;
    private long deltaLoopTime;
    private double deltaTime;

    // nanoseconds spent per frame
    private long OPTIMAL_TIME = 1000_000000 / fallCallDivider;

    /**
     * Main Function, instantiating Game
     */
    public static GameLoop game = new GameLoop();

    Runnable LoopRunnable = () -> {
        try {
            RunLoop();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    };
    private class GameRunnable implements Runnable {
        private long deltaTime;
        public void setDeltaTime(long deltaTime) {
            this.deltaTime = deltaTime;
        }
        @Override
        public void run() {
            Board.updateGame(deltaTime);
        }
    }
    GameRunnable gameRunnable = new GameRunnable() {
    };
    Thread LoopThread;
    Thread GameThread = new Thread();

    /**
     * Game Loop
     */
    public void RunLoop() throws InterruptedException {
        System.out.println("start");
        while (isRunning) {

            // if the game is paused
            if (isPaused) {
                // wait for user to unpause
                while (isPaused) {
                    Thread.onSpinWait();
                }
                System.out.println("continue");

                // reset time syncing
                lastLoopTime = System.nanoTime();
                continue;
            }
            if(Board.GameOver | Board.GameWon)
                endGame();

            // get delta time
            currentLoopTime = System.nanoTime();
            deltaLoopTime = currentLoopTime - lastLoopTime;
            deltaTime = deltaLoopTime / (double)OPTIMAL_TIME;
            // set last to current
            lastLoopTime = currentLoopTime;

            Board.updateGame(deltaLoopTime);

            // wait for game to catch up (if loop execution was too fast)
            if(System.nanoTime() - lastLoopTime < OPTIMAL_TIME)
                Thread.sleep( (lastLoopTime - System.nanoTime() + OPTIMAL_TIME)/1000_000 );
            else
                System.out.println("Dropping Frames");
        }
    }

    public void endGame() {
        stop();
        try {
            Thread.sleep((long) (Render.fadeDuration + 2000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Main.render.setCurrentListener(new Menu());
        Screens.mainMenu.selection = "null";
        Screens.setScreen(Screens.ScreenState.TryAgain);
    }

    public void init(int level) {
        isRunning = true;
        isPaused = false;
        Board.initStart(level);
    }

    public void start() {
        assert !isRunning : "Tried to start Game while Game was already Started";
        init(1);
        LoopThread = new Thread(LoopRunnable);
        LoopThread.start();
    }

    public void startLevel(int level) {
        assert !isRunning : "Tried to start Game while Game was already Started";
        init(level);
        LoopThread = new Thread(LoopRunnable);
        LoopThread.start();
    }

    public void stop() {
        assert isRunning : "Tried to stop Game while Game was already Stopped";
        isRunning = false;
    }

    public void pause() {
        assert isRunning : "Tried to pause Game while Game wasn't Running";
        assert !isPaused : "Tried to pause Game while Game was already Paused";
        isPaused = true;
    }

    public void resume() {
        assert isRunning : "Tried to resume Game while Game wasn't Running";
        assert isPaused : "Tried to resume Game while Game was already Resumed";
        isPaused = false;
    }

    public void GameOver() {
        Board.GameOver = true;
        Board.blockInput = true;
    }

    public void GameWin() {
        Board.GameWon = true;
        Board.blockInput = true;
    }

    public void togglePause() {
        if(!isPaused) {
            pause();
            Board.blockInput = true;
        } else {
            resume();
            Board.blockInput = false;
        }
    }

    public void setFrameTime(long millis) {
        OPTIMAL_TIME = millis;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public boolean isPaused() {
        return isPaused;
    }

}
