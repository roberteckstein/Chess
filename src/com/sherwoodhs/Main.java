package com.sherwoodhs;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        HumanPlayer player1 = new HumanPlayer(true);
        HumanPlayer player2 = new HumanPlayer(false);

        Game game = new Game();
        game.initialize(player1, player2);

        //  test

        int startX = 0, startY = 0, endX = 0, endY = 0;
        String start, end;
        final char[] files = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};

        Scanner input = new Scanner(System.in);

        while (true) {

            //  Ask player 1 for a move
            System.out.println("Player 1:");
            System.out.println("Enter starting square");

            start = input.next().toUpperCase();

            System.out.println("Enter ending square");

            end = input.next().toUpperCase();

            for (int i = 0; i <= 7; i++) {
                if (start.charAt(0) == files[i]) {
                    startX = i;
                }

                if (end.charAt(0) == files[i]) {
                    endX = i;
                }
            }

            startY = Character.getNumericValue(start.charAt(1));
            endY = Character.getNumericValue(end.charAt(1));

            game.playerMove(player1, startX, startY, endX, endY);


            if (game.isEnd()) {
                break;
            }

            //  Ask player 2 for a move
            System.out.println("Player 2:");
            System.out.println("Enter starting square");

            start = input.next();

            System.out.println("Enter ending square");

            end = input.next();

            for (int i = 0; i < 8; i++) {
                if (start.charAt(0) == files[i]) {
                    startX = i;
                }

                if (end.charAt(0) == files[i]) {
                    endX = i;
                }
            }

            startY = start.charAt(1);
            endY = start.charAt(1);

            game.playerMove(player2, startX, startY, endX, endY);
            if (game.isEnd()) {
                break;
            }

        }

        //  Game is over. Check which king is not dead to determine winner.

    }
}
