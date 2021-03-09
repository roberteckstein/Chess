package com.sherwoodhs;

public class Pawn extends Piece {

  private int pawnMoves = 0;

  public Pawn(boolean white) {
    super(white);
  }

  public void promotion() {
    new Spot =(end.getX(), end.getY(), new Queen(true))
    
  }

  @Override
  public boolean canMove(Board board,Spot start, Spot end) {
    
    if (end.getPiece().isWhite() == this.isWhite()) {
      return false;
    }

    int x = Math.abs(start.getX() - end.getX());
    int y = Math.abs(start.getY() - end.getY());
    //If pawn moves forward 1 space
    if (x + y == 1) {
      pGoal += 1;
      return true;
    //If the pawn is capturing another piece moving 1 diagonally is legal
    } else if (x + y == 2) {
      if(!end.getPiece.isWhite()) {
        pGoal += 1;
        return true;
      }else{
        return false;
      }
    //If it's the pawns first move, moving two spaces is legal
    } else if (pawnMoves == 0 && x + y == 2) {
      return true;
    }
  }
}

