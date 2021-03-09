package com.sherwoodhs;

public class Spot {

    private Piece piece;
    private boolean empty;
    private int x;
    private int y;

    public Spot(int x, int y, Piece piece)
    {
        this.setPiece(piece);
        this.setX(x);
        this.setY(y);
        this.empty = false;
    }

    public Spot(int x, int y, boolean empty)
    {
        this.setX(x);
        this.setY(y);
        this.empty = empty;
    }

    public Piece getPiece()
    {
        return this.piece;
    }

    public void setPiece(Piece p)
    {
        this.piece = p;
    }

    public void setEmpty(boolean empty){

      this.empty = empty;
      
    }

    public boolean isEmpty(){

      return this.empty;

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
        try {
            return piece.toString();
        } catch(Exception e) {
            return " ☐☐";
        }
    }
}
