import java.util.ArrayList;

public class Etat {
    int index;                          //index de l'état dans le tableau automate
    String nom;                         //nom de l'état
    ArrayList<String> transitions;      //toutes les transitions
    //ArrayList<Integer> transitions;   // toutes les transitions version int
    ArrayList<String> charTransitions;  //tous les caractères de transition
    ArrayList<String> groupeMinimisation;
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

    public void setTransitions(String transitions) {
        this.transitions.add(transitions);
    }

    public void setTransitions(int i, String transitions) {
        getTabTransitions().remove(i);
        this.transitions.add(i, transitions);
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


    public void setGroupeMinimisation(int i, String groupeMinimisation) {
        getTabTransitions().remove(i);
        this.groupeMinimisation.add(i, groupeMinimisation);
    }
    public String getGroupeMinimisation(int i) {
        return this.groupeMinimisation.get(i);
    }

    public void setGroupeMinimisation(String groupeMinimisation) {
        this.groupeMinimisation.add(groupeMinimisation);
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

    public void setGroupeMinimisation(){
        this.groupeMinimisation = new ArrayList<String>();
    }

    public ArrayList<String> getGroupeMinimisation(){
        return groupeMinimisation;
    }

    public void affiche_etat(){
        System.out.println("\n"+"Vous êtes à l'état : "+this.nom);
    }

    public void affiche_etat(String all) {
        System.out.println("\n" + "Vous êtes à l'état : " + this.nom);

        for (int j = 0; j < getTabCharTransitions().size(); j++) {
            System.out.print("(" + getNom() + ")");
            System.out.print("-" + getTabCharTransitions().get(j) + "->");
            System.out.println("(" + getTabTransitions().get(j) + "), ");

        }
    }



}