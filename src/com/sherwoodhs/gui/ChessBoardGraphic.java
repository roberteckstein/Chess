package com.sherwoodhs.gui;

import com.sherwoodhs.*;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class ChessBoardGraphic extends JLabel {

    public DataFlavor CHESS_BOARD_GRAPHIC_DATA_FLAVOR =
            new DataFlavor(ChessBoardGraphic.class, "ChessBoardGraphic");
    private ChessPieceTransferHandler transferHandler;

    private JLabel label;
    private ChessBoardSquare square;
    private Piece piece;


    public ChessBoardGraphic(ChessBoardSquare square, Piece piece) {

        this.square = square;

        addMouseListener(new GraphicDragAdapter());
        setDropTarget(new GraphicDropTarget(square.getSpot()));

        transferHandler = new ChessPieceTransferHandler(square);
        setTransferHandler(transferHandler);

        setPiece(piece);

    }

    public Piece getPiece() {
        return this.piece;
    }

    public void setPiece(Piece piece) {

        this.piece = piece;

        ImageIcon graphic = getGraphicImageIcon(piece);
        setIcon(graphic);

        if (transferHandler != null) {
            transferHandler.setDragImage(graphic.getImage());
            transferHandler.setDragImageOffset(new Point(-50, -50));
        }

    }

    private ImageIcon getGraphicImageIcon(Piece piece) {

        String filename = "Blank.png";

        if (piece instanceof King) {
            if (piece.isWhite()) {
                filename = "WhiteKing.png";
            } else {
                filename = "BlackKing.png";
            }
        } else if (piece instanceof Queen) {
            if (piece.isWhite()) {
                filename = "WhiteQueen.png";
            } else {
                filename = "BlackQueen.png";
            }
        } else if (piece instanceof Bishop) {
            if (piece.isWhite()) {
                filename = "WhiteBishop.png";
            } else {
                filename = "BlackBishop.png";
            }
        } else if (piece instanceof Knight) {
            if (piece.isWhite()) {
                filename = "WhiteKnight.png";
            } else {
                filename = "BlackKnight.png";
            }
        } else if (piece instanceof Rook) {
            if (piece.isWhite()) {
                filename = "WhiteRook.png";
            } else {
                filename = "BlackRook.png";
            }
        } else if (piece instanceof Pawn) {
            if (piece.isWhite()) {
                filename = "WhitePawn.png";
            } else {
                filename = "BlackPawn.png";
            }
        }

        Toolkit tk = this.getToolkit();
        URL iconUrl = this.getClass().getResource("images/"+filename);
        return new ImageIcon(tk.getImage(iconUrl));
    }



    private class GraphicDragAdapter extends MouseAdapter {

        public void mousePressed(MouseEvent evt) {

            ChessBoardGraphic graphic = (ChessBoardGraphic) evt.getSource();

            //  If the square is not blank, and the game is not ended, and the
            //  current player color is the same as the color of the piece being
            //  dragged, then create the TransferHandler object for the drag-and-drop,
            //  signal that it can be dragged, and color the possible squares that
            //  the user can drop the icon onto.

            if ((graphic.getPiece() != null) && (!Game.getInstance().isEnd()) &&
                (graphic.getPiece().isWhite() == Game.getInstance().getCurrentPlayer().isWhiteSide())) {

                    TransferHandler handler = graphic.getTransferHandler();
                    handler.exportAsDrag(graphic, evt, TransferHandler.MOVE);
                    square.getChessBoard().redrawPossibleMoves(square.getSpot());

            }
        }

    }



    public class GraphicDropTarget extends DropTarget {

        private Spot targetSpot;

        public GraphicDropTarget(Spot targetSpot) {
            super();
            this.targetSpot = targetSpot;
        }

        public synchronized void drop(DropTargetDropEvent evt) {

            try {

                evt.acceptDrop(DnDConstants.ACTION_MOVE);

                Transferable t = evt.getTransferable();
                if (t.isDataFlavorSupported(CHESS_BOARD_GRAPHIC_DATA_FLAVOR)) {

                    Spot sourceSpot = (Spot) t.getTransferData(CHESS_BOARD_GRAPHIC_DATA_FLAVOR);
                    if (sourceSpot != null) {

                        ChessBoard chessBoard = square.getChessBoard();
                        Board board = Game.getInstance().getBoard();

                        System.out.println("Piece " + sourceSpot.getPiece() + " moves from " +
                                sourceSpot.getX() + "," + sourceSpot.getY() + " to " +
                                targetSpot.getX() + "," + targetSpot.getY());

                        //  What was sent was a serialized copy of the Spot object, not the original Spot object.
                        //  We need to obtain the actual Spot object to make a change to the chess board.

                        ChessBoardSquare square = chessBoard.getSquare(sourceSpot.getX(), sourceSpot.getY());

                        //  If this is a legal move, then process it and signal the drop is complete.

                        if (sourceSpot.getPiece().canMove(board, square.getSpot(), targetSpot)) {
                            Game.getInstance().playerMove(Game.getInstance().getCurrentPlayer(),
                                    sourceSpot.getX(), sourceSpot.getY(),
                                    targetSpot.getX(), targetSpot.getY());
                            evt.dropComplete(true);
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }


    class ChessPieceTransferHandler extends TransferHandler {

        private final DataFlavor flavors[] = { CHESS_BOARD_GRAPHIC_DATA_FLAVOR };
        private ChessBoardSquare square;

        public ChessPieceTransferHandler(ChessBoardSquare square) {
            this.square = square;
        }

        public int getSourceActions(JComponent c) {
            return TransferHandler.MOVE;
        }

        public Transferable createTransferable(JComponent comp) {

            if (comp instanceof ChessBoardGraphic) {
                return new ChessPieceTransferable(square.getSpot());
            }

            return null;
        }

    }


    class ChessPieceTransferable implements Transferable {

        private Spot chessSpot;
        private final DataFlavor flavors[] = { CHESS_BOARD_GRAPHIC_DATA_FLAVOR };

        public ChessPieceTransferable(Spot spot) {
            super();
            this.chessSpot = spot;
        }

        public Object getTransferData(DataFlavor flavor) {
            if (isDataFlavorSupported(flavor)) {
                return chessSpot;
            }
            return null;
        }

        public DataFlavor[] getTransferDataFlavors() {
            return flavors;
        }

        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return flavors[0].equals(flavor);
        }
    }
}
