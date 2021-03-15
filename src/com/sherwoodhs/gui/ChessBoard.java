package com.sherwoodhs.gui;

import com.sherwoodhs.*;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.border.StrokeBorder;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ChessBoard extends JFrame implements PropertyChangeListener {

    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;

    private ChessBoardSquare[][] squares;
    private JPanel gameBoardPanel;

    public ChessBoard() {

        super("Chess");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gameBoardPanel = new JPanel();
        gameBoardPanel.setLayout(new GridLayout(WIDTH,HEIGHT));

        squares = new ChessBoardSquare[WIDTH][HEIGHT];

        Board board = Game.getInstance().getBoard();
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                squares[i][j] = new ChessBoardSquare(this, board.getBox(i,j));
                gameBoardPanel.add(squares[i][j]);
            }
        }

        setContentPane(gameBoardPanel);
        pack();

        Game.getInstance().addPropertyChangeListener(this);

    }

    public ChessBoardSquare getSquare(int x, int y) {
        return squares[x][y];
    }

    public void redrawBoard() {

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                squares[i][j].setNormalBackground();
                squares[i][j].reset();
            }
        }

        gameBoardPanel.repaint();

    }

    public void redrawPossibleMoves(Spot start) {

        Board board = Game.getInstance().getBoard();

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                Spot end = board.getBox(i, j);
                if (start.getPiece().canMove(board, start, end)) {
                    squares[i][j].setBackground(Color.green);
                } else {
                    squares[i][j].setNormalBackground();
                }
            }
        }

        gameBoardPanel.repaint();

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        if (evt.getPropertyName().equals(Move.MOVE_PROPERTY)) {
            redrawBoard();
        } else if (evt.getPropertyName().equals(Game.CURRENT_PLAYER_PROPERTY)) {
            Player p = (Player)evt.getNewValue();
            gameBoardPanel.setBorder(new MatteBorder(5,5,5,5,
                    p.isWhiteSide() ? Color.white : Color.black));
        } else if (evt.getPropertyName().equals(Game.GAME_STATUS_PROPERTY)) {
            GameStatus status = (GameStatus)evt.getNewValue();
            if (status != GameStatus.ACTIVE) {
                gameBoardPanel.setBorder(new MatteBorder(5,5,5,5,Color.green));
            }
        }

    }
}
