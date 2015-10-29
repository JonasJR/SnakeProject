package com.company;

import java.util.ArrayList;

/**
 * Created by Jonas on 2015-10-29.
 */
public class Snake {
    private int posX, posY;
    private ArrayList<Integer[][]> prevPos;

    public Snake(){
        posX = 0;
        posY = 0;
        prevPos = new ArrayList<>();
    }
}
