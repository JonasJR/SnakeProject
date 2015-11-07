package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Created by jerrypedersen on 15-10-29.
 */
public class Visualize {

    private static final int SPEED_JUMP = 50;

    private PaintWindow window;
    private Map map;
    private int boxWidth;
    private ArrayList<Pos> snake;
    private int currentLongestSnakeLength;
    private int[][] currentLongestSnake;
    private long speed;

    public Visualize(Map map) {
        this.map = map;
        this.snake = new ArrayList<>();
        this.window = new PaintWindow();
        this.speed = 20;

        this.currentLongestSnakeLength = 0;

        moveSnake(new Pos(0, 0, 1), makeBoard(map.getFields()), 0);

        System.out.println("Longest snake");
        printBoard(this.currentLongestSnake);
    }

    private int[][] makeBoard(Field[][] fields) {
        int[][] board = new int[fields.length][fields.length];
        this.currentLongestSnake = new int[fields.length][fields.length];

        for (int row = 0; row < fields.length; row++) {
            for (int col = 0; col < fields.length; col++) {
                board[row][col] = fields[row][col].getK();
            }
        }
        return board;
    }

    public void drawWindow(int[][] board) {
        int biggestSide = Math.max(map.getColumns(), map.getRows());
        boxWidth = window.getWidth() / biggestSide;

        drawBoard(board);
    }

    private void drawBoard(int[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                if (board[row][col] == -1) {
                    window.fillRect(col * boxWidth, row * boxWidth, boxWidth, boxWidth, Color.RED);
                } else if (board[row][col] > 0) {
                    window.fillRect(col * boxWidth, row * boxWidth, boxWidth, boxWidth, Color.GREEN);
                }
                window.drawRect(col * boxWidth, row * boxWidth, boxWidth, boxWidth, Color.BLACK, 10);
            }
        }
    }

    public void moveSnake(Pos snakeHead, int[][] board, int snakeLength) {
        int[][] tempBoard = board.clone();
        board[snakeHead.x][snakeHead.y] = snakeHead.pos;
        drawWindow(board);
        try {
            Thread.sleep(this.speed);
        } catch (Exception e) {
        }

        snakeLength += 1;
        snakeHead.pos += 1;

        Pos pos = new Pos(snakeHead.x + 1, snakeHead.y, snakeHead.pos);

        if (validMove(pos, board))
            moveSnake(pos, board.clone(), snakeLength);

        board = tempBoard;
        pos = new Pos(snakeHead.x, snakeHead.y + 1, snakeHead.pos);
        if (validMove(pos, board))
            moveSnake(pos, board.clone(), snakeLength);

        board = tempBoard;
        pos = new Pos(snakeHead.x - 1, snakeHead.y, snakeHead.pos);
        if (validMove(pos, board))
            moveSnake(pos, board.clone(), snakeLength);

        board = tempBoard;

        pos = new Pos(snakeHead.x, snakeHead.y - 1, snakeHead.pos);
        if (validMove(pos, board))
            moveSnake(pos, board.clone(), snakeLength);

        board = tempBoard;

        if (snakeLength > currentLongestSnakeLength) {
            currentLongestSnakeLength = snakeLength;
            currentLongestSnake = board.clone();
            System.out.println("Snake length: " + currentLongestSnakeLength);
            printBoard(board);

        }

        board[snakeHead.x][snakeHead.y] = 0;
        window.clear();
    }

    private void printBoard(int[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                System.out.print(String.format("%2d", board[row][col]) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private boolean validMove(Pos pos, int[][] board) {
        if (pos.x >= 0 && pos.x < board.length &&
                pos.y >= 0 && pos.y < board.length &&
                board[pos.x][pos.y] == 0) return true;
        return false;
    }

    private class Pos {
        public int x, y, pos;

        public Pos(int x, int y, int pos) {
            this.x = x;
            this.y = y;
            this.pos = pos;
        }
    }
}