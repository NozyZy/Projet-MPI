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

    boolean isAsynchrone() {
        boolean state = false;
        for (int i = 0; i < nbEntrees; i++){
            boolean next = entrees[i].isAsynchrone();
            if (next) {
                state = true;
                asynchrone = true;
            }
        }
        for (int i = 0; i < nbEntrees; i++){
            entrees[i].resetRead();
        }
        return state;
    }

    boolean contains(String string) {
        boolean contains = false;
        for(Etat etat: entrees) {
            contains = etat.contains(string);
            if (contains) return true;
        }
        return false;
    }

}

