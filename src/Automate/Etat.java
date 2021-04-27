package Automate;

import java.util.ArrayList;

public class Etat {
    int index;                          //index de l'état dans le tableau automate
    String nom;                         //nom de l'état
    ArrayList<String> transitions;      //toutes les transitions
    ArrayList<String> charTransitions;  //tous les caractères de transition
    boolean entree;                     //true si oui, false sinon
    boolean sortie;                     //true si oui, false sinon
    boolean read;

    public final char MOT_VIDE = '*';

    /**
     * Constructeur d'etat avec un nom et sa position dans l'automate
     * @param nom
     * @param index
     */
    public Etat(String nom, int index){
        setNom(nom);
        setIndex(index);
    }


    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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

    public int nbTransitions() {
        return getTabCharTransitions().size();
    }

    public String getTransitions(int x) {
        return this.transitions.get(x);
    }

    public void setTransitions(String transitions) {
        this.transitions.add(transitions);
    }

    public void setTransitions(int i, String transitions) {
        getTabTransitions().remove(i);
        this.transitions.add(i, transitions);
    }

    public ArrayList<String> getCharTransitions() {
        return this.charTransitions;
    }

    public String getCharTransitions(int x) {
        return this.charTransitions.get(x);
    }

    public void setCharTransitions(String charTransitions) {
        this.charTransitions.add(charTransitions);
    }

    public void setCharTransitions(int i, String charTransitions) {
        getTabCharTransitions().remove(i);
        this.charTransitions.add(i, charTransitions);
    }

    public void setTotalTransitions(String transition, String element){
        setCharTransitions(transition);
        setTransitions(element);
    }

    /**
     * Cree un tableau dynamique en <String> pour le tableau des nom de transitions (a, b, c...)
     */
    public void setTabCharTransitions(){
        this.charTransitions = new ArrayList<String>();
    }

    public ArrayList<String> getTabCharTransitions(){
        return charTransitions;
    }

    /**
     * Cree un tableau dynamique en <String> pour le tableau des transitions
     * (e0, e1, e2...)
     */
    public void setTabTransitions() {
        this.transitions = new ArrayList<String>();
    }

    public ArrayList<String> getTabTransitions() {
        return transitions;
    }

    public void afficher_transition(int index) {
        System.out.print(" " + nom + getCharTransitions(index) + getTransitions(index) + " ");
    }

    public void affiche_etat(){
        System.out.println("\n"+"Vous êtes à l'état : " + this.nom);
    }

    public void affiche_etat_complet() {
        System.out.println("\n" + "Vous êtes à l'état : " + this.nom);

        for (int j = 0; j < getTabCharTransitions().size(); j++) {
            System.out.print("(" + getNom() + ")");
            System.out.print("-" + getTabCharTransitions().get(j) + "->");
            System.out.println("(" + getTabTransitions().get(j) + "), ");

        }
    }
}