package Automate;

import java.util.Objects;

/**
 * Menu du programme qui permet de selectionner les actions, on enverra a chaque fois un clone de l'automate initiale
 */
public class Menu {
    public static void main(String[] args) {
        int menu0 = 1;
        int menu1 = 1;

        Multifonctions jarvis = new Multifonctions();//admin du menu
        jarvis.clearConsole();
        jarvis.clearConsole();
        jarvis.autoSelection();
        jarvis.clearConsole();

        Automate automate = Readwrite.readAutomateFile(jarvis.getPathfinder());


        

        if (automate == null) {
            jarvis.error();
            return;
        }

        automate.afficherAutomate();
        
        while (menu0 == 1) {//Boucle du menu

            while (menu1 == 1) {
                jarvis.laCarte(Objects.requireNonNull(Readwrite.readAutomateFile(jarvis.getPathfinder())));// affichage du menu + choix
                
                if (jarvis.getChoix().equals("g")) {
                    //boolean mario = true;

                    //while (mario) {
                        jarvis.clearConsole();
                        jarvis.autoSelection();

                        Automate nintendo = Readwrite.readAutomateFile(jarvis.getPathfinder());// Nintendo car nintendo
                                                                                               // "switch" et switch ne
                                                                                               // peut pas etre un nom
                        assert nintendo != null;

                        jarvis.clearConsole();
                        nintendo.afficherAutomate();
                        //jarvis.laCarte();// affichage du menu + choix
                        //if (!jarvis.getChoix().equals("g")) {
                            //mario = false;
                        //} else {
                            //jarvis.clearConsole();
                        //}
                    
                }

                if (jarvis.getChoix().equals("e")){
                    jarvis.clearConsole();
                    menu1 = 0;
                }

                if (jarvis.getChoix().equals("d")) {//Choix determinisation
                    Automate deter = Readwrite.readAutomateFile(jarvis.getPathfinder());
                    jarvis.clearConsole();
                    if (deter == null) jarvis.error();
                    else {
                        deter.afficherAutomate();
                        deter.verifDeterministe(true);
                        deter.determinisation();
                        deter.setLabel("Determinisé");
                        deter.afficherAutomate();
                    }
                }

                if (jarvis.getChoix().equals("a")) {// Choix determinisation+complétion synchrone/asynchrone
                    Automate komba = Readwrite.readAutomateFile(jarvis.getPathfinder());
                    jarvis.clearConsole();
                    if (komba == null) jarvis.error();
                    else {
                        komba.afficherAutomate();
                        komba.allVerifs(true);
                        if (komba.isAsynchrone()) {
                            Automate newkomba = komba.determinisation_completion_asynchrone();
                            newkomba.afficherAutomate();
                        } else {
                            komba.determinisation_completion_synchrone();
                            komba.afficherAutomate();
                        }
                    }
                }

                /*if (jarvis.getChoix().equals("k")) {// Choix determinisation+complétion synchrone
                    Automate kombo = Readwrite.readAutomateFile(jarvis.getPathfinder());
                    if (kombo.isAsynchrone()) {
                        if (kombo.isAsynchrone()) {
                            System.out.println("L'automate " + kombo.label + " est asynchrone !");
                        }
                        
                    }
                    else{
                        jarvis.clearConsole();
                        kombo.afficherAutomate();
                        kombo.determinisation();
                        kombo.completion();
                        kombo.setLabel("Deterministe complet synchrone");
                        kombo.afficherAutomate();
                    }
                }*/

                if (jarvis.getChoix().equals("s")) {// Choix Standard
                    Automate standard = Readwrite.readAutomateFile(jarvis.getPathfinder());
                    jarvis.clearConsole();
                    if (standard == null) jarvis.error();
                    else {
                        standard.afficherAutomate();
                        standard.verifStandard(true);
                        standard.standardisation("i");
                        standard.setLabel("Standardisé");
                        standard.afficherAutomate();
                    }
                }

                if (jarvis.getChoix().equals("c")) {// Choix Complet
                    Automate complet = Readwrite.readAutomateFile(jarvis.getPathfinder());
                    jarvis.clearConsole();
                    if (complet == null) jarvis.error();
                    else {
                        complet.afficherAutomate();
                        complet.verifComplet(true);
                        complet.completion();
                        complet.setLabel("Complet");
                        complet.afficherAutomate();
                    }
                }

                if (jarvis.getChoix().equals("m")) {// Choix Mini
                    Automate mini = Readwrite.readAutomateFile(jarvis.getPathfinder());
                    jarvis.clearConsole();
                    if (mini == null) jarvis.error();
                    else {
                        mini.afficherAutomate();
                        mini.minimisation();
                        mini.setLabel("Minimisé");
                        mini.afficherAutomate();
                    }
                }

                if (jarvis.getChoix().equals("b")) {//Choix elimination epsilon
                    Automate async = Readwrite.readAutomateFile(jarvis.getPathfinder());
                    jarvis.clearConsole();
                    if (async == null) jarvis.error();
                    else {
                        async.afficherAutomate();
                        async.verifAsynchrone(true);
                        Automate sync = async.eliminationEpsilon();
                        sync.setLabel("Automate fini");
                        sync.afficherAutomate();
                    }
                }

                if (jarvis.getChoix().equals("k")) {//Choix elimination epsilon
                    Automate comp = Readwrite.readAutomateFile(jarvis.getPathfinder());
                    jarvis.clearConsole();
                    if (comp == null) jarvis.error();
                    else {
                        comp.afficherAutomate();
                        if (comp.isAsynchrone()) comp = comp.langage_complementaire_asynchrone();
                        else comp.langage_complementaire_synchrone();
                        comp.setLabel("Complémentaire");
                        comp.afficherAutomate();
                    }
                }

                if (jarvis.getChoix().equals("v")) {//Choix verif mot
                    Automate auto = Readwrite.readAutomateFile(jarvis.getPathfinder());
                    jarvis.clearConsole();
                    if (auto == null) jarvis.error();
                    else {
                        auto.afficherAutomate();
                        auto.lire_mot();
                    }
                }

            }
            
            System.out.println("\n---------------------------> Voulez vous quittez ? tapez o");
            
            jarvis.setChoix("o");

            if (jarvis.getChoix().equals("o")){
                jarvis.clearConsole();
                jarvis.closescanner();
                menu0 = 0;
            }
        }
    }
}
