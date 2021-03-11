package com.sherwoodhs;

public class Bishop extends Piece{

    // Hayden deBoer

    public Bishop(boolean white) {
        super(white);
    }

    public boolean canMove(Board board, Spot start, Spot end) {

      if (end.getPiece().isWhite() == this.isWhite()) {
        return false;
        
      } else {

        if(end.getX() - start.getX() != end.getY() - start.getY()) {
            // Checking if not moved diagonally
            return false;
            
        } else {
            if(Math.abs(start.getY() - end.getX()) != Math.abs(start.getY() - end.getY())) {
                // Checking if movement was diagonal
                return false;
            }
        }
    }
    }

    public String toString() {
        if (this.isWhite()) {
            return " wB ";
        } else {
            return " bB ";
        }
    }
}
