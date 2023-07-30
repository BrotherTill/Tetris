package Tetris;

import Tetris.Game.Board;
import Tetris.Rendering.Render;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    public static Render render = new Render();

    public Main() {
        initUI();
    }

    private void initUI() {

        add(render);              //add the Renderer to Frame

        setResizable(false);
        pack();

        setTitle("Tetris");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        new Board(7);       //initialize The Game Tetris.Logic.Board with a next queue Generation Length of 7(cannot be 0)

        EventQueue.invokeLater(() -> {
            JFrame ex = new Main();
            ex.setVisible(true);
        });

    }

}