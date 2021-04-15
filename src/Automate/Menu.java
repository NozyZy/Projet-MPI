package Automate;

/**
 * Menu du programme qui permet de selectionner les actions, on enverra a chaque fois un clone de l'automate initiale
 */
public class Menu {
    public static void main(String[] args) {
        int menu0 = 1;
        int menu1 = 1;

        Multifonctions jarvis = new Multifonctions();
        Automate automate = Readwrite.readAutomateFile("src/Automate/test_automate.txt");
        assert automate != null;

        while (menu0 == 1) {

            while (menu1 == 1) {

                System.out.println("test copie defensive");
                automate.afficherAutomate();
                
                System.out.println(
                "\n------------------------Voici le menu : que voulez vous faire ?-----------------------------------\n\n" + 
                "---------------------------> d : determinisation\n" +
                "---------------------------> s : standardisation\n" +
                "---------------------------> e : exit\n");

                jarvis.setChoix();

                
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

            }
            
            System.out.println("\n---------------------------> Voulez vous quittez ? tapez o");
            
            jarvis.setChoix();

            if (jarvis.getChoix().equals("o")){
                jarvis.clearConsole();
                jarvis.closesaisie();
                menu0 = 0;
            }
        }
    }
}
