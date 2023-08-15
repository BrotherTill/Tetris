package main.java.tjirm.Tetris.Pieces;

import main.java.tjirm.Tetris.Preferences;
import main.java.tjirm.Tetris.Rendering.RenderUtil;
import main.java.tjirm.Tetris.Rendering.Themes;

import java.awt.*;
import java.util.Objects;

import static main.java.tjirm.Tetris.Rendering.RenderUtil.blockPadding;

public class Block {

    private String type;
    private boolean filled;
    private Color color;
    private String pattern;
    private String retro;
    private boolean ignorePadding;
    private boolean typeSensitive;

    private boolean above;
    private boolean RUdiagonal;
    private boolean toRight;
    private boolean RDdiagonal;
    private boolean below;
    private boolean LDdiagonal;
    private boolean toLeft;
    private boolean LUdiagonal;

    public Block() {
        init();
    }

    public Block(boolean filled) {
        init();
        this.filled = filled;
    }

    public Block(boolean filled, Color color, String Pattern, String Retro, boolean ignorePadding) {
        this.filled = filled;
        this.color = color;
        this.pattern = Pattern;
        this.retro = Retro;
        this.ignorePadding = ignorePadding;
    }

    private void init() {
        this.type = "default";
        this.filled = false;
        this.color = RenderUtil.Primary;
        this.pattern = "A1";
        this.retro = "A1";
        this.ignorePadding = false;
        this.typeSensitive = false;
    }

    public void paintGhost(Graphics2D g, int x, int y, Block[][] field, int XIndex, int YIndex) {
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5F));
        draw(g, x, y, field, XIndex, YIndex);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));
    }

    public void paint(Graphics g, int x, int y, Block[][] field, int XIndex, int YIndex) {
        draw(g, x, y, field, XIndex, YIndex);
    }

    private void draw(Graphics g, int x, int y, Block[][] field, int XIndex, int YIndex) {
        if(!filled)
            return;
        above =         OffsetisFilled(field, XIndex, YIndex, 0, -1);
        LUdiagonal =    OffsetisFilled(field, XIndex, YIndex, -1, -1);
        toRight =       OffsetisFilled(field, XIndex, YIndex, 1, 0);
        RUdiagonal =    OffsetisFilled(field, XIndex, YIndex, 1, -1);
        below =         OffsetisFilled(field, XIndex, YIndex, 0, 1);
        RDdiagonal =    OffsetisFilled(field, XIndex, YIndex, 1, 1);
        toLeft =        OffsetisFilled(field, XIndex, YIndex, -1, 0);
        LDdiagonal =    OffsetisFilled(field, XIndex, YIndex, -1, 1);

        switch (Preferences.theme) {
            case minimalistic ->    paintColor(g, x, y, RenderUtil.Primary);
            case color ->           paintColor(g, x, y, Preferences.Primary);
            case retro ->           paintRetro(g, x, y);
            case pattern ->         paintPattern(g, x, y);
            case rainbow ->         paintRainbow(g, x, y);
        }
    }

    public void paintColor(Graphics g, int x, int y, Color color) {
        g.setColor(color);
        if(ignorePadding) {
            g.fillRect(x, y, RenderUtil.totalBlockWidth, RenderUtil.totalBlockHeight);
            return;
        }
        g.fillRect(x + blockPadding, y + blockPadding, RenderUtil.blockWidth, RenderUtil.blockHeight);
        if(above)
            g.fillRect(x + blockPadding, y, RenderUtil.blockWidth, blockPadding);
        if(toRight)
            g.fillRect(x + RenderUtil.blockWidth + blockPadding, y + blockPadding, blockPadding, RenderUtil.blockHeight);
        if(below)
            g.fillRect(x + blockPadding, y + RenderUtil.blockHeight + blockPadding, RenderUtil.blockWidth, blockPadding);
        if(toLeft)
            g.fillRect(x, y + blockPadding, blockPadding, RenderUtil.blockHeight);
        if(toRight && above && RUdiagonal)
            g.fillRect(x + RenderUtil.blockWidth + blockPadding, y, blockPadding, blockPadding);
        if(toRight && below && RDdiagonal)
            g.fillRect(x + RenderUtil.blockWidth + blockPadding, y + RenderUtil.blockHeight + blockPadding, blockPadding, blockPadding);
        if(toLeft && above && LUdiagonal)
            g.fillRect(x, y, blockPadding, blockPadding);
        if(toLeft && below && LDdiagonal)
            g.fillRect(x, y + RenderUtil.blockHeight + blockPadding, blockPadding, blockPadding);
    }

    public void paintRetro(Graphics g, int x, int y) {
        Image image = null;
        try {
            image = Themes.getRetroLook(retro);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
        g.drawImage(image, x, y, null);
        g.setColor(Preferences.Background);
        if(ignorePadding)
            return;
        drawMask(g, x, y);
    }

    public void paintPattern(Graphics g, int x, int y) {
        Image image = Themes.getPattern(pattern);
        g.drawImage(image, x, y, null);
        g.setColor(Preferences.Background);
        if(ignorePadding)
            return;
        drawMask(g, x, y);
    }

    private void drawMask(Graphics g, int x, int y) {
        if(!above)
            g.fillRect(x + blockPadding, y, RenderUtil.blockWidth, blockPadding);
        if(!toRight)
            g.fillRect(x + RenderUtil.blockWidth + blockPadding, y + blockPadding, blockPadding, RenderUtil.blockHeight);
        if(!below)
            g.fillRect(x + blockPadding, y + RenderUtil.blockHeight + blockPadding, RenderUtil.blockWidth, blockPadding);
        if(!toLeft)
            g.fillRect(x, y + blockPadding, blockPadding, RenderUtil.blockHeight);
        if(!toRight || !above || !RUdiagonal)
            g.fillRect(x + RenderUtil.blockWidth + blockPadding, y, blockPadding, blockPadding);
        if(!toRight || !below || !RDdiagonal)
            g.fillRect(x + RenderUtil.blockWidth + blockPadding, y + RenderUtil.blockHeight + blockPadding, blockPadding, blockPadding);
        if(!toLeft || !above || !LUdiagonal)
            g.fillRect(x, y, blockPadding, blockPadding);
        if(!toLeft || !below || !LDdiagonal)
            g.fillRect(x, y + RenderUtil.blockHeight + blockPadding, blockPadding, blockPadding);
    }

    public void paintRainbow(Graphics g, int x, int y) {
        g.setColor(color);
        g.fillRect(x, y, RenderUtil.blockWidth, RenderUtil.blockHeight);
        if(toRight)
            g.fillRect(x + RenderUtil.blockWidth, y, blockPadding * 2, RenderUtil.blockHeight);
        if(below)
            g.fillRect(x, y + RenderUtil.blockHeight, RenderUtil.blockWidth, blockPadding * 2);
        if(toRight && below && RDdiagonal)
            g.fillRect(x + RenderUtil.blockWidth, y + RenderUtil.blockHeight, blockPadding * 2, blockPadding * 2);
    }

    public boolean OffsetisFilled(Block[][] field, int x, int y, int xOffset, int yOffset) {
        if(y + yOffset >= field.length || x + xOffset >= field[0].length)
            return false;
        if(y + yOffset < 0 || x + xOffset < 0)
            return false;
        if(typeSensitive)
            return field[y + yOffset][x + xOffset].getType().equals(type);
        return field[y + yOffset][x + xOffset].isFilled();
    }

    public Block setType(String type) {
        this.typeSensitive = true;
        this.type = type;
        return this;
    }
    public String getType() {
        return type;
    }
    public boolean isFilled() {
        return filled;
    }
    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    @Override
    public boolean equals(Object o) {       //not from StackOverflow *wink wink*
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Block block = (Block) o;
        return Objects.equals(filled, block.filled);
    }

}