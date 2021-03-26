package com.sherwoodhs;

public class King extends Piece {

    private boolean castlingDone = false;

    public King(boolean white)
    {
        super(white);
    }

    public boolean isCastlingDone()
    {
        return this.castlingDone;
    }

    public void setCastlingDone(boolean castlingDone)
    {
        this.castlingDone = castlingDone;
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end)
    {
        // cannot move to same spot
        if (start == end) {
            return false;
        }

        // we can't move the piece to a Spot that
        // has a piece of the same color
        if ((!end.isEmpty()) && (end.getPiece().isWhite() == this.isWhite())) {
            return false;
        }

        //  King can only move
        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());

        if ((x == 0 || x == 1) && (y == 0 || y == 1) && !Game.getInstance().isCheck(board, end)) {
            // check if this move will not result in the king
            // being attacked if so return true
            Game.getInstance().setKingPosition(end);
            return true;
        } else {
            return false;
        }

       // return this.isValidCastling(board, start, end);
    }

    @Override
    public boolean performMove(Board board, Spot start, Spot end) {
        //  No special items to be checked for after a move
        return true;
    }

    private boolean isValidCastling(Board board,
                                    Spot start, Spot end)
    {

        if (this.isCastlingDone()) {
            return false;
        }

        // Logic for returning true or false

        return true;
    }

    public boolean isCastlingMove(Spot start, Spot end)
    {
        // check if the starting and
        // ending position are correct

        return true;
    }

    public String toString() {
        if (this.isWhite()) {
            return " wK ";
        } else {
            return " bK ";
        }
    }
}
