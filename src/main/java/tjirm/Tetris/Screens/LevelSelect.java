package main.java.tjirm.Tetris.Screens;

import main.java.tjirm.Tetris.Game.GameLoop;
import main.java.tjirm.Tetris.Preferences;
import main.java.tjirm.Tetris.Rendering.Render;
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

        int i = 0;
        for(int x=0; x <= MAXXSelection - 1; x++) {
            for(int y=0; y <= MAXYSelection - 1; y++) {
                i = x * MAXYSelection + y + 1;
                int xOrigin = (frameWidth - TOTALBLOCKWidth * 4) / (MAXXSelection - 1) * x + TOTALBLOCKWidth * 2;
                int yOrigin = (frameHeight - TOTALBLOCKHeight * 3) / MAXYSelection * y + TOTALBLOCKHeight * 2;
                addBtn(new Button(String.valueOf(i), Button.bigFontSize, i, xOrigin, yOrigin, true));
            }
        }
        addBtn(new Button("endlessMd", Button.bigFontSize, i + 1, frameWidth / 2, (int) (TOTALBLOCKHeight * 6.5F), true, false));
        addBtn(new Button("menu", Button.normalFontSize, i + 2, frameWidth - BLOCKWidth, TOTALBLOCKHeight * 7, true, true));
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
            Render.Screen = RenderUtil.ScreenState.Game;
            GameLoop.game.startLevel(Scoring.endlessLevel);
        }
        if(selection == RenderUtil.LevelSelectionX * RenderUtil.LevelSelectionY + 2) {
            selection = 0;
            Render.Screen = RenderUtil.ScreenState.Menu;
        }
    }

    @Override
    public void exitAction() {
        Render.Screen = RenderUtil.ScreenState.Menu;
    }

    @Override
    public void draw(Graphics g) {
        int BLOCKWidth = blockWidth * 3;
        int TOTALBLOCKHeight = totalBlockHeight * 3;
        int TOTALBLOCKWidth = totalBlockWidth * 3;

        int MAXXSelection = RenderUtil.LevelSelectionX;
        int MAXYSelection = RenderUtil.LevelSelectionY;

        g.setColor(Preferences.Primary);
        g.fillRect(0, 0, frameWidth, frameHeight);

        g.setColor(Text.color);

        g.setFont(Text.textHeaderFont);
        g.drawString(Text.getStr("levels"), frameWidth / 2 - Text.getHeadWidth("levels") / 2, TOTALBLOCKHeight);
    }
}