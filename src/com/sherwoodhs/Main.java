package com.sherwoodhs;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        HumanPlayer player1 = new HumanPlayer(true);
        HumanPlayer player2 = new HumanPlayer(false);

        Game game = new Game();
        game.initialize(player1, player2);

        int startX1, startY1, startX2, startY2, endX1, endY1, endX2, endY2;

        Scanner input = new Scanner(System.in);

        while (true) {

            //  Ask player 1 for a move
            System.out.println("Enter starting square's X position");

             startX1 = input.nextInt();

            System.out.println("Enter starting square's Y position");

             startY1 = input.nextInt();

            System.out.println("Enter ending square's X position");

             endX1 = input.nextInt();

            System.out.println("Enter ending square's Y position");

             endY1 = input.nextInt();

           

          //  game.playerMove(player1, startX1, startY1, endX1, endY1);
            if (game.isEnd()) {
                break;
            }

            //  Ask player 2 for a move

            System.out.println("Enter starting square's X position");

             startX2 = input.nextInt();

            System.out.println("Enter starting square's Y position");

             startY2 = input.nextInt();

            System.out.println("Enter ending square's X position");

             endX2 = input.nextInt();

            System.out.println("Enter ending square's Y position");

             endY2 = input.nextInt();

         //   game.playerMove(player2, startX2, startY2, endX2, endY2);
            if (game.isEnd()) {
                break;
            }

        }

        //  Game is over. Check which king is not dead to determine winner.

    }
}
