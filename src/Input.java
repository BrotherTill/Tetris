import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Input extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_ESCAPE:
            case KeyEvent.VK_F1:
                Board.Pause();
                break;
            case KeyEvent.VK_F2:
                Board.GameWin();
                break;
            case KeyEvent.VK_SHIFT:
            case KeyEvent.VK_LESS:
            case KeyEvent.VK_NUMPAD0:
                Board.Hold1();
                break;
            case KeyEvent.VK_C:
            case KeyEvent.VK_DECIMAL:
                Board.Hold2();
                break;
            case KeyEvent.VK_Z:
            case KeyEvent.VK_Y:
            case KeyEvent.CTRL_DOWN_MASK:
            case KeyEvent.VK_NUMPAD3:
            case KeyEvent.VK_NUMPAD7:
                Board.rotateCCW();
                break;
            case KeyEvent.VK_X:
            case KeyEvent.VK_UP:
            case KeyEvent.VK_NUMPAD1:
            case KeyEvent.VK_NUMPAD5:
            case KeyEvent.VK_NUMPAD9:
                Board.rotateCW();
                break;
            case KeyEvent.VK_SPACE:
            case KeyEvent.VK_NUMPAD8:
                Board.HardDrop();
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_NUMPAD2:
                Board.startSoftDrop();
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_NUMPAD4:
                Board.MoveLeft();
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_NUMPAD6:
                Board.MoveRight();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_NUMPAD2:
            Board.stopSoftDrop();
        }
    }

}
