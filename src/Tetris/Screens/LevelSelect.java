package Tetris.Screens;

import Tetris.Game.GameLoop;
import Tetris.Rendering.Render;
import Tetris.Rendering.RenderUtil;
import Tetris.Rendering.Text;

import java.awt.*;

public class LevelSelect extends Screen {

    @Override
    public void init() {
        int BLOCKWidth = blockWidth * 3;
        int TOTALBLOCKHeight = totalBlockHeight * 3;
        int TOTALBLOCKWidth = totalBlockWidth * 3;
        int MAXXSelection = RenderUtil.LevelSelectionX;
        int MAXYSelection = RenderUtil.LevelSelectionY;

        int i = 0;
        for(int x=0; x <= MAXXSelection - 1; x++) {
            for(int y=0; y <= MAXYSelection - 1; y++) {
                i = x * MAXYSelection + y + 1;
                int xOrigin = (frameWidth - TOTALBLOCKWidth * 4) / (MAXXSelection - 1) * x + TOTALBLOCKWidth * 2;
                int yOrigin = (frameHeight - TOTALBLOCKHeight * 3) / MAXYSelection * y + TOTALBLOCKHeight * 2;
                addBtn(new Button(String.valueOf(i), i, x+1, y+1,
                        xOrigin - Text.bigFontMetrics.stringWidth(String.valueOf(i)) / 2 - Text.bigFontHeight / 3,
                        yOrigin - Text.bigFontHeight,
                        xOrigin + Text.bigFontMetrics.stringWidth(String.valueOf(i)) / 2 + Text.bigFontHeight / 3,
                        yOrigin + Text.bigFontHeight / 3));
            }
        }
        addBtn(new Button("Quit", i + 1, RenderUtil.LevelSelectionX, RenderUtil.LevelSelectionY + 1,
                frameWidth - BLOCKWidth - Text.exitMenuWidth / 2 - Text.fontHeight / 3,
                TOTALBLOCKHeight * 7 - Text.fontHeight, frameWidth - BLOCKWidth + Text.exitMenuWidth / 2 + Text.fontHeight / 3,
                TOTALBLOCKHeight * 7 + Text.fontHeight / 3));
        addBtn(new Button("Endless", -1, RenderUtil.LevelSelectionX / 2, RenderUtil.LevelSelectionY + 1,
                frameWidth / 2 - Text.endlessMdWidth / 2 - Text.fontHeight / 3,
                (int) (TOTALBLOCKHeight * 6.5F) - Text.bigFontHeight, frameWidth / 2 + Text.endlessMdWidth / 2 + Text.fontHeight / 3,
                (int) (TOTALBLOCKHeight * 6.5F) + Text.bigFontHeight / 3));
    }

    @Override
    public void selectionAction() {
        if(selection >= 1 && selection <= RenderUtil.LevelSelectionX * RenderUtil.LevelSelectionY) {
            Render.Screen = RenderUtil.ScreenState.Game;
            GameLoop.game.startLevel(selection);
            selection = 0;
        } else
        if(selection == RenderUtil.LevelSelectionX * RenderUtil.LevelSelectionY + 1){
            selection = 0;
            Render.Screen = RenderUtil.ScreenState.Menu;
        }
        if(selection == -1) {
            selection = 0;
            Render.Screen = RenderUtil.ScreenState.Game;
            GameLoop.game.startLevel(-1);
        }
    }

    @Override
    public void exitAction() {
        Render.Screen = RenderUtil.ScreenState.Menu;
    }

    @Override
    public void paint(Graphics g) {
        int BLOCKWidth = blockWidth * 3;
        int TOTALBLOCKHeight = totalBlockHeight * 3;
        int TOTALBLOCKWidth = totalBlockWidth * 3;

        int MAXXSelection = RenderUtil.LevelSelectionX;
        int MAXYSelection = RenderUtil.LevelSelectionY;

        g.setColor(RenderUtil.Primary);
        g.fillRect(0, 0, frameWidth, frameHeight);

        g.setColor(Text.color);

        g.setFont(Text.textHeaderFont);
        g.drawString("Level Select", (frameWidth / 2) - (Text.headerFontMetrics.stringWidth("Level Select") / 2), TOTALBLOCKHeight);

        g.setFont(Text.textBigFont);

        for(int x=0; x <= MAXXSelection - 1; x++) {
            for(int y=0; y <= MAXYSelection - 1; y++) {
                g.setColor(RenderUtil.Background);
                int i = x * MAXYSelection + y + 1;
                if(selection == i)
                    g.setColor(RenderUtil.SELECTION);
                g.drawString(String.valueOf(i), (frameWidth - TOTALBLOCKWidth * 4) / (MAXXSelection - 1) * x + TOTALBLOCKWidth * 2 - Text.bigFontMetrics.stringWidth(String.valueOf(i)) / 2,
                        (frameHeight - TOTALBLOCKHeight * 3) / MAXYSelection * y + TOTALBLOCKHeight * 2);
            }
        }

        g.setColor(Text.color);
        if(selection == -1)
            g.setColor(Text.selectionColor);
        g.drawString(Text.endlessMd, frameWidth / 2 - Text.endlessMdWidth / 2, (int) (TOTALBLOCKHeight * 6.5F));

        g.setColor(Text.exitColor);
        g.setFont(Text.textFont);
        if(selection == MAXXSelection * MAXYSelection + 1)
            g.setColor(Text.exitSelectionColor);
        g.drawString(Text.exitMenu, frameWidth - BLOCKWidth - Text.exitMenuWidth / 2, TOTALBLOCKHeight * 7);
    }
}