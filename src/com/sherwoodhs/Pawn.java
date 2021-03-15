package com.sherwoodhs;

public class Pawn extends Piece {

  private boolean firstMove = true;

  public Pawn(boolean white) {
    super(white);
  }

  //For testing
  @Override
  public boolean performMove(Board board, Spot start, Spot end) {
    return true;
  }
  
  /* Unfinished
  @Override
  public boolean performMove(Board board, Spot start, Spot end) {
    // Will need to do something here to perform promotion
    int x = end.getX();
    int y = end.getY();

    

    this.getPiece().setKilled();
    move.getEnd().setPiece(move.getStart().getPiece());
    move.getStart().setPiece(null);
    return true;
  }
*/
    @Override
    public boolean canMove(Board board,Spot start, Spot end) {

      //Stops pawn from moving onto space with another white piece
      if (end.getPiece().isWhite() == this.isWhite()) {
        return false;
      }

      int x = Math.abs(start.getX() - end.getX());
      int y = Math.abs(start.getY() - end.getY());


      //If pawn moves forward 1 space
      if (y == 1 && x == 0 && end.isEmpty()) {
        firstMove = false;
        return true;

      //If the pawn is capturing another piece moving 1 diagonally it is legal
      } else if (x + y == 2 || x + y == 0) {
        if(end.isEmpty()) {
          return false;
        }else{
          firstMove = false;
          return true;
        }

      //If it's the pawns first move, moving two spaces is legal
      } else if (firstMove && y == 2 && x == 0) {
        if (end.getY() - 1 == 1 && !end.isEmpty()) {
          return false;
        } else {
          firstMove = false;
          return true;
        }
      } else {
        return false;
      }
    }

    public String toString() {
      if (this.isWhite()) {
        return " wP ";
      } else {
        return " bP ";
      }
    }

}

