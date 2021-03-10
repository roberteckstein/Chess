//Made by Andrew
package com.sherwoodhs;

public class Rook extends Piece {

    // Andrew Hiser

    public Rook(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        if (end.getPiece().isWhite() == this.isWhite()) {
            return false;
        } else {
            //checks if selection is on either the same x or y, easy optimization
            if (end.getX() != start.getX() && end.getY() != start.getY()) {
                return false;
            } else {
                //moving right
                if(start.getX() < end.getX()) {
                    for (int i = start.getX() + 1; i <= end.getX() - 1; i++) {
                        if(board.getBox(i,start.getY()).isEmpty()) {
                            return false;
                        }
                    }
                }
                //moving left
                else if(start.getX() > end.getX()) {
                    for (int i = start.getX() - 1; i >= end.getX() + 1; i--) {
                        if(board.getBox(i,start.getY()).isEmpty()) {
                            return false;
                        }
                    }
                }
                //moving down
                else if(start.getY() < end.getY()) {
                    for (int i = start.getY() + 1; i <= end.getY() - 1; i++) {
                        if(board.getBox(start.getX(), i).isEmpty()) {
                            return false;
                        }
                    }
                }
                //moving up
                else if(start.getY() > end.getY()) {
                    for (int i = start.getY() - 1; i >= end.getY() + 1; i--) {
                        if(board.getBox(start.getX(), i).isEmpty()) {
                            return false;
                        }
                    }
                }
                return true;
            }
        }
    }

    public String toString() {
        if (this.isWhite()) {
            return " wR ";
        } else {
            return " bR ";
        }
    }

}

