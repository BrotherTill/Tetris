package main.java.tjirm.Tetris.Screens;

import main.java.tjirm.Tetris.Preferences;
import main.java.tjirm.Tetris.Rendering.Render;
import main.java.tjirm.Tetris.Rendering.RenderUtil;
import main.java.tjirm.Tetris.Rendering.Text;
import main.java.tjirm.Tetris.Rendering.Themes.themes;
import main.java.tjirm.Tetris.Screens.Elements.Button;
import main.java.tjirm.Tetris.Screens.Elements.Toggle;

import java.awt.*;

public class Options extends Screen {

    private Toggle mini;
    private Toggle color;
    private Toggle pattern;
    private Toggle retro;
    private Toggle rainbow;
    private Toggle ghost;

    @Override
    public void init() {
        int BLOCKWidth = blockWidth * 3;
        int TOTALBLOCKHeight = totalBlockHeight * 3;

        mini = new Toggle("mini", Button.bigFontSize, 1, frameWidth / 2 - BLOCKWidth * 2, TOTALBLOCKHeight * 2,true,false);
        color = new Toggle("color", Button.bigFontSize, 2, frameWidth / 2 + BLOCKWidth * 2, TOTALBLOCKHeight * 2,true,false);
        pattern = new Toggle("pattern", Button.bigFontSize, 3, frameWidth / 2 - BLOCKWidth * 2, TOTALBLOCKHeight * 3,true,false);
        retro = new Toggle("retro", Button.bigFontSize, 4, frameWidth / 2 + BLOCKWidth * 2, TOTALBLOCKHeight * 3,true,false);
        rainbow = new Toggle("rainbow", Button.bigFontSize, 4, frameWidth / 2, TOTALBLOCKHeight * 4,true,false);
        switch (Preferences.theme) {
            case minimalistic -> mini.setActive(true);
            case color -> color.setActive(true);
            case pattern -> pattern.setActive(true);
            case retro -> retro.setActive(true);
            case rainbow -> rainbow.setActive(true);
        }
        ghost = new Toggle("ghost", Button.bigFontSize, 5, frameWidth / 2, (int) (TOTALBLOCKHeight * 4.5F),true, Preferences.showGhost);
        addTgl(mini);
        addTgl(color);
        addTgl(pattern);
        addTgl(retro);
        //addTgl(rainbow);
        addTgl(ghost);
        addBtn(new Button("menu", Button.normalFontSize, 6, frameWidth - BLOCKWidth, TOTALBLOCKHeight * 7,true,true));
    }

    @Override
    public void selectionAction() {
        switch (selection) {
            case 1 -> {
                deactivateAll();
                mini.setActive(true);
                Preferences.theme = themes.minimalistic;
            }
            case 2 -> {
                deactivateAll();
                color.setActive(true);
                Preferences.theme = themes.color;
            }
            case 3 -> {
                deactivateAll();
                pattern.setActive(true);
                Preferences.theme = themes.pattern;
            }
            case 4 -> {
                deactivateAll();
                retro.setActive(true);
                Preferences.theme = themes.retro;
            }
            case 5 -> {
                ghost.toggleActive();
                Preferences.showGhost = ghost.isActive();
            }
            case 6 -> {
                selection = 0;
                Render.Screen = RenderUtil.ScreenState.Menu;
            }
        }
    }

    private void deactivateAll() {
        mini.setActive(false);
        color.setActive(false);
        pattern.setActive(false);
        retro.setActive(false);
        rainbow.setActive(false);
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
