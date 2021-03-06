//Made by Andrew
package com.sherwoodhs;

public class Rook extends Piece {

  // Andrew Hiser

  public Rook(boolean white) {
    super(white);
  }

  @Override
  public boolean canMove(Board board, Spot start, Spot end) {

    if ((end.getPiece() != null) && (end.getPiece().isWhite() == this.isWhite())) {
      return false;
    } else {

      int deltaX = end.getX() - start.getX();
      int deltaY = end.getY() - start.getY();

      // checks if selection is on either the same x or y, easy optimization
      if (end.getX() != start.getX() && end.getY() != start.getY()) {
        return false;
      } else {

        // moving right
        if (deltaX > 0) {
          for (int i = start.getX() + 1; i < end.getX(); i++) {
            if (!board.getBox(i, start.getY()).isEmpty()) {
              return false;
            }
          }

          return true;
        }
        // moving left
        else if (deltaX < 0) {
          for (int i = start.getX() - 1; i > end.getX(); i--) {
            if (!board.getBox(i, start.getY()).isEmpty()) {
              return false;
            }
          }

          return true;
        }
        // moving up
        else if (deltaY > 0) {
          for (int i = start.getY() + 1; i < end.getY(); i++) {
            if (!board.getBox(start.getX(), i).isEmpty()) {
              return false;
            }
          }

          return true;
        }
        // moving down
        else if (deltaY < 0) {
          for (int i = start.getY() - 1; i > end.getY(); i--) {
            if (!board.getBox(start.getX(), i).isEmpty()) {
              return false;
            }
          }
          return true;
        } else {
          return false;
        }
      }
    }
  }

  @Override
  public boolean performMove(Board board, Spot start, Spot end) {
    //  No special items to be checked for after a move
    return true;
  }

  public String toString() {
    if (this.isWhite()) {
      return " wR ";
    } else {
      return " bR ";
    }
  }

}
