package com.sherwoodhs;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class Game {

    private static Game instance;

    private final Player[] players = new Player[2];
    private Player currentPlayer;
    public static final String CURRENT_PLAYER_PROPERTY = "CurrentPlayer";

    private GameStatus status;
    public static final String GAME_STATUS_PROPERTY = "GameStatus";

    private Spot kingPosition;
    
    private final Board board = new Board();
    private List<Move> movesPlayed;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public static Game getInstance() {

      if (instance == null) {
        instance = new Game();
      }

      return instance;

    }

    // just to test printing board
    private Game() {

      movesPlayed = new ArrayList<Move>();

    //  board.flipBoard();
    }

    public Board getBoard() {
      return board;
    }

    public Spot getKingPosition() {
        return kingPosition;
    }

    public Spot setKingPosition(Spot kingPos){
        kingPosition = kingPos;
        return kingPosition;
    }
    
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player p) {
        Player oldPlayer = currentPlayer;
        currentPlayer = p;
        pcs.firePropertyChange(Game.CURRENT_PLAYER_PROPERTY, oldPlayer, currentPlayer);
    }

    public GameStatus getStatus() {
        return this.status;
    }

    public void setStatus(GameStatus status) {

        GameStatus oldStatus = this.status;
        this.status = status;
        pcs.firePropertyChange(Game.GAME_STATUS_PROPERTY, oldStatus, this.status);

    }

    public void initialize(Player p1, Player p2) {

      players[0] = p1;
      players[1] = p2;

      board.resetBoard();
      movesPlayed.clear();
      Game.getInstance().setKingPosition(new Spot(0,3));

      setStatus(GameStatus.ACTIVE);

    }

    public boolean isEnd() {
      return this.getStatus() != GameStatus.ACTIVE;
    }

    public boolean isCheck(Board board, Spot king) {


        for (int x = 0; x < 8; x++)
            for (int y = 0; y < 8; y++)
                if ((!board.getBox(x,y).isEmpty()) && !board.getBox(x,y).getPiece().isWhite() && x != king.getX() && y != king.getY()) {
                    if (board.getBox(x, y).getPiece().canMove(board, board.getBox(x, y), king)) {
                        return true;
                    }
                }
        return false;
    }

    public boolean canPieceBlock(Board board, Spot start, Spot king, Piece piece) {
        for (int x = 0; x < 8; x++)
            for (int y = 0; y < 8; y++)
                if (piece.canMove(board, start, board.getBox(x,y)) && !isCheck(board, king)){
                    return true;
                }
        return false;
    }

    public boolean canBlockCheck(Board board, Spot end) {
        for (int x = 0; x < 8; x++)
            for (int y = 0; y < 8; y++)
                if (!board.getBox(x,y).getPiece().isWhite() && x != end.getX() && y != end.getY())
                    if (canPieceBlock(board, board.getBox(x,y), end, board.getBox(x,y).getPiece())){
                        return true;
                    }
        return false;
    }

    public boolean isCheckmate(Board board, Spot king) {
        int x = king.getX();
        int y = king.getY();

        if(isCheck(board, king)) {
            for (int i = -1; i < 2; i++)
                for (int k = -1; k < 2; k++)
                    if (king.getPiece().canMove(board, king, board.getBox(Math.abs(x + i), Math.abs(y + k)))){
                        return false;
                    } else if (canBlockCheck(board, king)) {
                        return false;
                    }
        }

        Game.getInstance().setStatus(GameStatus.WHITE_WIN);

        return true;
    }

    public boolean playerMove(Player player, int startX, int startY, int endX, int endY) {
      Spot startBox = board.getBox(startX, startY);
      Spot endBox = board.getBox(endX, endY);
      Move move = new Move(player, startBox, endBox);
      return this.makeMove(move, player);
    }

    private boolean makeMove(Move move, Player player) {

      Piece sourcePiece = move.getStart().getPiece();
      if (sourcePiece == null) {
        return false;
      }

      // valid player
      if (player != currentPlayer) {
        return false;
      }

      // didn't move their own piece?
      if (sourcePiece.isWhite() != player.isWhiteSide()) {
        return false;
      }

      // didn't move the piece to a new square?
      if ((move.getStart().getX() == move.getEnd().getX()) &&
            (move.getStart().getY() == move.getEnd().getY())) {
        return false;
      }

      // in check?
      Board potentialBoard = new Board();
      // tried to override the clone function but couldn't get it to work
      if (sourcePiece.isWhite()) {
          for (int x = 0; x < 8; x++)
              for (int y = 0; y < 8; y++)
                  potentialBoard.boxes[x][y] = board.boxes[x][y];
          potentialBoard.boxes[move.getEnd().getX()][move.getEnd().getY()] = new Spot(move.getEnd().getX(), move.getEnd().getY(), move.getStart().getPiece());
          potentialBoard.boxes[move.getStart().getX()][move.getStart().getY()] = new Spot(move.getStart().getX(), move.getStart().getY(), null);
      }

        if((sourcePiece.isWhite()) && isCheck(potentialBoard, kingPosition)) {
            return false;
        }
        
      // valid move?
      if (!sourcePiece.canMove(board, move.getStart(), move.getEnd())) {
        return false;
      }

      // kill?
      Piece destPiece = move.getEnd().getPiece();
      if (destPiece != null) {
        System.out.println("Piece  " + destPiece + "  is captured");
        destPiece.setKilled(true);
        move.setPieceKilled(destPiece);
      }

      // castling?
      if (sourcePiece != null && sourcePiece instanceof King && ((King) sourcePiece).isCastlingMove(move.getStart(), move.getEnd())) {
        move.setCastlingMove(true);
      }
        
      // store the move
      movesPlayed.add(move);

      // checkmate?
        if (isCheck(board, kingPosition))
            if (isCheckmate(board, kingPosition) && !sourcePiece.isWhite()){
                System.out.println("Checkmate");
                return true;
                // returns true because game status ends game
            }



        // move piece from the stat box to end box
      move.getEnd().setPiece(move.getStart().getPiece());
      move.getStart().setPiece(null);
      pcs.firePropertyChange(Move.MOVE_PROPERTY, null, move);



        if (destPiece != null && destPiece instanceof King) {
        if (player.isWhiteSide()) {
          this.setStatus(GameStatus.WHITE_WIN);
          return true;
        } else {
          this.setStatus(GameStatus.BLACK_WIN);
          return true;
        }
      }

      // check for any special consequences of move
      if (!sourcePiece.performMove(board, move.getStart(), move.getEnd()))
        return false;



        // set the current turn to the other player
      if (this.currentPlayer == players[0]) {
        setCurrentPlayer(players[1]);
      } else {
        setCurrentPlayer(players[0]);
      }

      return true;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }
}
