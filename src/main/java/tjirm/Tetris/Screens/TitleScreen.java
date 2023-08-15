package main.java.tjirm.Tetris.Screens;

import main.java.tjirm.Tetris.Preferences;
import main.java.tjirm.Tetris.Rendering.Render;
import main.java.tjirm.Tetris.Rendering.RenderUtil;
import main.java.tjirm.Tetris.Rendering.Text;

import java.awt.*;

import static main.java.tjirm.Tetris.Rendering.Render.deltaTime;

public class TitleScreen extends Screen {

    public static long elapsedTime = 0;

    @Override
    public void selectionAction() {
        Render.Screen = RenderUtil.ScreenState.Menu;
    }

    @Override
    public void exitAction() {
        Render.Screen = RenderUtil.ScreenState.Menu;
    }

    @Override
    public void paint(Graphics g) {
        int BLOCKHeight = blockHeight * 4;
        int BLOCKWidth = blockWidth * 4;
        int TOTALBLOCKHeight = totalBlockHeight * 4;
        int TOTALBLOCKWidth = totalBlockWidth * 4;
        int BLOCKPadding = blockPadding * 4;

        g.setColor(Preferences.Primary);
        g.fillRect(0, 0, frameWidth, frameHeight);

        g.setColor(Preferences.Background);
        g.fillRect((int) (frameWidth / 2 - TOTALBLOCKWidth * 1.5f - BLOCKPadding), (int) (TOTALBLOCKHeight * 2.5f), TOTALBLOCKWidth * 3 - (BLOCKPadding * 2), BLOCKHeight);
        g.fillRect((int) (frameWidth / 2 + TOTALBLOCKWidth * 0.5f - BLOCKPadding), (int) ((TOTALBLOCKHeight * 2.5f) - BLOCKHeight + BLOCKPadding ), BLOCKWidth, BLOCKHeight + BLOCKPadding);

        g.setColor(Text.color);
        g.setFont(Text.textHeaderFont);
        g.drawString(Text.tetris, (frameWidth / 2) - Text.tetrisWidth / 2, TOTALBLOCKHeight);
        g.setFont(Text.textFont);
        g.drawString("by Till", (frameWidth / 2) - Text.fontMetrics.stringWidth("by Till") / 2, TOTALBLOCKHeight * 5);


        elapsedTime += deltaTime.toMillis();
        if(elapsedTime <= 3000)
            return;
        Render.Screen = RenderUtil.ScreenState.Menu;
    }

    @Override
    public void init() {    }

}