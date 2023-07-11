import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Input extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_ESCAPE, KeyEvent.VK_F1
                    -> Board.Pause();
            case KeyEvent.VK_F2
                    -> Board.GameWin();
            case KeyEvent.VK_SHIFT, KeyEvent.VK_LESS, KeyEvent.VK_NUMPAD0
                    -> Board.Hold1();
            case KeyEvent.VK_C, KeyEvent.VK_DECIMAL -> Board.Hold2();
            case KeyEvent.VK_Z, KeyEvent.VK_Y, KeyEvent.CTRL_DOWN_MASK, KeyEvent.VK_NUMPAD3, KeyEvent.VK_NUMPAD7
                    -> Board.rotateCCW();
            case KeyEvent.VK_X, KeyEvent.VK_UP, KeyEvent.VK_NUMPAD1, KeyEvent.VK_NUMPAD5, KeyEvent.VK_NUMPAD9
                    -> Board.rotateCW();
            case KeyEvent.VK_SPACE, KeyEvent.VK_NUMPAD8
                    -> Board.HardDrop();
            case KeyEvent.VK_DOWN, KeyEvent.VK_NUMPAD2
                    -> Board.startSoftDrop();
            case KeyEvent.VK_LEFT, KeyEvent.VK_NUMPAD4
                    -> Board.MoveLeft();
            case KeyEvent.VK_RIGHT, KeyEvent.VK_NUMPAD6
                    -> Board.MoveRight();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_DOWN, KeyEvent.VK_NUMPAD2
                    -> Board.stopSoftDrop();
        }
    }

}
