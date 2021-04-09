package Automate;

import java.io.*;

public class Readwrite {

    public static Automate readAutomateFile(String fileName) {

        try{
            FileInputStream fichier = new FileInputStream(fileName);
            return new Automate(fichier, "x");
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }
}