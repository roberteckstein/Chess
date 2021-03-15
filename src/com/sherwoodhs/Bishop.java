package com.sherwoodhs;

public class Bishop extends Piece{

    // Hayden deBoer

    public Bishop(boolean white) {
        super(white);
    }

    public boolean canMove(Board board, Spot start, Spot end) {

      int deltaX = end.getX() - start.getX();
      int deltaY = end.getY() - start.getY();

      if(end.getPiece().isWhite() == this.isWhite()) {
          return false;
      }

      if(Math.abs(deltaX) != Math.abs(deltaY)) {
          // Checking if not moved diagonally
          return false;
      } 
      
      //Moving up right
      if(deltaX > 0 && deltaY > 0){
          for(int i = 0; i < Math.abs(deltaX); i++){
              if(!board.getBox(start.getX() + i,  start.getY() + i).isEmpty()) {
                  return false;
              }
              return true;
          }
      }
      //Moving up left
      else if(deltaX < 0 && deltaY > 0){
          for(int i = 0; i < Math.abs(deltaX); i++) {
              if(!board.getBox(start.getX() - i, start.getY() + i).isEmpty()) {
                  return false;
              }
              return true;
          }
      }
      //Moving down right
      else if(deltaX > 0 && deltaY < 0){
          for(int i = 0; i < Math.abs(deltaX); i++) {
              if(!board.getBox(start.getX() + i, start.getY() - i).isEmpty()) {
                  return false;
              }
              return true;
          }
      }
      //Moving down left
      else if(deltaX < 0 && deltaY < 0){
          for(int i = 0; i < Math.abs(deltaX); i++) {
              if(!board.getBox(start.getX() - i, start.getY() - i).isEmpty()) {
                  return false;
              }
              return true;
          }
      }

      // generic return statement to avoid error while testing
      return false;
    }

    @Override
    public boolean performMove(Board board, Spot start, Spot end) {
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
