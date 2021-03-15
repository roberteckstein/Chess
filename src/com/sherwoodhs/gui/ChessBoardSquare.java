package com.sherwoodhs.gui;

import com.sherwoodhs.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class ChessBoardSquare extends JPanel {

    private ChessBoardGraphic graphicLabel;
    private Spot spot;
    private ChessBoard chessBoard;
    private boolean white;
    private Color normalBackground;

    public ChessBoardSquare(ChessBoard chessBoard, Spot spot) {

        super();

        this.spot = spot;
        this.chessBoard = chessBoard;

        if ((spot.getX()+spot.getY()) % 2 == 0) {
            white = false;
            normalBackground = Color.darkGray;
        } else {
            white = true;
            normalBackground = Color.lightGray;
        }

        setBackground(normalBackground);
        setBorder(new EtchedBorder());

        graphicLabel = new ChessBoardGraphic(this, spot.getPiece());
        add(graphicLabel);

    }

    public void setNormalBackground() {
        super.setBackground(normalBackground);
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public Spot getSpot() {
        return spot;
    }

    public void reset() {
        graphicLabel.setPiece(spot.getPiece());
    }

}
