package Tetris.Game;

import Tetris.Pieces.FallingPiece;
import Tetris.Pieces.Piece;
import Tetris.Pieces.PieceUtil;

public class RotationSystem {

    public static void rotateCW() {
        FallingPiece fallingPiece = Board.getFallingPiece();
        Piece piece = fallingPiece.getType().getPiece();
        if(fallingPiece.getType() == PieceUtil.types.O)
            return;

        int undoX = fallingPiece.getX();
        int undoY = fallingPiece.getY();
        int undoRotation = loopAround(fallingPiece.getRotation(), 0, 3);
        fallingPiece.rotateCW();
        int rotation = loopAround(fallingPiece.getRotation(), 0, 3);

        for (int i=0; i<5; i++) {
            int xOffset = 0;
            int yOffset = 0;
            xOffset += piece.getRotationPointX(undoRotation, i);
            yOffset += piece.getRotationPointY(undoRotation, i);
            xOffset -= piece.getRotationPointX(rotation, i);
            yOffset -= piece.getRotationPointY(rotation, i);
            if(!Board.collide(xOffset,yOffset)) {
                fallingPiece.addtoX(xOffset);
                fallingPiece.addtoY(yOffset);
                return;
            }
        }

        fallingPiece.setX(undoX);
        fallingPiece.setY(undoY);
        fallingPiece.rotateCCW();
    }
    public static void rotateCCW() {
        FallingPiece fallingPiece = Board.getFallingPiece();
        Piece piece = fallingPiece.getType().getPiece();
        if(fallingPiece.getType() == PieceUtil.types.O)
            return;

        int undoX = fallingPiece.getX();
        int undoY = fallingPiece.getY();
        int undoRotation = loopAround(fallingPiece.getRotation(), 0, 3);
        fallingPiece.rotateCCW();
        int rotation = loopAround(fallingPiece.getRotation(), 0, 3);

        for (int i=0; i<5; i++) {
            int xOffset = 0;
            int yOffset = 0;
            xOffset += piece.getRotationPointX(undoRotation, i);
            yOffset -= piece.getRotationPointY(rotation, i);
            yOffset += piece.getRotationPointY(undoRotation, i);
            xOffset -= piece.getRotationPointX(rotation, i);
            if(!Board.collide(xOffset,yOffset)) {
                fallingPiece.addtoX(xOffset);
                fallingPiece.addtoY(yOffset);
                return;
            }
        }

        fallingPiece.setX(undoX);
        fallingPiece.setY(undoY);
        fallingPiece.rotateCW();
    }

    private static int loopAround(int i, int min, int max) {
        return Math.abs((i - min) % (max - min + 1)) + min;
    }

}
