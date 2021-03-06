package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Main {

    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        File f = new File("./src/com/company/");
        File filePath;
        int checker;
        System.out.println("Lets user choose file...");
        chooser.setCurrentDirectory(f);
        checker = chooser.showOpenDialog(null);
        Map map = null;

        if(checker == JFileChooser.APPROVE_OPTION){
            filePath = chooser.getSelectedFile();
            System.out.println("Creates the file reader...");
            ReadFileAndGenerateMap readFileAndGenerateMap = new ReadFileAndGenerateMap(filePath.getAbsolutePath());
            System.out.println("Reads the file and generates the map...");
            map = readFileAndGenerateMap.readFile();
            System.out.println("Creates the snake path generator...");
            System.out.println("Generates the snake path...");
            Visualize visualize = new Visualize(map);
        }else{
            System.out.println("User clicked cancel...");
        }


    }
}
