package main.java.tjirm.Tetris.Screens;

import main.java.tjirm.Tetris.Game.GameLoop;
import main.java.tjirm.Tetris.Preferences;
import main.java.tjirm.Tetris.Rendering.RenderUtil;
import main.java.tjirm.Tetris.Rendering.Text;
import main.java.tjirm.Tetris.Scoring;
import main.java.tjirm.Tetris.Screens.Elements.Button;

import java.awt.*;

public class LevelSelect extends Screen {

    @Override
    public void init() {
        int BLOCKWidth = blockWidth * 3;
        int TOTALBLOCKHeight = totalBlockHeight * 3;
        int TOTALBLOCKWidth = totalBlockWidth * 3;
        int MAXXSelection = RenderUtil.LevelSelectionX;
        int MAXYSelection = RenderUtil.LevelSelectionY;

        int i;
        for(int x=0; x <= MAXXSelection - 1; x++) {
            for(int y=0; y <= MAXYSelection - 1; y++) {
                i = x * MAXYSelection + y + 1;
                int xOrigin = (frameWidth - TOTALBLOCKWidth * 4) / (MAXXSelection - 1) * x + TOTALBLOCKWidth * 2;
                int yOrigin = (frameHeight - TOTALBLOCKHeight * 3) / MAXYSelection * y + TOTALBLOCKHeight * 2;
                addBtn(String.valueOf(i), Button.bigFontSize, xOrigin, yOrigin, true);
            }
        }
        addBtn("Endless", "endlessMd", Button.bigFontSize, frameWidth / 2, (int) (TOTALBLOCKHeight * 6.5F), true, false);
        addBtn("Menu", "menu", Button.normalFontSize, frameWidth - BLOCKWidth, TOTALBLOCKHeight * 7, true, true);
    }

    @Override
    protected void selectionAction() {
        if(selection.startsWith("Special")) {
            Screens.setScreen(Screens.ScreenState.Game);
            GameLoop.game.startLevel(Integer.parseInt(selection.substring(7)));
            selection = "null";
            return;
        }
        if(selection.equals("Endless")){
            selection = "null";
            Screens.setScreen(Screens.ScreenState.Game);
            GameLoop.game.startLevel(Scoring.endlessLevel);
            return;
        }
        if(selection.equals("Menu"))
            exitAction();
    }

    @Override
    public void exitAction() {
        selection = "null";
        Screens.setScreen(Screens.ScreenState.Menu);
    }

    @Override
    public void draw(Graphics g) {
        int BLOCKWidth = blockWidth * 3;
        int TOTALBLOCKHeight = totalBlockHeight * 3;
        int TOTALBLOCKWidth = totalBlockWidth * 3;

        int MAXXSelection = RenderUtil.LevelSelectionX;
        int MAXYSelection = RenderUtil.LevelSelectionY;

        g.setColor(Preferences.Frame);
        g.fillRect(0, 0, frameWidth, frameHeight);

        g.setColor(Text.color);

        g.setFont(Text.textHeaderFont);
        g.drawString(Text.getStr("levels"), frameWidth / 2 - Text.getHeadWidth("levels") / 2, TOTALBLOCKHeight);
    }
}