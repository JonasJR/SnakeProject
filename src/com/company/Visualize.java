package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by jerrypedersen on 15-10-29.
 */
public class Visualize {

    private PaintWindow window;
    private Map map;
    private int boxWidth;
    private ArrayList<Pos> snake;
    private int currentLongestSnakeLength;
    private Snake snaken;
    private int[][] boarden;

    public Visualize(Map map) {
        this.map = map;
        this.snake = new ArrayList<>();
        window = new PaintWindow();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        currentLongestSnakeLength = 0;
        moveSnake(new Pos(0, 0), makeBoard(map.getFields()), 1);
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

    public void drawWindow(int[][] board) {
        int biggestSide = Math.max(map.getColumns(), map.getRows());
        boxWidth = window.getWidth() / biggestSide;

        drawSnake();
        drawBoard(board);
    }

    private void drawBoard(int[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                if (board[row][col] == 1) {
                    window.fillRect(col * boxWidth, row * boxWidth, boxWidth, boxWidth, Color.RED);
                } else if (board[row][col] == 2) {
                    window.fillRect(col * boxWidth, row * boxWidth, boxWidth, boxWidth, Color.GREEN);
                }
                window.drawRect(col * boxWidth, row * boxWidth, boxWidth, boxWidth, Color.BLACK, 10);
            }
        }
    }

    private void drawSnake() {
        for (Pos p : snake) {
            window.fillRect(p.x * boxWidth, p.y * boxWidth, boxWidth, boxWidth, Color.GREEN);
        }
    }

    private void drawMap() {
        for (int row = 0; row < map.getRows(); row++) {
            for (int col = 0; col < map.getColumns(); col++) {
                if (map.getFields()[row][col].getK() == 1) {
                    window.fillRect(col * boxWidth, row * boxWidth, boxWidth, boxWidth, Color.RED);
                }
                window.drawRect(col * boxWidth, row * boxWidth, boxWidth, boxWidth, Color.BLACK, 10);
            }
        }
    }

    public void printMap() {
        for (int row = 0; row < map.getRows(); row++) {
            for (int col = 0; col < map.getColumns(); col++) {
                System.out.print(map.getFields()[row][col].getK() + " ");
            }
            System.out.println();
        }
    }

    public void printLongestSnake() {
        int biggestSide = Math.max(map.getColumns(), map.getRows());
        boxWidth = window.getWidth() / biggestSide;
        for (Integer[] s : snaken.getPrevPos()) {
            window.fillRect(s[0] * boxWidth, s[1] * boxWidth, boxWidth, boxWidth, Color.GREEN);
        }
        drawBoard(boarden);
    }

    private class Pos {
        public int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public void moveSnake(Pos snakeHead, int[][] board, int snakeLength) {
        int[][] tempBoard = board.clone();
        board[snakeHead.x][snakeHead.y] = 2;
/*        drawWindow(board);
        try {
            Thread.sleep(20);
        } catch (Exception e) {
        }*/

        Pos pos = new Pos(snakeHead.x + 1, snakeHead.y);

        if (validMove(pos, board))
            moveSnake(pos, board.clone(), snakeLength++);

        pos = new Pos(snakeHead.x, snakeHead.y + 1);
        if (validMove(pos, board))
            moveSnake(pos, board.clone(), snakeLength++);

        board = tempBoard;
        pos = new Pos(snakeHead.x - 1, snakeHead.y);
        if (validMove(pos, board))
            moveSnake(pos, board.clone(), snakeLength++);

        board = tempBoard;

        pos = new Pos(snakeHead.x, snakeHead.y - 1);
        if (validMove(pos, board))
            moveSnake(pos, board.clone(), snakeLength++);

        if (snakeLength > currentLongestSnakeLength) {
            currentLongestSnakeLength = snakeLength;
            System.out.println("Snake length: " + snakeLength);
            printBoard(board);
        }

        board[snakeHead.x][snakeHead.y] = 0;
//        window.clear();
    }

    private void printBoard(int[][] board) {
        snaken = new Snake();
        boarden = board;
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                if(board[row][col] == 2){
                    snaken.setPrevPos(row, col);
                }
                System.out.print(board[row][col] + " ");
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
}