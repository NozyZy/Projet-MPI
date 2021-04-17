package Automate;

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

                if (jarvis.getChoix().equals("e")){
                    jarvis.clearConsole();
                    menu1 = 0;
                }

                if (jarvis.getChoix().equals("g")) {
                    jarvis.clearConsole();
                    jarvis.autoSelection();
                    jarvis.clearConsole();
                    
                    Automate nintendo = Readwrite.readAutomateFile(jarvis.getPathfinder());
                    assert nintendo != null;

                    nintendo.afficherAutomate();
                    jarvis.laCarte();//affichage du menu + choix

                }
                

                if (jarvis.getChoix().equals("d")) {//Choix determinisation
                    Automate deter = Readwrite.readAutomateFile(jarvis.getPathfinder());
                    jarvis.clearConsole();
                    deter.afficherAutomate();
                    deter.determinisation();
                    deter.setLabel("Determinisé");
                    deter.afficherAutomate();
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
                    complet.standardisation("i");
                    complet.setLabel("Standardisé");
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
