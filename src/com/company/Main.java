package com.company;

import javax.swing.*;
import java.io.File;

public class Main {

    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        File f = new File("C:/");
        File filePath;
        int checker;
        System.out.println("Lets user choose file...");
        chooser.setCurrentDirectory(f);
        checker = chooser.showOpenDialog(null);

        if(checker == JFileChooser.APPROVE_OPTION){
            filePath = chooser.getSelectedFile();
            System.out.println("Creates the file reader...");
            ReadFileAndGenerateMap readFileAndGenerateMap = new ReadFileAndGenerateMap(filePath.getAbsolutePath());
            System.out.println("Reads the file and generates the map...");
            Map map = readFileAndGenerateMap.readFile();
            System.out.println("Creates the snake path generator...");
            GenerateSnakePath generateSnakePath = new GenerateSnakePath(map);
            System.out.println("Generates the snake path...");
            generateSnakePath.generate();
        }else{
            System.out.println("User clicked cancel...");
        }
    }
}
