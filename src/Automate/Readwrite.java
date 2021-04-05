package Automate;

import java.io.*;

public class Readwrite {

    public static void main(String[] args) {

        try{

            FileInputStream fichier = new FileInputStream("src/Automate/test_automate.txt");

            Automate test = new Automate(fichier);
            test.afficherAutomate();
            

        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }

        


       
    }
}