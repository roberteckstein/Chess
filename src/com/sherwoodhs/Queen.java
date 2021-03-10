package com.sherwoodhs;

public class Queen extends Piece{

    // Andrew Hiser and Hayden deBoer

    public Queen(boolean white) {
        super(white);
    }

    public boolean canMove(Board board, Spot start, Spot end) {
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
