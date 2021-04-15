package Automate;

import java.util.Scanner;

/**
 * Objet à tout faire
 */
public class Multifonctions {

    private String choix;
    private Scanner saisie = new Scanner(System.in);


    public String getChoix() {
        return this.choix;
    }

    /**
     * Permet une saisie utilisateur
     * @return retourne la saisie utilisateur
     */
    public void setChoix(){
        System.out.print("Tapez votre choix : ");
        this.choix = saisie.next();
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
    public void closesaisie(){
        saisie.close();
    }

}
