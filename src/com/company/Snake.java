package com.company;

import java.util.ArrayList;

/**
 * Created by Jonas on 2015-10-29.
 */
public class Snake {
    private int posX, posY, traveled;
    private ArrayList<Integer[]> prevPos;

    public Snake(){
        posX = 0;
        posY = 0;
        traveled = 0;
        prevPos = new ArrayList<>();
    }


    public ArrayList<Integer[]> getPrevPos() {
        return prevPos;
    }

    public void setPrevPos(int x, int y) {
        Integer[] newPrevPos = new Integer[2];
        newPrevPos[0] = x;
        newPrevPos[1] = y;
        this.prevPos.add(newPrevPos);
    }

    public boolean hasBeenThere(int x, int y){
        Integer[] newPrevPos = new Integer[2];
        newPrevPos[0] = x;
        newPrevPos[1] = y;
        if(prevPos.contains(newPrevPos)){
            return true;
        } else {
            return false;
        }
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getTraveled() {
        return traveled;
    }

    public void setTraveled(int traveled) {
        this.traveled = traveled;
    }
}
