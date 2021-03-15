package com.sherwoodhs;

import java.io.Serializable;

public class Spot implements Serializable {

    private Piece piece;
    private int x;
    private int y;

    public Spot(int x, int y, Piece piece)
    {
        this.setPiece(piece);
        this.setX(x);
        this.setY(y);
    }

    public Spot(int x, int y)
    {
        this.setX(x);
        this.setY(y);
    }

    public Piece getPiece()
    {
        return this.piece;
    }

    public void setPiece(Piece p)
    {
        this.piece = p;
    }

    public boolean isEmpty() {

      return (piece == null);

    }

    public int getX()
    {
        return this.x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return this.y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    @Override
    public String toString() {
      
        if (!this.isEmpty()){
            return piece.toString();
        } else {
            return " ☐☐ ";
        }
    }
}
