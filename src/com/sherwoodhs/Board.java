package com.sherwoodhs;

public class Board {
    Spot[][] boxes;

    public Board() {
        this.resetBoard();
    }

    public Spot getBox(int x, int y) {

        if (x < 0 || x > 7 || y < 0 || y > 7) {
            throw new IndexOutOfBoundsException();
        }

        return boxes[x][y];
    }

    public void resetBoard() {
        // initialize white back row
        boxes[0][0] = new Spot(0, 0, new Rook(true));
        boxes[0][1] = new Spot(0, 1, new Knight(true));
        boxes[0][2] = new Spot(0, 2, new Bishop(true));
        boxes[0][3] = new Spot(0, 3, new King(true));
        boxes[0][4] = new Spot(0, 4, new Queen(true));
        boxes[0][5] = new Spot(0, 5, new Bishop(true));
        boxes[0][6] = new Spot(0, 6, new Knight(true));
        boxes[0][7] = new Spot(0, 7, new Rook(true));

        // initialize white pawn row

        for (int i = 0; i < 8; i++) {
            boxes[1][i] = new Spot(1, i, new Pawn(true));
        }

        // initialize black back row
        boxes[7][0] = new Spot(7, 0, new Rook(false));
        boxes[7][1] = new Spot(7, 1, new Knight(false));
        boxes[7][2] = new Spot(7, 2, new Bishop(false));
        boxes[7][3] = new Spot(7, 3, new King(false));
        boxes[7][4] = new Spot(7, 4, new Queen(false));
        boxes[7][5] = new Spot(7, 5, new Bishop(false));
        boxes[7][6] = new Spot(7, 6, new Knight(false));
        boxes[7][7] = new Spot(7, 7, new Rook(false));

        // initialize white pawn row

        for (int i = 0; i < 8; i++) {
            boxes[6][i] = new Spot(6, i, new Pawn(false));
        }
    }

    public void flipBoard() {
        Spot[][] temp = new Spot[8][8];

        // make new identical array
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                temp[i][j] = boxes[i][j];
            }
        }

        // flip array
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                boxes[i][j] = temp[Math.abs(i-7)][Math.abs(j-7)];
            }
        }

    }
}
