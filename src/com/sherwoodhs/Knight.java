package com.sherwoodhs;

public class Knight extends Piece {
    public Knight(boolean white)
    {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end){

        // we can't move the piece to a spot that has
        // a piece of the same color

        if ((!end.isEmpty()) && (end.getPiece().isWhite() == this.isWhite())) {
            return false;
        }

        //  Knight must move two spaces in one direction, and
        //  one space in the other.
        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());
        return x * y == 2;
    }

    @Override
    public boolean performMove(Board board, Spot start, Spot end) {
        //  No special items to be checked for after a move
        return true;
    }

    public String toString() {
        if (this.isWhite()) {
            return " wN ";
        } else {
            return " bN ";
        }
    }
}
