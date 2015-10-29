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

    public Visualize(Map map) {
        this.map = map;
        this.snake = new ArrayList<>();
        window = new PaintWindow();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.setPreferredSize(new Dimension(1000, 1000));
        new Updater().start();
    }

    public void drawWindow() {
        int biggestSide = Math.max(map.getColumns(), map.getRows());
        boxWidth = window.getWidth() / biggestSide;

        drawSnake();
        drawMap();

    }

    private void drawSnake() {
        for (Pos p: snake) {
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

    private class Pos {
        public int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private class Updater extends Thread {

        int x = 0;
        int y = 0;

        public void run() {
            while (!Thread.interrupted()) {
                if (map != null) {
                    try {
                        snake.add(new Pos(x, y));
                        drawWindow();
                        y++;
                        Thread.sleep(500);
                    } catch (Exception e) {
                    }
                }
            }
        }
    }
}
