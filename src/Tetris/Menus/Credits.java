package Tetris.Menus;

import Tetris.Rendering.Render;
import Tetris.Rendering.RenderUtil;

import java.awt.*;

public class Credits extends Menu {

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

        g.setColor(Primary);
        g.fillRect(0, 0, frameWidth, frameHeight);

        g.setColor(Background);

        g.setFont(textHeaderFont);
        g.drawString("Tetris", (frameWidth / 2) - headerFontMetrics.stringWidth("Tetris") / 2, TOTALBLOCKHeight);
        g.setFont(textFont);
        g.drawString("by Till", (frameWidth / 2) - fontMetrics.stringWidth("by Till") / 2, TOTALBLOCKHeight * 5);
        g.drawString("made with Java", (frameWidth / 2) - fontMetrics.stringWidth("made with Java") / 2, TOTALBLOCKHeight * 5 + fontHeight - BLOCKPadding * 3);

        g.fillRect((int) (frameWidth / 2 - TOTALBLOCKWidth * 1.5f - BLOCKPadding), (int) (TOTALBLOCKHeight * 2.5f), TOTALBLOCKWidth * 3 - (BLOCKPadding * 2), BLOCKHeight);
        g.fillRect((int) (frameWidth / 2 + TOTALBLOCKWidth * 0.5f - BLOCKPadding), (int) ((TOTALBLOCKHeight * 2.5f) - BLOCKHeight + BLOCKPadding ), BLOCKWidth, BLOCKHeight + BLOCKPadding);
    }
}