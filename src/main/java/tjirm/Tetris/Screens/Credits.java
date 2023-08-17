package main.java.tjirm.Tetris.Screens;

import main.java.tjirm.Tetris.Preferences;
import main.java.tjirm.Tetris.Rendering.Text;

import java.awt.*;

public class Credits extends Screen {

    @Override
    public void clickAction() {
        exitAction();
    }
    @Override
    public void pressAction() {
        exitAction();
    }

    @Override
    public void exitAction() {
        Screens.setScreen(Screens.ScreenState.Menu);
    }

    @Override
    public void draw(Graphics g) {
        int BLOCKHeight = blockHeight * 4;
        int BLOCKWidth = blockWidth * 4;
        int TOTALBLOCKHeight = totalBlockHeight * 4;
        int TOTALBLOCKWidth = totalBlockWidth * 4;
        int BLOCKPadding = blockPadding * 4;

        g.setColor(Preferences.Frame);
        g.fillRect(0, 0, frameWidth, frameHeight);

        g.setColor(Preferences.Background);

        g.setColor(Text.color);
        g.setFont(Text.textHeaderFont);
        g.drawString(Text.getStr("tetris"), frameWidth / 2 - Text.getHeadWidth("tetris") / 2, TOTALBLOCKHeight);
        g.setFont(Text.textFont);
        g.drawString("by Till", frameWidth / 2 - Text.getTextWidth("by Till") / 2, TOTALBLOCKHeight * 5);
        g.drawString("made with Java", frameWidth / 2 - Text.getTextWidth("made with Java") / 2, TOTALBLOCKHeight * 5 + Text.fontHeight - BLOCKPadding * 3);

        g.fillRect((int) (frameWidth / 2 - TOTALBLOCKWidth * 1.5f - BLOCKPadding), (int) (TOTALBLOCKHeight * 2.5f), TOTALBLOCKWidth * 3 - (BLOCKPadding * 2), BLOCKHeight);
        g.fillRect((int) (frameWidth / 2 + TOTALBLOCKWidth * 0.5f - BLOCKPadding), (int) ((TOTALBLOCKHeight * 2.5f) - BLOCKHeight + BLOCKPadding ), BLOCKWidth, BLOCKHeight + BLOCKPadding);
    }

    @Override
    public void init() {    }

}