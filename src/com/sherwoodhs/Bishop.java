package com.sherwoodhs;

public class Bishop extends Piece{

    public Bishop(boolean white) {
        super(white);
    }

    public boolean canMove(Board board, Spot start, Spot end) {
        if(end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }

        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());

        return (x == y && (x != 0 || y != 0));
    }
}
