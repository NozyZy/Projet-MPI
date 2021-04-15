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
                System.out.println("Voici le menu : que voulez vous faire ?" + "\n" + 
                "d : determinisation"+"\n"+"e : exit");

                choix = saisie.next();

                
                if (choix.equals("e")) {
                   menu1 = 0;
                }


                if (choix.equals("d")) {//Choix determinisation
                    Automate deter = (Automate) automate.clone();
                    deter.afficherAutomate();
                    deter.determinisation();
                }

            }
            
            System.out.println("Voulez vous quittez ? tapez o");
            choix = saisie.next();
            if (choix.equals("o")){
                menu0 = 0;
                saisie.close();
            }
        }
    }
}
