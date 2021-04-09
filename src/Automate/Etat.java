package Automate;

import java.util.ArrayList;

public class Etat {
    //int label;
    String nom;                          //nom de l'état
    ArrayList<String> transitions;     //toutes les transitions
    //ArrayList<Integer> transitions; // toutes les transitions version int
    ArrayList<String> charTransitions;  //tous les caractères de transition
    int nbTransitions;                  //le nombre de transistions sortantes
    boolean entree;                     //true si oui, false sinon
    boolean sortie;                     //true si oui, false sinon
    boolean read;                       //true si déjà lu, false sinon

    public final char MOT_VIDE = '*';


    /*public Etat(int label){ 
      setLabel(label);
    }*/

    public Etat(String nom){
        setNom(nom);
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNbTransitions() {
        return this.nbTransitions;
    }

    public void setNbTransitions(int nbTransitions) {
        this.nbTransitions = nbTransitions;
    }

    public boolean isEntree() {
        return this.entree;
    }

    public boolean getEntree() {
        return this.entree;
    }

    public void setEntree(boolean entree) {
        this.entree = entree;
    }

    public boolean isSortie() {
        return this.sortie;
    }

    public boolean getSortie() {
        return this.sortie;
    }

    public void setSortie(boolean sortie) {
        this.sortie = sortie;
    }

    public boolean isRead() {
        return this.read;
    }

    public boolean getRead() {
        return this.read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public char getMOT_VIDE() {
        return this.MOT_VIDE;
    }


    public String getTransitions(int x) {
        return this.transitions.get(x);
    }

    public void setTransitions(String transitions, int x) {
        this.transitions.add(x, transitions);
    }

    public String getCharTransitions(int x) {
        return this.charTransitions.get(x);
    }

    public void setCharTransitions(String charTransitions, int x) {
        this.charTransitions.add(x, charTransitions);
    }


    public void affiche_etat(){
        System.out.println("\n"+"Vous êtes à l'état : "+this.nom);
    }



}