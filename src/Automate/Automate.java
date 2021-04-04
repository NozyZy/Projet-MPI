package Automate;

public class Automate {
    String label;
    Etat[] entrees;
    int nbEtats;
    int nbEntrees;
    boolean deterministe;
    boolean complet;
    boolean asynchrone;
    boolean standard;
    boolean minimale;

    Automate(String label, Etat[] entrees, int nbEntrees, int nbEtats){
        this.label = label;
        if (entrees == null) this.entrees = null;
        else this.entrees = entrees.clone();
        this.nbEntrees = nbEntrees;
        this.nbEtats = nbEtats;
        deterministe = complet = asynchrone = standard = minimale = false;
    }

    void afficherAutomate() {
        for (int i = 0; i < nbEntrees; i++){
            entrees[i].printAllEtats();
        }
        for (int i = 0; i < nbEntrees; i++){
            entrees[i].resetRead();
        }
    }

}

