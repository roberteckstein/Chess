package com.sherwoodhs;

public class Queen extends Piece{

    public Queen(boolean white) {
        super(white);
    }

    public boolean canMove(Board board, Spot start, Spot end) {
        return false;
    }
}
