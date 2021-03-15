package com.sherwoodhs;

public class Pawn extends Piece {

  private boolean firstMove = true;

  public Pawn(boolean white) {
    super(white);
  }

  @Override
  public boolean performMove(Board board, Spot start, Spot end) {
    firstMove = false;
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
    public boolean canMove(Board board, Spot start, Spot end) {

      //Stops pawn from moving onto space with another white piece
      if ((!end.isEmpty()) && (end.getPiece().isWhite() == this.isWhite())) {
        return false;
      }

      //This is correct. The x and y are purposely reversed
      int y = Math.abs(start.getX() - end.getX());
      int x = Math.abs(start.getY() - end.getY());

      // If pawn moves forward 1 space
      if (y == 1 && x == 0 && end.isEmpty()) {
        //  Can only move forward
        if (isWhite() && start.getX() < end.getX())
           return true;
        else if (!isWhite() && start.getX() > end.getX())
           return true;
        else
           return false;
      // If it's the pawns first move, moving two spaces is legal
      } else if (firstMove && y == 2 && x == 0) {
        if (end.getX() - 1 == 1 && !end.isEmpty()) {
          return false;
        } else {
          return true;
        }
      } else if (x == 1 && !end.isEmpty()) {
        if (isWhite() && y == 1 && start.getX() < end.getX())
          return true;
        else if (!isWhite() && y == 1 && start.getX() > end.getX())
          return true;
        else
          return false;
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

