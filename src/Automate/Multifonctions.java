package Automate;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Objet à tout faire
 */
public class Multifonctions {

    private String choix;
    private Scanner scanner = new Scanner(System.in);
    private File docFile = new File("index");
    private File[] index = docFile.listFiles();
    


    public String getChoix() {
        return this.choix;
    }

    /**
     * Permet une saisie utilisateur simple
     */
    public void setChoix(){
        System.out.print("Tapez votre choix : ");
        this.choix = scanner.next();
    }

    /**
     * Permet une saisie utilisateur securisé
     * 
     */
    public void setChoix(String mot) {
        boolean lecture = true;

        while (lecture) {
            System.out.print("Tapez votre choix : ");
            this.choix = scanner.next();

            for (int i = 0; i < mot.length(); i++) {
                if (getChoix().equals(mot.substring(i, i + 1))){
                    lecture = false;
                }
            }
        }
    }


    /**
     * Fonction qui clean la console
     */
    public void clearConsole(){
        System.out.print("\033[H\033[2J");// Effacement de la console
        System.out.flush();// Pour une meilleur visibilité
    }

    /**
     * Fermet la saisie utilisateur
     */
    public void closescanner(){
        scanner.close();
    }

    public String autoSelection(){
        String path = "";

        System.out.println(Arrays.toString(this.index));

        for (int i = 0; i < index.length; i++) {

            path = Arrays.toString(this.index);
            System.out.println("Path = : "+ path);
        }

        return path;
    }

}
