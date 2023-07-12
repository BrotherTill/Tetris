import javax.swing.JFrame;
import java.awt.*;

public class Main extends JFrame {

    public Main() {
        initUI();
    }

    private void initUI() {

        add(new Render());              //add the Renderer to Frame

        setResizable(false);
        pack();

        setTitle("Tetris");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        new Board(0);       //initialize The Game Board with a next queue Generation Length of 7(cannot be 0)

        EventQueue.invokeLater(() -> {
            JFrame ex = new Main();
            ex.setVisible(true);
        });

    }

}