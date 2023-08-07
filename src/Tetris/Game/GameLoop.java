package Tetris.Game;

import Tetris.Input.Menu;
import Tetris.Main;
import Tetris.Rendering.Render;
import Tetris.Rendering.RenderUtil;
import Tetris.Screens.Screens;

public class GameLoop {

    private static final int fallCallDivider = 20;
    private static int fallRate = 1000;
    private static boolean softFall = false;
    private static final int softFallRate = 50;
    private static int softFallCounter = 0;

    // if the game is running, default = true
    // if the game is paused, default = false
    private boolean isRunning;
    private boolean isPaused;

    // execution time for the last cycle of game loop
    private long lastLoopTime;
    private long currentLoopTime;
    private long deltaLoopTime;
    private double deltaTime;

    // if a second has passed since last execution
    private boolean secondPassed;
    private long secondTimer;

    // nanoseconds spent per frame
    private final int OPTIMAL_FPS = 20;
    private final int OPTIMAL_TIME = 1000000000 / OPTIMAL_FPS;

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
    Runnable GameRunnable = () -> {
        Board.updateGame(deltaLoopTime);
    };
    Thread LoopThread;
    Thread GameThread = new Thread();;

    /**
     *
     */
    public GameLoop () {
        // the game is on and not paused
        isRunning = false;
        isPaused = false;

        // a second has not yet passed
        secondPassed = false;
        secondTimer = System.currentTimeMillis();

        // begin with current time
        lastLoopTime = System.nanoTime();
    }

    /**
     * Game Loop
     */
    public void RunLoop() throws InterruptedException {
        while (isRunning == true) {

            // if the game is paused
            if (isPaused == true) {
                // wait for user to unpause
                while (isPaused == true) {
                }

                // reset time synching
                lastLoopTime = System.nanoTime();
            }

            // get delta time
            currentLoopTime = System.nanoTime();
            deltaLoopTime = currentLoopTime - lastLoopTime;
            deltaTime = deltaLoopTime / (double)OPTIMAL_TIME;
            // set last to current
            lastLoopTime = currentLoopTime;

            if(GameThread.isAlive())
                GameThread.join();
            GameThread = new Thread(GameRunnable);
            // update game
            GameThread.start();

            // wait for game to catch up (if loop execution was too fast)
            Thread.sleep( (lastLoopTime - System.nanoTime() + OPTIMAL_TIME)/1000_000 );
        }
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
        endGame();
    }

    public void GameWin() {
        Board.GameWon = true;
        Board.blockInput = true;
        endGame();
    }

    public void endGame() {
        stop();
        try {
            Thread.sleep((long) (Render.fadeDuration + 2000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Main.render.setCurrentListener(new Menu());
        Screens.mainMenu.selection = 0;
        Render.Screen = RenderUtil.ScreenState.TryAgain;
    }

    public void togglePause() {
        if(isRunning) {
            pause();
            Board.blockInput = true;
        } else {
            resume();
            Board.blockInput = false;
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    public boolean isPaused() {
        return isPaused;
    }

}
