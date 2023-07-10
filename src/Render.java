import java.awt.*;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.Instant;

public class Render extends JPanel implements ActionListener{

    private final Font Text_font = new Font("Roboto", Font.PLAIN, 30);
    private final FontMetrics Font_metrics = getFontMetrics(Text_font);
    private final int HOLD_width = Font_metrics.stringWidth("HOLD");
    private final int NEXT_width = Font_metrics.stringWidth("NEXT");
    private final Color Background = Color.DARK_GRAY;
    private final Color Primary = Color.WHITE;

    private final int frameRate = 165;
    Timer renderCaller;

    private static final int blockHeight = Board.getBlockHeight();
    private static final int blockWidth = Board.getBlockWidth();
    private static final int blockPadding = Board.getBlockPadding();
    private static final int totalBlockHeight = Board.getTotalBlockHeight();
    private static final int totalBlockWidth = Board.getTotalBlockWidth();
    private final int frameHeight;
    private final int frameWidth;

    private static final int fieldHeight = Board.getFieldHeight();
    private static final int fieldWidth = Board.getFieldWidth();

    private static long GameOverOpacity = 0;
    private static final float GameOverAlpha = 218;
    private static final float fadeDuration = 1000L;
    private static Duration deltaTime = Duration.ZERO;
    private static Instant beginTime = Instant.now();

    private FallingPiece fallingPiece;

    public Render() {
        frameWidth = totalBlockWidth * 10 + totalBlockWidth * 14;
        frameHeight = totalBlockHeight * 20 + totalBlockHeight * 2;

        setFocusable(true);
        setPreferredSize(new Dimension(frameWidth, frameHeight));

        addKeyListener(new Input());

        renderCaller = new Timer(1000 / frameRate, this);
        renderCaller.start();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        repaint();
        deltaTime = Duration.between(beginTime, Instant.now());
        beginTime = Instant.now();
    }

    @Override
    public void paintComponent(Graphics g) {
        //System.out.println("Starting paint");
        //super.paint(g);
        drawScreen(g);

        if(Board.GameOver) {
            drawGameOver(g);
        }
    }
    public void drawGameOver(Graphics g) {
        g.setColor(new Color(14, 14, 14, (int) GameOverOpacity));
        g.fillRect(0, 0, frameWidth, frameHeight);
        if(GameOverOpacity <= GameOverAlpha) {
            GameOverOpacity += (GameOverAlpha / fadeDuration) * (float) deltaTime.toMillis();
            return;
        }
        g.setColor(new Color(203, 5, 5));
        g.setFont(Text_font);
        g.drawString("GameOver", (frameWidth / 2) - (Font_metrics.stringWidth("GameOver") / 2), (frameHeight / 2) - (frameHeight / 5) - blockPadding);
        g.drawString("Level: " + Scoring.getScore(), (frameWidth / 2) - (Font_metrics.stringWidth("Level: " + Scoring.getScore()) / 2), (frameHeight / 2) - (Font_metrics.getHeight() / 2) + blockPadding);
        g.drawString("Score: " + Scoring.getScore(), (frameWidth / 2) - (Font_metrics.stringWidth("Score: " + Scoring.getScore()) / 2), (frameHeight / 2) + (Font_metrics.getHeight() / 2) + blockPadding);
        renderCaller.stop();
    }

    public void drawScreen(Graphics g) {
        fallingPiece = Board.getFallingPiece();
        int nextLength = fallingPiece.getNextLength();
        Piece tempPiece;
        
        g.setColor(Background);
        g.fillRect(0,0,frameWidth,frameHeight);

        g.setColor(Primary);
        g.fillRect(0,0, frameWidth, blockHeight + blockPadding);
        g.fillRect(0,0, blockWidth + blockPadding, frameHeight);
        g.fillRect(frameWidth - blockWidth - blockPadding,0, blockHeight + blockPadding, frameHeight);
        g.fillRect(0, frameHeight - blockHeight - blockPadding, frameWidth, blockHeight + blockPadding);
        g.fillRect(totalBlockWidth * 6 + blockPadding, 0, blockWidth, frameHeight);
        g.fillRect(blockWidth + blockPadding, totalBlockHeight * 6 + blockPadding, totalBlockWidth * 6 - blockWidth, totalBlockHeight * 15);
        g.fillRect(totalBlockWidth * 17 + blockPadding, 0, blockWidth, frameHeight);
        g.fillRect(totalBlockWidth * 18 - blockPadding, totalBlockHeight * 6 + blockPadding, totalBlockWidth * 6 - blockWidth, blockHeight);
        if(nextLength > (fieldHeight - 8) / 3) {
            nextLength = (fieldHeight - 8) / 3;
        }
        g.fillRect(totalBlockWidth * 18 - blockPadding, (8 + (nextLength * 3)) * totalBlockHeight + blockPadding, totalBlockWidth * 6 - blockWidth, (13 - (nextLength * 3)) * totalBlockHeight);

        g.setColor(Background);
        g.setFont(Text_font);
        g.drawString("HOLD",(totalBlockWidth) + (totalBlockWidth * 5 / 2) - (HOLD_width / 2), blockHeight - blockPadding);
        g.drawString("HOLD",(totalBlockWidth * 18) + (totalBlockWidth * 5 / 2) - (HOLD_width / 2), blockHeight - blockPadding);
        g.drawString("NEXT",(totalBlockWidth * 18) + (totalBlockWidth * 5 / 2) - (NEXT_width / 2),totalBlockHeight * 7 - blockPadding * 3);

        //

        g.setColor(Primary);

        drawField(Board.getField(), 7, -19, g);

        drawField(fallingPiece.getPieceField(), (7 + fallingPiece.getX()), (1 + fallingPiece.getY()), g);

        for(int i=0; i < nextLength; i++) {
            tempPiece = fallingPiece.getNextTypes()[i].getPiece();
            drawField(tempPiece.getField(), 18 + tempPiece.getRxOffset(), 6.5F + tempPiece.getRyOffset() + (i * 3), g);
        }

        tempPiece = Board.getHoldSlot1().getPiece();
        drawField(tempPiece.getField(), 1 + tempPiece.getRxOffset(), 1 + tempPiece.getRyOffset(), g);
        tempPiece = Board.getHoldSlot2().getPiece();
        drawField(tempPiece.getField(), 18 + tempPiece.getRxOffset(), 1 + tempPiece.getRyOffset(), g);

    }

    private void drawField(Block[][] field, float x, float y, Graphics g) {
        for(int rY=0; rY < field.length; rY++) {
            for(int rX=0; rX < field[0].length; rX++) {
                if(field[rY][rX].isFilled()) {
                    drawFieldBlock(field, x, y, rX, rY, g);
                }
            }
        }
    }

    private void drawFieldBlock(Block[][] field, float x, float y, int rX, int rY, Graphics g) {
        g.fillRect((int) ((x + rX) * totalBlockWidth + blockPadding),(int) ((y + rY) * totalBlockHeight + blockPadding), blockWidth, blockHeight);

        if(rX != field[0].length - 1) {
            if(field[rY][rX + 1].isFilled()) {
                g.fillRect((int) ((x + rX + 1) * totalBlockWidth - blockPadding), (int) ((y + rY) * totalBlockHeight + blockPadding), blockPadding * 2, blockHeight);
            }
        }
        if(rY != field.length - 1) {
            if(field[rY + 1][rX].isFilled()) {
                g.fillRect((int) ((x + rX) * totalBlockWidth + blockPadding), (int) ((y + rY + 1) * totalBlockHeight - blockPadding), blockWidth, blockPadding * 2);
            }
        }
        if(rX != field[0].length - 1 && rY != field.length - 1) {
            if(     field[rY + 1][rX + 1].isFilled() &&
                    field[rY][rX + 1].isFilled() &&
                    field[rY + 1][rX].isFilled()) {
                g.fillRect((int) ((x + rX + 1) * totalBlockWidth -blockPadding), (int) ((y + rY + 1) * totalBlockHeight - blockPadding), blockPadding * 2, blockPadding * 2);
            }
        }
    }

}
