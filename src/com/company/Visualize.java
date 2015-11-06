package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Created by jerrypedersen on 15-10-29.
 */
public class Visualize implements KeyListener {

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
        this.window.addKeyListener(this);
        this.speed = 500;

        this.currentLongestSnakeLength = 0;

        moveSnake(new Pos(0, 0), makeBoard(map.getFields()), 1);
        drawWindow(currentLongestSnake);
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

//        drawSnake();
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

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            this.speed += SPEED_JUMP;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (this.speed > SPEED_JUMP) this.speed -= SPEED_JUMP;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

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
        drawWindow(board);
        try {
            Thread.sleep(this.speed);
        } catch (Exception e) {
        }

        snakeLength += 1;

        Pos pos = new Pos(snakeHead.x + 1, snakeHead.y);

        if (validMove(pos, board))
            moveSnake(pos, board.clone(), snakeLength);

        board = tempBoard;
        pos = new Pos(snakeHead.x, snakeHead.y + 1);
        if (validMove(pos, board))
            moveSnake(pos, board.clone(), snakeLength);

        board = tempBoard;
        pos = new Pos(snakeHead.x - 1, snakeHead.y);
        if (validMove(pos, board))
            moveSnake(pos, board.clone(), snakeLength);

        board = tempBoard;

        pos = new Pos(snakeHead.x, snakeHead.y - 1);
        if (validMove(pos, board))
            moveSnake(pos, board.clone(), snakeLength);

        board = tempBoard;

        if (snakeLength > currentLongestSnakeLength) {
            currentLongestSnakeLength = snakeLength;
            currentLongestSnake = board;
            System.out.println("Snake length: " + currentLongestSnakeLength);
            printBoard(board);

        }

        board[snakeHead.x][snakeHead.y] = 0;
        window.clear();
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

    private boolean validMove(Pos pos, int[][] board) {
        if (pos.x >= 0 && pos.x < board.length &&
                pos.y >= 0 && pos.y < board.length &&
                board[pos.x][pos.y] == 0) return true;
        return false;
    }
}