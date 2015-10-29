package com.company;

import javax.swing.*;
import java.io.File;

public class Main {

    private static String filePath;

    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        File f = new File("C:/");
        File filePath;
        int checker;

        chooser.setCurrentDirectory(f);
        checker = chooser.showOpenDialog(null);

        if(checker == JFileChooser.APPROVE_OPTION){
            filePath = chooser.getSelectedFile();
            ReadFileAndGenerateMap readFileAndGenerateMap = new ReadFileAndGenerateMap(filePath.getAbsolutePath());
            readFileAndGenerateMap.readFile();
        }else{
            System.out.println("User clicked cancel...");
        }
    }
}
