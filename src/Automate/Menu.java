package Automate;

import java.util.Scanner;

/**
 * Menu du programme qui permet de selectionner les actions, on enverra a chaque fois un clone de l'automate initiale
 */
public class Menu {
    public static void main(String[] args) {
        int menu0 = 1;
        int menu1 = 1;
        String choix;
        Scanner saisie = new Scanner(System.in);// Objet saisisseur

        Automate automate = Readwrite.readAutomateFile("src/Automate/test_automate.txt");
        assert automate != null;

        while (menu0 == 1) {

            while (menu1 == 1) {

                System.out.println();
                automate.afficherAutomate();
                
                System.out.println(
                "\n------------------------Voici le menu : que voulez vous faire ?-----------------------------------\n\n" + 
                "---------------------------> d : determinisation\n" +
                "---------------------------> s : standardisation\n" +
                "---------------------------> e : exit\n");

                System.out.print("Tapez votre choix : ");
                choix = saisie.next();

                
                if (choix.equals("e")){
                    System.out.print("\033[H\033[2J");// Effacement de la console
                    System.out.flush();// Pour une meilleur visibilité
                    menu1 = 0;
                }


                if (choix.equals("d")) {//Choix determinisation
                    Automate deter = (Automate) automate.clone();
                    System.out.print("\033[H\033[2J");//Effacement de la console
                    System.out.flush();//Pour une meilleur visibilité
                    deter.determinisation();
                    deter.setLabel("Determinisé");
                    deter.afficherAutomate();
                }

                if (choix.equals("s")) {// Choix determinisation
                    Automate standard = (Automate) automate.clone();
                    System.out.print("\033[H\033[2J");// Effacement de la console
                    System.out.flush();// Pour une meilleur visibilité
                    standard.standardisation("i");
                    standard.setLabel("Standardisé");
                    standard.afficherAutomate();
                }

            }
            
            System.out.println("Voulez vous quittez ? tapez o");
            System.out.print("Tapez votre choix : ");
            choix = saisie.next();

            if (choix.equals("o")){
                System.out.print("\033[H\033[2J");// Effacement de la console
                System.out.flush();// Pour une meilleur visibilité
                menu0 = 0;
                saisie.close();
                
            }
        }
    }
}
