package Automate;

public class Main {
    public static void main(String[] args) {

        //Multifonctions jarvis = new Multifonctions();
        //jarvis.clearConsole();
        //jarvis.autoSelection();

        Automate automate = Readwrite.readAutomateFile("index/E1-7.txt");
        //assert automate != null;// Verification que l'automate à bien été remplie (Il me semble que dans readwrite la fonction try permet la meme chose a voir)
        automate.afficherAutomate();
        automate.determinisation();
        automate.afficherAutomate();
        System.out.println(automate.contains(""));
        System.out.println(automate.contains("a"));
        System.out.println(automate.contains("aaaaa"));
        System.out.println(automate.contains("aaaaaaaaaaaddddddddddd"));
        //System.out.println("test\n");
        //System.out.println();
        //automate.standardisation("i");
        //automate.setLabel("Standardisé");
        //automate.determinisation();
        //automate.setLabel("Deterministe");
        //automate.afficherAutomate();
        //System.out.println(automate.contains("abaa"));
        //automate.afficherAutomate();
        //System.out.println("-----------------------------------> "+automate.getEtatEntree(1).getNom());
        //System.out.println("-----------------------------------> "+automate.mitose(automate.getEtats(0)));
        //automate.afficherAutomate();
        // for(Etat etat: automate.entrees)
        // System.out.println(etat.label);
        //automate.navigation();
        //automate.determinisation();
        //automate.doublon(automate.etats[2].charTransitions);


    }

}
