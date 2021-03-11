package com.sherwoodhs;

public class Queen extends Piece{

    // Andrew Hiser and Hayden deBoer

    public Queen(boolean white) {
        super(white);
    }

    public boolean canMove(Board board, Spot start, Spot end) {

        int deltaX = end.getX() - start.getX();
        int deltaY = end.getY() - start.getY();

        if(end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }

        /*
        if(Math.abs(deltaX) = Math.abs(deltaY)) {
          // Checking if moved diagonally

        } else if(deltaX == 0 ^ deltaY == 0) {

        }*/

        return false;
    }

    public String toString() {
        if (this.isWhite()) {
            return " wQ ";
        } else {
            return " bQ ";
        }
    }

}
