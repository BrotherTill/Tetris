package main.java.tjirm.Tetris.Rendering;

import main.java.tjirm.Tetris.Input.Menu;
import main.java.tjirm.Tetris.Input.Mouse;
import main.java.tjirm.Tetris.Input.MouseHover;
import main.java.tjirm.Tetris.Screens.Screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

public class Render extends JPanel implements ActionListener{

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////************  All the Variables above the Code are modifiable to whatever you desire  *****************/////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static final int frameRate = 165;
    Timer renderCaller;

    public static Duration deltaTime = Duration.ZERO;      //Just used to initialize
    public static Instant beginTime = Instant.now();       //Just used to initialize

    public static long GameOverOpacity = 0;                //Used as a Counter to gradually fade ou the Tetris.Logic.Board when the game ends(you can modify it if you want to change the start Opacity)
    public static final float GameOverAlpha = 218;         //The final Value of the Opacity when the game ends in Alpha
    public static final float fadeDuration = 1000L;        //The time to fade out the Tetris.Logic.Board in Milliseconds

    public static KeyListener currentListener = new Menu();


    public Render() throws IOException {           //initialize the Renderer
        RenderUtil.init();

        addKeyListener(currentListener);
        addMouseMotionListener(new MouseHover());
        addMouseListener(new Mouse());

        setFocusable(true);
        setPreferredSize(new Dimension(RenderUtil.frameWidth, RenderUtil.frameHeight));

        renderCaller = new Timer(1000 / frameRate, this);
        renderCaller.start();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        paintImmediately(0, 0, RenderUtil.frameWidth, RenderUtil.frameHeight);
        deltaTime = Duration.between(beginTime, Instant.now());         //deltaTime used so the Tetris.Logic.Board fades out in the correct time(not used for FrameRate)
        beginTime = Instant.now();
    }

    @Override
    public void paintComponent(Graphics g) {
        Screens.getCurrent().paint(g);
    }

    public void setCurrentListener(KeyListener newListener) {
        removeKeyListener(currentListener);
        currentListener = newListener;
        addKeyListener(currentListener);
    }

}