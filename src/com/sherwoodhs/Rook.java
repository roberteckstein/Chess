package com.sherwoodhs;

public class Rook extends Piece {
    private Spot spot;

    public Rook(Spot spot, boolean white) {
        super(white);
        this.spot = spot;
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        if (end.getPiece().isWhite() == this.isWhite()) {
            return false;
        } else {
            if (end.getX() != start.getX() && end.getY() != start.getY()) {
                return false;
            } else {
                //moving right
                if(start.getX() < end.getX()) {
                    for (int i = start.getX() + 1; i <= end.getX() - 1; i++) {
                        if(board.getBox(i,start.getY()).getPiece() != null) {
                            return false;
                        }
                    }
                }
                //moving left
                else if(start.getX() > end.getX()) {
                    for (int i = start.getX() - 1; i >= end.getX() + 1; i--) {
                        if(board.getBox(i,start.getY()).getPiece() != null) {
                            return false;
                        }
                    }
                }
                //moving down
                else if(start.getY() < end.getY()) {
                    for (int i = start.getY() + 1; i <= end.getY() - 1; i++) {
                        if(board.getBox(start.getX(), i).getPiece() != null) {
                            return false;
                        }
                    }
                }
                //moving up
                else if(start.getY() > end.getY()) {
                    for (int i = start.getY() - 1; i >= end.getY() + 1; i--) {
                        if(board.getBox(start.getX(), i).getPiece() != null) {
                            return false;
                        }
                    }
                }
                //if end has an enemy piece, capture it
                if((end.getPiece().isWhite() && !start.getPiece().isWhite()) || (!end.getPiece().isWhite() && start.getPiece().isWhite())) {
                    end.getPiece().setKilled(true);
                }
                return true;
            }
        }
    }
}
