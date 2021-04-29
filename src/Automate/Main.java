

public class Main {
    public static void main(String[] args) {

        Multifonctions jarvis = new Multifonctions();
        jarvis.clearConsole();
        //jarvis.autoSelection();
        //String mot = "32";

        //mot = jarvis.sorted(mot);
        //System.out.println(mot);

        Automate automate = Readwrite.readAutomateFile("index/C_automate.txt");
        automate.afficherAutomate();
        /*if (automate.isPointer(automate.pointeur_Etat("3"))) {
            System.out.println("Yeah je suis pointer");
        }
        else{
            System.out.println("Eh frero je suis pas pointer");
        }*/
        //assert automate != null;// Verification que l'automate à bien été remplie (Il me semble que dans readwrite la fonction try permet la meme chose a voir)
        //System.out.println(automate.contains("abaa"));
        //System.out.println("test\n");
        //System.out.println();
        //automate.standardisation("i");
        //automate.setLabel("Standardisé");
        automate.determinisation();
        automate.setLabel("Deterministe");
        //automate.nettoyage();
        //automate.fusion_Entrees();
        automate.afficherAutomate();
        //System.out.println(automate.nbEtats);
        //automate.fusion_multiple(automate.getNbEntrees()).affiche_etat("all");
        //automate.setLabel("Deterministe");
        //automate.determinisation();
        //automate.mitose(automate.getEtatEntree());
        //System.out.println(automate.contains("abaa"));
        //System.out.println();
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
