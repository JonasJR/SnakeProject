package com.company;

/**
 * Created by Jonas on 2015-10-29.
 */
public class Map {

    private Field[][] fields;
    private int numberOfRed, numberOfFields, rows, columns;

    public Map(Field[][] fields, int rows, int columns, int numberOfRed){
        this.fields = fields;
        this.rows = rows;
        this.columns = columns;
        this.numberOfRed = numberOfRed;
        this.numberOfFields = rows * columns;
    }

    public int getNumberOfRed() {
        return numberOfRed;
    }

    public void setNumberOfRed(int numberOfRed) {
        this.numberOfRed = numberOfRed;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public Field[][] getFields() {
        return fields;
    }

    public void setFields(Field[][] fields) {
        this.fields = fields;
    }

    public int getNumberOfFields() {
        return numberOfFields;
    }
}