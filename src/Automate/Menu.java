package Automate;

/**
 * Menu du programme qui permet de selectionner les actions, on enverra a chaque fois un clone de l'automate initiale
 */
public class Menu {
    public static void main(String[] args) {
        int menu0 = 1;
        int menu1 = 1;

        Multifonctions jarvis = new Multifonctions();//admin du menu
        Automate automate = Readwrite.readAutomateFile("src/Automate/test_automate.txt");
        assert automate != null;
        jarvis.clearConsole();

        while (menu0 == 1) {

            while (menu1 == 1) {

                automate.afficherAutomate();
                
                System.out.println(
                "\n------------------------Voici le menu : que voulez vous faire ?-----------------------------------\n\n" + 
                "---------------------------> d : Déterminisation\n" +
                "---------------------------> s : Standardisation\n" +
                "---------------------------> c : Complétion \n" +
                "---------------------------> m : Miniminisation \n" +
                "---------------------------> e : exit\n");

                jarvis.setChoix("dscme");

                if (jarvis.getChoix().equals("e")){
                    jarvis.clearConsole();
                    menu1 = 0;
                }
                

                if (jarvis.getChoix().equals("d")) {//Choix determinisation
                    Automate deter = Readwrite.readAutomateFile("src/Automate/test_automate.txt");
                    jarvis.clearConsole();
                    deter.determinisation();
                    deter.setLabel("Determinisé");
                    deter.afficherAutomate();
                }

                if (jarvis.getChoix().equals("s")) {// Choix determinisation
                    Automate standard = Readwrite.readAutomateFile("src/Automate/test_automate.txt");
                    jarvis.clearConsole();
                    standard.standardisation("i");
                    standard.setLabel("Standardisé");
                    standard.afficherAutomate();
                }

                if (jarvis.getChoix().equals("c")) {// Choix determinisation
                    Automate complet = Readwrite.readAutomateFile("src/Automate/test_automate.txt");
                    jarvis.clearConsole();
                    complet.standardisation("i");
                    complet.setLabel("Standardisé");
                    complet.afficherAutomate();
                }

                if (jarvis.getChoix().equals("m")) {// Choix determinisation
                    Automate mini = Readwrite.readAutomateFile("src/Automate/test_automate.txt");
                    jarvis.clearConsole();
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
