package com.sherwoodhs;

public class Queen extends Piece{

    // Andrew Hiser and Hayden deBoer
    // Andrew have you been working on this at all?

    public Queen(boolean white) {
        super(white);
    }

    public boolean canMove(Board board, Spot start, Spot end) {

        int deltaX = end.getX() - start.getX();
        int deltaY = end.getY() - start.getY();

        if(end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }

        // Checking if moved diagonally
        if(Math.abs(deltaX) == Math.abs(deltaY)) {
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
        }
        // Checking if moved along only one axis
        //else if(deltaX == 0 ^ deltaY == 0) {

        //}

        return false;
    }

    public String toString() {
        if (this.isWhite()) {
            return " wQ ";
        } else {
            return " bQ ";
        }
    }

}
