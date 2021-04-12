package Automate;

public class Main {
    public static void main(String[] args) {

        Automate automate = Readwrite.readAutomateFile("src/Automate/test_automate.txt");
        assert automate != null;// Verification que l'automate à bien été remplie (Il me semble que dans readwrite la fonction try permet la meme chose a voir)

        automate.afficherAutomate();
        //System.out.println("test\n");
        //automate.lecture_automate_branche(automate.getEtats(0), "a");
        //System.out.println();
        //automate.standardisation("i");
        //automate.setLabel("Standardisé");
        automate.determinisation();
        automate.setLabel("Deterministe");
        automate.afficherAutomate();
        // for(Etat etat: automate.entrees)
        // System.out.println(etat.label);
        //automate.navigation();
        //automate.determinisation();
        //automate.doublon(automate.etats[2].charTransitions);


    }




}
