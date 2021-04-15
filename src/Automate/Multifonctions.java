package Automate;

import java.util.Scanner;

import javax.swing.text.StyledEditorKit.BoldAction;

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
     * Permet une saisie utilisateur simple
     */
    public void setChoix(){
        System.out.print("Tapez votre choix : ");
        this.choix = saisie.next();
    }

    /**
     * Permet une saisie utilisateur securisé
     * 
     */
    public void setChoix(String mot) {
        boolean lecture = true;

        while (lecture) {
            System.out.print("Tapez votre choix : ");
            this.choix = saisie.next();

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
    public void closesaisie(){
        saisie.close();
    }

}
