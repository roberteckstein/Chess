package com.sherwoodhs;

public class Queen extends Piece {

  // Andrew Hiser and Hayden deBoer

  public Queen(boolean white) {
    super(white);
  }

  public boolean canMove(Board board, Spot start, Spot end) {

    int deltaX = end.getX() - start.getX();
    int deltaY = end.getY() - start.getY();

    if ((!end.isEmpty()) && (end.getPiece().isWhite() == this.isWhite())) {
      return false;
    }

    // Checking if moved diagonally
    if (Math.abs(deltaX) == Math.abs(deltaY)) {
      // Moving up right
      if (deltaX > 0 && deltaY > 0) {
        for (int i = 0; i < Math.abs(deltaX); i++) {
          if (!board.getBox(start.getX() + i, start.getY() + i).isEmpty()) {
            return false;
          }
          return true;
        }
      }
      // Moving up left
      else if (deltaX < 0 && deltaY > 0) {
        for (int i = 0; i < Math.abs(deltaX); i++) {
          if (!board.getBox(start.getX() - i, start.getY() + i).isEmpty()) {
            return false;
          }
          return true;
        }

      return true;
      }
      // Moving down right
      else if (deltaX > 0 && deltaY < 0) {
        for (int i = 0; i < Math.abs(deltaX); i++) {
          if (!board.getBox(start.getX() + i, start.getY() - i).isEmpty()) {
            return false;
          }
          return true;
        }
        return true;
      }
      // Moving down left
      else if (deltaX < 0 && deltaY < 0) {
        for (int i = 0; i < Math.abs(deltaX); i++) {
          if (!board.getBox(start.getX() - i, start.getY() - i).isEmpty()) {
            return false;
          }
          
        }
        return true;
      } else {
        return false;
      }
    }
    // Checking if moved along only one axis
    else if ((deltaX == 0 && deltaY != 0) || (deltaX != 0 && deltaY == 0)) {

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

    return false;
  }

  public boolean performMove(Board board, Spot start, Spot end) {
    //  No special items to be checked for after a move
    return true;
  }

  public String toString() {
    if (this.isWhite()) {
      return " wQ ";
    } else {
      return " bQ ";
    }
  }

}
