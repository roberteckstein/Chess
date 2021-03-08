package com.sherwoodhs;

public class Bishop extends Piece{

    public Bishop(boolean white) {
        super(white);
    }

    public boolean canMove(Board board, Spot start, Spot end) {
        return false;
    }
}
