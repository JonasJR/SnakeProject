package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Jonas on 2015-10-29.
 */
public class ReadFileAndGenerateMap {

    private String path;
    Map map;
    Field[][] fields;

    public ReadFileAndGenerateMap(String path){
        this.path = path;
    }

    public Map readFile() {
        String currentLine;

        try(BufferedReader br = new BufferedReader((new FileReader(path)))) {
            int reds, rows, columns;
            currentLine = br.readLine();
            String[] seperaterd = currentLine.split(",");

            columns = Integer.parseInt(seperaterd[0]);
            rows = Integer.parseInt(seperaterd[1]);
            reds = Integer.parseInt(seperaterd[2]);

            fields = new Field[columns][rows];

            for(int i = 0; i < columns; i++){
                for (int j = 0; j < rows; j++){
                    fields[i][j] = new Field(0);
                    System.out.print(i + "" + j + " ");
                }
                System.out.println();
            }

            System.out.println();

            while ((currentLine = br.readLine()) != null) {
                String[] sep = currentLine.split(",");
                fields[Integer.parseInt(sep[0])][Integer.parseInt(sep[1])].setK(1);
                System.out.println(sep[0] + " " + sep[1]);
            }

            map = new Map(fields, rows, columns, reds);
            System.out.println("Map created!");
            return map;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}