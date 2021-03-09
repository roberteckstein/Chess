package com.sherwoodhs;

public class Pawn extends Piece {

  private boolean firstMove = true;

  public Pawn(boolean white) {
    super(white);
  }

  public void promotion() {
    
  }

  @Override
  public boolean canMove(Board board,Spot start, Spot end) {
    
    //Stops pawn from moving onto space with another white piece
    if (end.getPiece().isWhite() == this.isWhite()) {
      return false;
    }

    int x = Math.abs(start.getX() - end.getX());
    int y = Math.abs(start.getY() - end.getY());
    
    //If pawn moves forward 1 space
    if (y == 1) {
      return true;

    //If the pawn is capturing another piece moving 1 diagonally is legal
    } else if (x + y == 2 || x + y == 0) {
      if(!end.getPiece.isWhite()) {
        return true;
      }else{
        return false;
      }
      
    //If it's the pawns first move, moving two spaces is legal
    } else if (firstMove && y == 2) {
      firstMove = false;
      return true;
    }
  }
}

