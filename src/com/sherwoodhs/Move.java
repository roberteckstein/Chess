package com.sherwoodhs;

public class Move {

    private Player player;
    private Spot start;
    private Spot end;
    private Piece pieceMoved;
    private Piece pieceKilled;
    private boolean castlingMove = false;

    public Move(Player player, Spot start, Spot end)
    {
        this.player = player;
        this.start = start;
        this.end = end;
        this.pieceMoved = start.getPiece();
    }

    public boolean isCastlingMove()
    {
        return this.castlingMove;
    }

    public void setCastlingMove(boolean castlingMove)
    {
        this.castlingMove = castlingMove;
    }

    public Spot getEnd() {

        return this.end;
    }

    public Spot getStart() {

        return this.start;
    }

    public void setPieceKilled(Piece destPiece) {
        destPiece.setKilled(true);
    }
}
