//package Automate;

/**
 * Menu du programme qui permet de selectionner les actions, on enverra a chaque fois un clone de l'automate initiale
 */
public class Menu {
    public static void main(String[] args) {
        int menu0 = 1;
        int menu1 = 1;

        Multifonctions jarvis = new Multifonctions();//admin du menu

        jarvis.clearConsole();
        jarvis.autoSelection();

        Automate automate = Readwrite.readAutomateFile(jarvis.getPathfinder());

        assert automate != null;
        jarvis.clearConsole();

        automate.afficherAutomate();
        
        while (menu0 == 1) {//Boucle du menu
           

            while (menu1 == 1) {
                jarvis.laCarte();// affichage du menu + choix
                
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
                    deter.afficherAutomate();
                    deter.determinisation();
                    deter.setLabel("Determinisé");
                    deter.afficherAutomate();
                }

                if (jarvis.getChoix().equals("a")) {// Choix determinisation+complétion synchrone
                    Automate komba = Readwrite.readAutomateFile(jarvis.getPathfinder());
                    jarvis.clearConsole();
                    komba.afficherAutomate();
                    Automate newkomba = komba.determinisation_completion_asynchrone();
                    newkomba.afficherAutomate();
                }

                if (jarvis.getChoix().equals("k")) {// Choix determinisation+complétion synchrone
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
                }

                

                if (jarvis.getChoix().equals("s")) {// Choix Standard
                    Automate standard = Readwrite.readAutomateFile(jarvis.getPathfinder());
                    jarvis.clearConsole();
                    standard.afficherAutomate();
                    standard.standardisation("i");
                    standard.setLabel("Standardisé");
                    standard.afficherAutomate();
                }

                if (jarvis.getChoix().equals("c")) {// Choix Complet
                    Automate complet = Readwrite.readAutomateFile(jarvis.getPathfinder());
                    jarvis.clearConsole();
                    complet.afficherAutomate();
                    complet.completion();
                    complet.setLabel("Complet");
                    complet.afficherAutomate();
                }

                if (jarvis.getChoix().equals("m")) {// Choix Mini
                    Automate mini = Readwrite.readAutomateFile(jarvis.getPathfinder());
                    jarvis.clearConsole();
                    mini.afficherAutomate();
                    mini.standardisation("i");
                    mini.setLabel("Standardisé");
                    mini.afficherAutomate();
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
