package Automate;

public class Main {
    public static void main(String[] args) {

        Automate automate = Readwrite.readAutomateFile("src/Automate/test_automate.txt");
        assert automate != null;// Verification que l'automate à bien été remplie (Il me semble que dans
                                // readwrite la fonction try permet la meme chose a voir)

        automate.afficherAutomate();
        // for(Etat etat: automate.entrees)
        // System.out.println(etat.label);
        //automate.navigation();
        //automate.determinisation();
        //automate.doublon(automate.etats[2].charTransitions);

        automate.etats[0].setNom("Gianlucca");
        automate.etats[1].setNom("Apex");
        automate.etats[2].setNom("Fanny");
        automate.etats[3].setNom("Raoul");
        automate.etats[4].setNom("l'état 4");

        System.out.println("test : "+automate.etats[2].transitions);
        System.out.println();
        automate.afficherAutomate();

    }




}
