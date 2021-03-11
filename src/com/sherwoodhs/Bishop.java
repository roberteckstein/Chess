package com.sherwoodhs;

public class Bishop extends Piece{

    // Hayden deBoer

    public Bishop(boolean white) {
        super(white);
    }

    public boolean canMove(Board board, Spot start, Spot end) {

      if(end.getPiece().isWhite() == this.isWhite()) {
        return false;
      }

      if(Math.abs(end.getX() - start.getX()) != Math.abs(end.getY() - start.getY())) {
          // Checking if not moved diagonally
           return false;
      } else {
          if(Math.abs(start.getY() - end.getX()) != Math.abs(start.getY() - end.getY())) {
                // Checking if movement was diagonal
                return false;
            } else {
              if(start.getX() < end.getX() && start.getY() < end.getY()) {
                  // Moving right/up
              } else if(start.getX() > end.getX() && start.getY() < end.getY()) {
                  // Moving left/up
              } else if(start.getX() < end.getX() && start.getY() > end.getY()) {
                  // Moving right/down
              } else if(start.getX() > end.getX() && start.getY() > end.getY()) {
                  // Moving left/down
              }
          }
        }
        
      // generic return statement to avoid error while testing
      return false;
    }

    public String toString() {
        if (this.isWhite()) {
            return " wB ";
        } else {
            return " bB ";
        }
    }
}
