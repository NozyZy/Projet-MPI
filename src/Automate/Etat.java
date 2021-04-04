package Automate;

import java.util.Arrays;

public class Etat {
    String label;               //nom de l'état
    Etat[] transitions;         //toutes les transitions
    char[] charTransitions;     //tous les caractères de transition
    int nbTransitions;          //le nombre de transistions sortantes
    boolean entree;             //true si oui, false sinon
    boolean sortie;             //true si oui, false sinon
    boolean read;                   //true si déjà lu, false sinon

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
}
