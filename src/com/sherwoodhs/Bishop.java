package com.sherwoodhs;

public class Bishop extends Piece{

    // Hayden deBoer

    public Bishop(boolean white) {
        super(white);
    }

    public boolean canMove(Board board, Spot start, Spot end) {

      /*
            int deltaX = end.getX() - start.getX();
      int deltaY = end.getY() - start.getY();

      if(end.getPiece().isWhite() == this.isWhite()) {
        return false;
      }

      if(Math.abs(deltaX) != Math.abs(deltaY)) {
          // Checking if not moved diagonally
           return false;
      } 
      
      //Moving forward right
      if (deltaX > 0 && deltaY > 0){
        for (int i = 0; i < Math.abs(deltaX); i++){

          if (!board.getBox(start.getX() + i,  start.getY() + i;).isEmpty()) {
              return false;
            }
            return true;
        }
      
  
      }
      //Moving Forward left
      else if(deltaX > 0 && deltaY < 0){
        
      }
      //Moving downward right
      else if(deltaX < 0 && deltaY > 0){
      
      }
      //Moving downward left
      else{

      }
      */

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
                  for(int i = start.getX() + 1; i < end.getX() - 1; i++) {
                      if(!board.getBox(i, i).isEmpty()) {
                          return false;
                      }
                  }
                  return true;
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
