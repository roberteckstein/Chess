package com.sherwoodhs;
import com.sherwoodhs.gui.ChessBoard;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        HumanPlayer player1 = new HumanPlayer(true);
        HumanPlayer player2 = new HumanPlayer(false);

        Game game = Game.getInstance();
        game.initialize(player1, player2);

        //  Create and display the GUI

        ChessBoard cb = new ChessBoard();
        cb.setVisible(true);

        game.setCurrentPlayer(player1);

        //  test

        int startX = 0, startY = 0, endX = 0, endY = 0;
        String start, end;
        final char[] files = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};

        Scanner input = new Scanner(System.in);

        while (true) {

            System.out.println(game.getBoard());

            //  Ask player 1 for a move
            System.out.println(player1);
            System.out.println("Enter starting square");
            start = input.next().toUpperCase();
            System.out.println("Enter ending square");
            end = input.next().toUpperCase();

            for (int i = 0; i <= 7; i++) {
                if (start.charAt(0) == files[i]) {
                    startY = i;
                }

                if (end.charAt(0) == files[i]) {
                    endY = i;
                }
            }

            startX = Character.getNumericValue(start.charAt(1)) - 1;
            endX = Character.getNumericValue(end.charAt(1)) - 1;

            System.out.println(player1 + " move from " + startX + "," + startY + " to " + endX + "," + endY);
            game.playerMove(player1, startX, startY, endX, endY);

            if (game.isEnd()) {
                break;
            }

            System.out.println(game.getBoard());

            //  Ask player 2 for a move
            System.out.println(player2);
            System.out.println("Enter starting square");

            start = input.next();

            System.out.println("Enter ending square");

            end = input.next();

            for (int i = 0; i < 8; i++) {
                if (start.charAt(0) == files[i]) {
                    startY = i;
                }

                if (end.charAt(0) == files[i]) {
                    endY = i;
                }
            }

            startX = Character.getNumericValue(start.charAt(1)) - 1;
            endX = Character.getNumericValue(end.charAt(1)) - 1;

            System.out.println(player2 + " move from " + startX + "," + startY + " to " + endX + "," + endY);
            game.playerMove(player2, startX, startY, endX, endY);
            if (game.isEnd()) {
                break;
            }

        }

        //  Game is over. Check which king is not dead to determine winner.

    }
}
