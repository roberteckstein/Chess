package com.sherwoodhs;

public class Main {

    public static void main(String[] args) {

        HumanPlayer player1 = new HumanPlayer(true);
        HumanPlayer player2 = new HumanPlayer(false);

        Game game = new Game();
        game.initialize(player1, player2);

        int startX1, startY1, startX2, startY2, endX1, endY1, endX2, endY2;

        while (true) {

            //  Ask player 1 for a move

          //  game.playerMove(player1, startX1, startY1, endX1, endY1);
            if (game.isEnd()) {
                break;
            }

            //  Ask player 2 for a move

         //   game.playerMove(player2, startX2, startY2, endX2, endY2);
            if (game.isEnd()) {
                break;
            }

        }

        //  Game is over. Check which king is not dead to determine winner.

    }
}
