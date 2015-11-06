package com.company;

/**
 * Created by Xeronic on 15-10-30.
 */
public class Test {

    public Test(Map map) {
        int[][] board = makeBoard(map.getFields());
        board[0][0] = 2;
        int[][] tempBoard = board.clone();
        printBoard(tempBoard);
    }

    private int[][] makeBoard(Field[][] fields) {
        int[][] board = new int[fields.length][fields.length];

        for (int row = 0; row < fields.length; row++) {
            for (int col = 0; col < fields.length; col++) {
                board[row][col] = fields[row][col].getK();
            }
        }
        return board;
    }


    private void printBoard(int[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
