package Automate;

public class Main {
    public static void main(String[] args) {

        Automate automate = Readwrite.readAutomateFile("src/Automate/test_automate.txt");
        assert automate != null;// Verification que l'automate à bien été remplie (Il me semble que dans
                                // readwrite la fonction try permet la meme chose a voir)

        automate.afficherAutomate();
        // for(Etat etat: automate.entrees)
        // System.out.println(etat.label);
        automate.navigation();
        //automate.determinisation();

        //automate.doublon(automate.etats[2].charTransitions);

    }

}
