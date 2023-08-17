package main.java.tjirm.Tetris.Screens;

import main.java.tjirm.Tetris.Preferences;
import main.java.tjirm.Tetris.Rendering.Text;
import main.java.tjirm.Tetris.Rendering.Themes.themes;
import main.java.tjirm.Tetris.Screens.Elements.Button;
import main.java.tjirm.Tetris.Screens.Elements.DropDown;

import java.awt.*;

public class Options extends Screen {

    @Override
    public void init() {
        int BLOCKWidth = blockWidth * 3;
        int TOTALBLOCKHeight = totalBlockHeight * 3;

        String select = Preferences.theme.toString().toLowerCase();
        addDropDown(new DropDown<themes>("Themes", DropDown.bigFontSize, frameWidth / 2, TOTALBLOCKHeight * 2)
                .addOption("minimalistic", themes.minimalistic)
                .addOption("color", themes.color)
                .addOption("pattern", themes.pattern)
                .addOption("retro", themes.retro)
                .setSelected(select));
        addTgl("Ghost", "ghost", Button.bigFontSize, frameWidth / 2, (int) (TOTALBLOCKHeight * 4.5F),true, Preferences.showGhost);
        addBtn("Menu", "menu", Button.normalFontSize, frameWidth - BLOCKWidth, TOTALBLOCKHeight * 7,true,true);
    }

    @Override
    protected void selectionAction() {
        if(selection.startsWith("Themes"))
            Preferences.theme = (themes) getDropDownbyName("Themes").select(selection.substring(6));
        switch (selection) {
            case "Ghost" -> {
                getTogglebyName("Ghost").toggleActive();
                Preferences.showGhost = getTogglebyName("Ghost").getActive();
            }
            case "Menu" -> exitAction();
        }
    }

    @Override
    public void exitAction() {
        selection = "null";
        Screens.setScreen(Screens.ScreenState.Menu);
    }

    @Override
    public void draw(Graphics g) {
        int BLOCKHeight = blockHeight * 3;
        int BLOCKWidth = blockWidth * 3;
        int TOTALBLOCKHeight = totalBlockHeight * 3;
        int TOTALBLOCKWidth = totalBlockWidth * 3;
        int BLOCKPadding = blockPadding * 3;

        g.setColor(Preferences.Frame);
        g.fillRect(0, 0, frameWidth, frameHeight);

        g.setColor(Text.color);
        g.setFont(Text.textHeaderFont);
        g.drawString(Text.getStr("options"), frameWidth / 2 - Text.getHeadWidth("options") / 2, TOTALBLOCKHeight);

    }

}
