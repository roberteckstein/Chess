package com.sherwoodhs;

import java.io.Serializable;

public abstract class Player implements Serializable {

    public boolean whiteSide;
    public boolean humanPlayer;

    public boolean isWhiteSide()
    {
        return this.whiteSide;
    }
    public boolean isHumanPlayer()
    {
        return this.humanPlayer;
    }

    public String toString() {
        return (isWhiteSide() ? "White" : "Black") + " Player (" +
                (isHumanPlayer() ? "Human" : "Computer") + ")";
    }
}
