package Automate;

import java.util.ArrayList;

public class Etat {
    int index;                             //index de l'état dans le tableau automate
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

    /**
     * Cree un tableau dynamique en <String> pour le tableau des nom de transitions (a ,b ,c...)
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


    public void affiche_etat(){
        System.out.println("\n"+"Vous êtes à l'état : "+this.nom);
    }



}