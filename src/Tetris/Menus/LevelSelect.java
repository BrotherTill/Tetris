package Tetris.Menus;

import Tetris.Game.Board;
import Tetris.Rendering.Render;
import Tetris.Rendering.RenderUtil;

import java.awt.*;

public class LevelSelect extends Menu {

    @Override
    public void init() {
        int i = 0;
        for(int x=0; x <= RenderUtil.LevelSelectionX - 1; x++) {
            for(int y=0; y <= RenderUtil.LevelSelectionY - 1; y++) {
                i = x * (RenderUtil.LevelSelectionY) + y + 1;
                addBtn(new Button(String.valueOf(i), i, x+1, y+1, 10, 20, 10, 20));
            }
        }
        addBtn(new Button("Quit", i + 1, RenderUtil.LevelSelectionX, RenderUtil.LevelSelectionY + 1, 10, 20, 10 ,20));
    }

    @Override
    public void performAction() {
        if(selection != RenderUtil.LevelSelectionX * RenderUtil.LevelSelectionY + 1) {
            Render.Screen = RenderUtil.ScreenState.Game;
            Board.startGame(selection);
        } else {
            selection = 0;
            Render.Screen = RenderUtil.ScreenState.Menu;
        }
    }

    @Override
    public void paint(Graphics g) {
        int BLOCKWidth = blockWidth * 3;
        int TOTALBLOCKHeight = totalBlockHeight * 3;
        int TOTALBLOCKWidth = totalBlockWidth * 3;

        int MAXXSelection = RenderUtil.LevelSelectionX;
        int MAXYSelection = RenderUtil.LevelSelectionY;

        g.setColor(Primary);
        g.fillRect(0, 0, frameWidth, frameHeight);

        g.setColor(Background);

        g.setFont(textHeaderFont);
        g.drawString("Level Select", (frameWidth / 2) - (headerFontMetrics.stringWidth("Level Select") / 2), TOTALBLOCKHeight);

        g.setFont(textBigFont);

        for(int x=0; x <= MAXXSelection - 1; x++) {
            for(int y=0; y <= MAXYSelection - 1; y++) {
                g.setColor(Background);
                int i = x * MAXYSelection + y + 1;
                if(selection == i)
                    g.setColor(SELECTION);
                g.drawString(String.valueOf(i), (frameWidth - TOTALBLOCKWidth * 4) / (MAXXSelection - 1) * x + TOTALBLOCKWidth * 2 - bigFontMetrics.stringWidth(String.valueOf(i)), (frameHeight - TOTALBLOCKHeight * 3) / MAXYSelection * y + TOTALBLOCKHeight * 2);
            }
        }

        g.setFont(textFont);
        g.setColor(Color.BLACK);
        if(selection == MAXXSelection * MAXYSelection + 1)
            g.setColor(new Color(246, 46, 46, 255));
        g.drawString("Main Menu", frameWidth - BLOCKWidth - fontMetrics.stringWidth("Main Menu") / 2, TOTALBLOCKHeight * 7);
    }
}