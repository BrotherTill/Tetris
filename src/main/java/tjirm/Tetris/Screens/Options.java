package main.java.tjirm.Tetris.Screens;

import main.java.tjirm.Tetris.Preferences;
import main.java.tjirm.Tetris.Rendering.Text;

import java.awt.*;

public class Options extends Screen {

    @Override
    public void init() {
        int BLOCKWidth = blockWidth * 3;
        int TOTALBLOCKHeight = totalBlockHeight * 3;

        addBtn(new Button("minimalistic", 1, 1, 1, 0,0,0,0));
    }

    @Override
    public void paint(Graphics g) {
        int BLOCKHeight = blockHeight * 3;
        int BLOCKWidth = blockWidth * 3;
        int TOTALBLOCKHeight = totalBlockHeight * 3;
        int TOTALBLOCKWidth = totalBlockWidth * 3;
        int BLOCKPadding = blockPadding * 3;

        g.setColor(Preferences.Primary);
        g.fillRect(0, 0, frameWidth, frameHeight);

        g.setColor(Text.color);
        g.setFont(Text.textHeaderFont);
        g.drawString(Text.options, frameWidth / 2 - Text.headOptionsWidth / 2, TOTALBLOCKHeight);

        g.setFont(Text.textBigFont);
        g.drawString(Text.start, frameWidth / 2 - Text.startWidth / 2, (int) (TOTALBLOCKHeight * 4.5F));
        g.drawString(Text.endlessMd, frameWidth / 2 - Text.endlessMdWidth / 2, (int) (TOTALBLOCKHeight * (4.5F + 2.5F / 3F)));
        g.drawString(Text.levels, frameWidth / 2 - Text.levelsWidth / 2, (int) (TOTALBLOCKHeight * (4.5F + (2.5F / 3F) * 2)));
        g.drawString(Text.options, frameWidth / 2 - Text.optionsWidth / 2, TOTALBLOCKHeight * 7);
        g.setFont(Text.textFont);
        g.drawString(Text.credits, BLOCKWidth - Text.creditsWidth / 3, TOTALBLOCKHeight * 7);
        g.setColor(Text.exitColor);
        g.drawString(Text.quit, frameWidth - BLOCKWidth - Text.quitWidth / 2, TOTALBLOCKHeight * 7);
        g.setFont(Text.textBigFont);

        g.setColor(Text.selectionColor);
        switch (selection) {
            case 1 ->   g.drawString(Text.start, frameWidth / 2 - Text.startWidth / 2, (int) (TOTALBLOCKHeight * 4.5F));
            case 2 ->   g.drawString(Text.endlessMd, frameWidth / 2 - Text.endlessMdWidth / 2, (int) (TOTALBLOCKHeight * (4.5F + 2.5F / 3F)));
            case 3 ->   g.drawString(Text.levels, frameWidth / 2 - Text.levelsWidth / 2, (int) (TOTALBLOCKHeight * (4.5F + (2.5F / 3F) * 2)));
            case 4 ->   g.drawString(Text.options, frameWidth / 2 - Text.optionsWidth / 2, TOTALBLOCKHeight * 7);
            case 5 -> {
                g.setFont(Text.textFont);
                g.drawString(Text.credits, BLOCKWidth - Text.creditsWidth / 3, TOTALBLOCKHeight * 7);
            }
            case 6 -> {
                g.setColor(Text.exitSelectionColor);
                g.setFont(Text.textFont);
                g.drawString(Text.quit, frameWidth - BLOCKWidth - Text.quitWidth / 2, TOTALBLOCKHeight * 7);
            }
        }
    }

}
