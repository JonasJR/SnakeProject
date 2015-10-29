package com.company;

/**
 * Created by Jonas on 2015-10-29.
 */
public class Map {
    Field[][] map;
    int numberOfRed, rows, columns;

    public Map(Field[][] map, int rows, int columns, int numberOfRed){
        this.map = map;
        this.rows = rows;
        this.columns = columns;
        this.numberOfRed = numberOfRed;
    }
}
