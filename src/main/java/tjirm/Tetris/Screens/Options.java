package main.java.tjirm.Tetris.Screens;

import main.java.tjirm.Tetris.Preferences;
import main.java.tjirm.Tetris.Rendering.Text;
import main.java.tjirm.Tetris.Screens.Elements.Button;

import java.awt.*;

public class Options extends Screen {

    @Override
    public void init() {
        int BLOCKWidth = blockWidth * 3;
        int TOTALBLOCKHeight = totalBlockHeight * 3;

        addBtn(new main.java.tjirm.Tetris.Screens.Elements.Button("mini", Button.bigFontSize, 1, 0, 0,true,false));
        addBtn(new main.java.tjirm.Tetris.Screens.Elements.Button("color", Button.bigFontSize, 2, 0, 0,true,false));
        addBtn(new main.java.tjirm.Tetris.Screens.Elements.Button("pattern", Button.bigFontSize, 3, 0, 0,true,false));
        addBtn(new main.java.tjirm.Tetris.Screens.Elements.Button("retro", Button.bigFontSize, 4, 0, 0,true,false));
        addBtn(new main.java.tjirm.Tetris.Screens.Elements.Button("Ghost", Button.bigFontSize, 5, 0, 0,true,false));
        addBtn(new Button("menu", Button.normalFontSize, 6, 0, 0,true,true));
    }

    @Override
    public void draw(Graphics g) {
        int BLOCKHeight = blockHeight * 3;
        int BLOCKWidth = blockWidth * 3;
        int TOTALBLOCKHeight = totalBlockHeight * 3;
        int TOTALBLOCKWidth = totalBlockWidth * 3;
        int BLOCKPadding = blockPadding * 3;

        g.setColor(Preferences.Primary);
        g.fillRect(0, 0, frameWidth, frameHeight);

        g.setColor(Text.color);
        g.setFont(Text.textHeaderFont);
        g.drawString(Text.getStr("options"), frameWidth / 2 - Text.getHeadWidth("options") / 2, TOTALBLOCKHeight);

    }

}
