package Automate;

import java.util.Arrays;

public class Etat {
    String label;               //nom de l'état
    Etat[] transitions;         //toutes les transitions
    char[] charTransitions;     //tous les caractères de transition
    int nbTransitions;          //le nombre de transistions sortantes
    boolean entree;             //true si oui, false sinon
    boolean sortie;             //true si oui, false sinon
    boolean read;               //true si déjà lu, false sinon
    public final char MOT_VIDE = '*';

    Etat(String label, Etat[] transitions, char[] charTransitions, int nbTransitions, boolean entree, boolean sortie){
        this.label = label;
        if (transitions == null) this.transitions = null;
        else this.transitions = transitions.clone();
        this.charTransitions = charTransitions;
        this.nbTransitions = nbTransitions;
        this.entree = entree;
        this.sortie = sortie;
        read = false;
    }

    @Override
    public String toString() {
        String text =  "\n(";
        if (entree) text += ("->");
        text += (label);
        if (sortie) text += ("->");
        text += (")");
        if (nbTransitions > 0) {
            for (int i = 0; i < nbTransitions; i++) {
                text += (" /--") + charTransitions[i] + ("->");
                text += ("(");
                if (transitions[i].entree) text += ("->");
                text += (transitions[i].label);
                if (transitions[i].sortie) text += ("->");
                text += (")");
            }
        }
        return text;
    }

    void resetRead() {
        read = false;
        for (int i = 0; i < nbTransitions; i++){
            if (transitions[i].read) transitions[i].resetRead();
        }
    }

    void printEtat() {
        System.out.print("\n(");
        if (entree) System.out.print("->");
        System.out.print(label);
        if (sortie) System.out.print("->");
        System.out.print(")");
        if (nbTransitions > 0) {
            for (int i = 0; i < nbTransitions; i++) {
                System.out.print(" /--" + charTransitions[i] + "->");
                System.out.print("(");
                if (transitions[i].entree) System.out.print("->");
                System.out.print(transitions[i].label);
                if (transitions[i].sortie) System.out.print("->");
                System.out.print(")");
            }
        }
        read = true;
    }

    void printAllEtats() {
         printEtat();
         if (transitions != null) {
             for (Etat etat : transitions) {
                 if (!etat.read) etat.printAllEtats();
             }
         }
    }

    boolean contains(String string, int index) {
        if (string == null) return false;
        if (sortie && string.length() == index) return true;
        if (string.length() <= index) return false;
        int i = 0;
        while(i < nbTransitions && charTransitions[i] != string.charAt(index)) {
            i++;
        }
        if (i == nbTransitions) return false;
        return transitions[i].contains(string, index+1);
    }

    boolean contains(String string) {
        return contains(string, 0);
    }

}
