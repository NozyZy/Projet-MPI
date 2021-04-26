

import java.io.*;
import java.util.Scanner;


import java.util.ArrayList;

/**
 * Tienes aue respetar las indicationes por ejemplo en la playa no botes tus vasuras
 * 
 * trata des commpartir y apprender la cultura local como
 * descourir nueva gastronomia
 */


public class Automate implements Cloneable {

    Multifonctions jarvis = new Multifonctions();//Majordome
    private static final Etat Error = null;

    public final char MOT_VIDE = '*';

    String label;
    ArrayList<Etat> etats;
    int nbEtats;
    int initnbEtats;
    int nbEntrees;
    int nbSorties;
    int nbTransitions;
    int initnbTransitions;
    boolean deterministe;
    boolean standard;
    
    boolean complet;
    boolean asynchrone;
    boolean minimale;



    public char getMOT_VIDE() {
        return this.MOT_VIDE;
    }


    public int getInitnbTransitions() {
        return this.initnbTransitions;
    }

    public void setInitnbTransitions(int initnbTransitions) {
        this.initnbTransitions = initnbTransitions;
    }


    public int getInitnbEtats() {
        return this.initnbEtats;
    }

    public void setInitnbEtats(int initnbEtats) {
        this.initnbEtats = initnbEtats;
    }

    public void suppression_Etat(Etat element){
        this.etats.remove(element);
        this.nbEtats --;
    }

    /**
     * Supression des transitions depuis un état a une lettre donnée
     * @param element
     * @param transition
     */
    public void suppression_Transition(Etat element, String transition){
        int x = element.getTabCharTransitions().indexOf(transition);

        element.getTabCharTransitions().remove(x);
        element.getTabTransitions().remove(x);

    }
    
    /**
     * Supression des transitions depuis un état a un index donnée
     * 
     * @param element
     * @param x
     */
    public void suppression_Transition(Etat element, int x) {

        element.getTabCharTransitions().remove(x);
        element.getTabTransitions().remove(x);

    }

    /**
     * Permet de recuper un état à un indice donnée
     * @param i
     * @return l'état
     */
    public Etat getEtats(int i) {
        return this.etats.get(i);
    }

    /**
     * Permet de créer un etat dans l'automate à un indice donnée
     * et ses tableaux de transitions sont egalement initialisés
     * @param i indice
     * @param nom le nom de l'etat en string
     */
    public void setEtats(int i, String nom){
        this.etats.add(i, new Etat(nom,i));
        getEtats(i).setTabTransitions();
        getEtats(i).setTabCharTransitions();
        this.nbEtats++;
    }

    /**
     * Permet de créer un etat dans l'automate à l'indice 0 et ses tableaux de
     * transitions sont egalement initialisés
     * 
     * @param nom le nom de l'etat en string
     */
    public void setEtats(String nom) {
        this.etats.add(0, new Etat(nom, 0));
        getEtats(0).setTabTransitions();
        getEtats(0).setTabCharTransitions();
        this.nbEtats++;
    }

    /**
     * Cree le tableau dynamique d'etat
     */
    public void setTabEtats(){
        this.etats = new ArrayList<Etat>();
    }

    public ArrayList<Etat> getTabEtats(){
        return etats;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getNbEtats() {
        return this.nbEtats;
    }

    public void setNbEtats(int nbEtats) {
        this.nbEtats = nbEtats;
    }

    public int getNbEntrees() {
        return this.nbEntrees;
    }

    public void setNbEntrees(int nbEntrees) {
        this.nbEntrees = nbEntrees;
    }

    public int getNbSorties() {
        return this.nbSorties;
    }

    public void setNbSorties(int nbSorties) {
        this.nbSorties = nbSorties;
    }

    public int getNbTransitions() {
        return this.nbTransitions;
    }

    public void setNbTransitions(int nbTransitions) {
        this.nbTransitions = nbTransitions;
    }

    public boolean isDeterministe() {
        return this.deterministe;
    }

    public boolean getDeterministe() {
        return this.deterministe;
    }

    public void setDeterministe(boolean deterministe) {
        this.deterministe = deterministe;
    }

    public boolean isComplet() {
        return this.complet;
    }

    public boolean getComplet() {
        return this.complet;
    }

    public void setComplet(boolean complet) {
        this.complet = complet;
    }

    public boolean isAsynchrone() {
        return this.asynchrone;
    }

    public boolean getAsynchrone() {
        return this.asynchrone;
    }

    public void setAsynchrone(boolean asynchrone) {
        this.asynchrone = asynchrone;
    }

    public boolean isStandard() {
        return this.standard;
    }

    public boolean getStandard() {
        return this.standard;
    }

    public void setStandard(boolean standard) {
        this.standard = standard;
    }

    public boolean isMinimale() {
        return this.minimale;
    }

    public boolean getMinimale() {
        return this.minimale;
    }

    public void setMinimale(boolean minimale) {
        this.minimale = minimale;
    }


    /**
     * Cherche l'etat dans l'automate (tableau d'objets etats) et renvoie l'etat demander par son nom lorsqu'il le trouve 
     * @param nom : etat demander
     * @return l'etat trouver par son nom / retourne l'etat Error
     */
    public Etat pointeur_Etat(String nom){

        for (int i = 0; i < getTabEtats().size(); i++) {
            if (getEtats(i).getNom().equals(nom)){
                return getEtats(i);
            }
        }

        //System.out.println(nom+" N'existe pas");
        return Error;
    }

    /**
     * Recoit le tableau de transition et return true s'il ya un doublon dans les
     * transition
     * 
     * @param tab tableau de char en dynamique
     * @return true or false
     */
    public boolean doublon(ArrayList<String> tab) {
        String tmp;
        int occ = 0;

        for (int i = 0; i < tab.size(); i++) {
            tmp = tab.get(i);

            for (int j = 0; j < tab.size(); j++) {
                if (tab.get(j).equals(tmp)) {
                    occ++;
                }

            }

            if (occ > 1) {
                occ = 0;
                return true;
            }
        }
        return false;
    }



    Automate(String label, Etat[] etats, int nbEntrees, int nbEtats){
        this.label = label;
        if (etats == null) this.etats = null;
        //else this.etats = etats.clone();
        this.nbEntrees = nbEntrees;
        this.nbEtats = nbEtats;
        deterministe = complet = asynchrone = standard = minimale = false;
    }

    /**
     * Constructeur d'automate a partir d'un fichier txt
     * @param fichier
     */

    /*public Automate(FileInputStream fichier){

        Scanner lecture = new Scanner(fichier);//debut de lecture du fichier

        setLabel(lecture.nextLine());

        setNbEtats(lecture.nextInt());
        this.etats = new Etat[this.nbEtats];//creation du nombre d'élement dans l'automate

        for (int i = 0; i < this.nbEtats; i++) {
            this.etats[i] = new Etat(i);
            this.etats[i].transitions = new ArrayList<Integer>();
            this.etats[i].charTransitions = new ArrayList<String>();
        }
        System.out.println("\n");

        
        this.setNbEntrees(lecture.nextInt());

        for (int i = 0; i < this.nbEntrees; i++) {
            this.etats[lecture.nextInt()].setEntree(true);
        }

        this.nbSorties = lecture.nextInt();
        for (int i = 0; i < this.nbSorties; i++) {
            this.etats[lecture.nextInt()].setSortie(true);
        }

        this.setNbTransitions(lecture.nextInt());

        /**
         * Boucle qui permet l'enrengistrement de l'automate depuis un txt
         */
        /*for (int i = 0; i < this.nbTransitions; i++) {
            int x = lecture.nextInt();                          //on copie le nom de l'element
            this.etats[x].charTransitions.add(lecture.next());  //on ajoute la transtion (a,b,c,d...)
            this.etats[x].transitions.add(lecture.nextInt());   //on ajoute l'élement pointer (etat 1, etat2...)
            this.etats[x].nbTransitions++;
        }

        lecture.close();//fermeture de la lecture du txt
        /
    }*/


    /**
     * Constructeur d'automate a partir d'un fichier txt V2
     * 
     * @param fichier
     */
    public Automate(FileInputStream fichier){

        Scanner lecture = new Scanner(fichier);// debut de lecture du fichier

        setLabel(lecture.nextLine());
        setInitnbEtats(lecture.nextInt());//Nombre d'etat initiale donnée en txt
        setTabEtats();// creation du tableau dynamique d'objet d'etat => l'automate

       

        for (int i = 0; i < getInitnbEtats(); i++) {
            setEtats(i,lecture.next());
        }

        setNbEntrees(lecture.nextInt());

        for (int i = 0; i < getNbEntrees(); i++) {
            pointeur_Etat(lecture.next()).setEntree(true);
        }

        setNbSorties(lecture.nextInt());

        for (int i = 0; i < getNbSorties(); i++) {
           pointeur_Etat(lecture.next()).setSortie(true);
        }

        setInitnbTransitions(lecture.nextInt());

        /**
         * Boucle qui permet l'enrengistrement de l'automate depuis un txt
         */
        for (int i = 0; i < getInitnbTransitions(); i++) {
            int x = pointeur_Etat(lecture.next()).getIndex();

            getEtats(x).setTotalTransitions(lecture.next(), lecture.next()); // on ajoute la transtion (a,b,c,d...), on ajoute l'élement pointer (etat 1, etat2...)
            //getEtats(x).nbTransitions++; //on augmente le nombre de transitions
        }
        lecture.close();// fermeture de la lecture du txt
    }

    /**
     * Affiche l'automate en string
     */
    public void afficherAutomate() {
        System.out.println("Voici l'automate : "+ getLabel() +"\n");

        for (int i = 0; i < getTabEtats().size(); i++) {

            if (getEtats(i).isEntree()) {
                System.out.print("E--> ");
                
            }
            if (getEtats(i).isSortie()) {
                System.out.print("S--> ");
            }
            
            
            System.out.println("**" + getEtats(i).getNom() + "**");
            

            for (int j = 0; j < getEtats(i).getTabCharTransitions().size(); j++) {
                System.out.print("(" + getEtats(i).getNom() + ")");
                System.out.print("-" + getEtats(i).getTabCharTransitions().get(j) + "->");
                System.out.println("("+getEtats(i).getTabTransitions().get(j)+"), ");

            }
            System.out.println();

            
        }
    }

    /**
     * Fonction de navigation dans l'automate
     */
    /**public void navigation(){
        int nav = 1;
        int etat = 0;
        String transision;
        String choix;
        Scanner saisie = new Scanner(System.in);//Objet saisisseur

        System.out.println("Bienvenue dans le menu de navigation de l'automate"+"\n");

        System.out.println("--Entrée de l'automate--");
        while (nav == 1) {
            

            this.etats[etat].affiche_etat();
            System.out.println("\n"+"Voici les chemins possibles : ");
            
            for (int i = 0; i < this.etats[etat].charTransitions.size(); i++) {
                System.out.println("-> : " + this.etats[etat].charTransitions.get(i));
            }
            if (this.etats[etat].sortie == true) {
                System.out.println("Sortie : s");
            }

            System.out.println("\n"+"Choisi ton chemin :");
            transision = saisie.next();


            if (transision.equals("s")) {
                nav = 0;
                System.out.println("\n"+"--Sortie de l'automate--");
            }

            for (int i = 0; i < this.etats[etat].charTransitions.size(); i++) {
                if (this.etats[etat].charTransitions.get(i).equals(transision)) {
            
                    etat = this.etats[etat].transitions.get(i);
                    this.etats[etat].affiche_etat();
                    
                    if (doublon(this.etats[etat].charTransitions)) {
                        System.out.println("\n" + "Voulez vous choisir cet Etat ?" + "\n"
                                + "appuyer sur o pour oui et n pour non");
                        choix = saisie.next();
                        if (choix.equals("o")) {
                            i = 666;
                        }
                    }
                   
                   
                }
            }

            System.out.println("\n"+"Voulez vous continuer de naviguer ?" + "\n" + 
            "appuyer sur o pour oui et n pour non");
            choix = saisie.next();
            if (choix.equals("n")) {
               nav = 0;
               saisie.close();
            }
        }
    }**/

    /**
     * Standardisation de l'automate
     */
    public void standardisation(String nom){

        setEtats(nom);//creation de l'état initiale

        for (int i = 0; i < getTabEtats().size(); i++) {
            if (getEtats(i).isEntree()){
                if (getEtats(i).isSortie()) {
                    pointeur_Etat(nom).setSortie(true);//si une entrée est une sortie l'état i en est aussi
                }

                for (int j = 0; j < getEtats(i).getTabTransitions().size(); j++) {
                    pointeur_Etat(nom).setTotalTransitions(getEtats(i).getCharTransitions(j), getEtats(i).getTransitions(j));
                }
                getEtats(i).setEntree(false);// on supprime les entrée
            }
        }

        pointeur_Etat(nom).setEntree(true);//on le met en entrée
        setStandard(true);
    }

    /**
     * Fusionne les état a et b pour cree l'état ab et supprimer a et b. ab recupere les transtisions de a + b
     * @param a
     * @param b
     */
    public void fusion_Etat(Etat a, Etat b){
        /**System.out.println("--------------------------------");
        for (int i = 0; i < getTabEtats().size(); i++) {
            System.out.println("Index 1 : " + getEtats(i).nom);
        }
        System.out.print("---------------------------------------------> ");
        a.affiche_etat();**/
        if (a == null || b == null){
        }
        else{
            setEtats(a.nom + b.nom);

            if (getNbEntrees() > 1 || getNbEntrees() == 1) {
                if ((pointeur_Etat(a.nom).isEntree()) || pointeur_Etat(b.nom).isEntree()) {
                    pointeur_Etat(a.nom + b.nom).setEntree(true);
                }
            }

            if ((pointeur_Etat(a.nom).isSortie()) || pointeur_Etat(b.nom).isSortie()) {
                pointeur_Etat(a.nom + b.nom).setSortie(true);
            }

            for (int i = 0; i < pointeur_Etat(a.nom).getTabTransitions().size(); i++) {
                pointeur_Etat(a.nom + b.nom).setTotalTransitions(pointeur_Etat(a.nom).getCharTransitions(i),
                        pointeur_Etat(a.nom).getTransitions(i));
            }

            for (int i = 0; i < pointeur_Etat(b.nom).getTabTransitions().size(); i++) {
                pointeur_Etat(a.nom + b.nom).setTotalTransitions(pointeur_Etat(b.nom).getCharTransitions(i),
                        pointeur_Etat(b.nom).getTransitions(i));
            }

            if (!(pointeur_Etat(a.nom).getTabTransitions().contains(a.nom))) {
                suppression_Etat(a);
            }

            if (!(pointeur_Etat(b.nom).getTabTransitions().contains(b.nom))) {
                suppression_Etat(b);
            }
        }
        
        /**System.out.println("--------------------------------");

        for (int i = 0; i < getTabEtats().size(); i++) {
            System.out.println("Index 2 : " + getEtats(i).nom);
        }**/

    }

    /**
     * Fusionne les transitions identique d'un element. Si element x a 2 transions de a ==> x a 1 transition a qui pointe vers un nouvel element consituer des transitions
     * @param element
     */
    public void fusion_transition(Etat element){

        String memory_element = "";
        String memory_char = "";
        int occ;
        
        for (int i = 0; i < element.getTabCharTransitions().size(); i++) {//fusion des transition
            
            memory_char = element.getCharTransitions(i);

            
            for (int j = 0; j < element.getTabTransitions().size(); j++) {
                if (element.getCharTransitions(j).equals(memory_char)){
                    memory_element += element.getTransitions(j);
                }
            }
            element.setTransitions(i, memory_element);
            memory_element = "";
        }

        for (int i = 0; i < element.getTabCharTransitions().size(); i++) {//Nettoyage
            occ = 0;
            memory_char = element.getCharTransitions(i);

            for (int j = 0; j < element.getTabTransitions().size(); j++) {
                if (element.getCharTransitions(j).equals(memory_char)){
                  occ++;
                  if (occ > 1) {
                      suppression_Transition(element, j);
                  }
                }
            }
        }

    }

   

    /**
     * Fonction qui cree tout les nouveaux état a partir d'un element, la fonction va chercher dans les transitions les nouveaux états (pour l'instant pas utilisé)
     * @param element
     */
    public void setMultipleEtat(Etat element){
        
        //element.affiche_etat("all");
        for (int i = 0; i < element.getTabCharTransitions().size(); i++) {
            String a, b , copy = "";

            if (element.getTransitions(i).length() > 1) {
                for (int j = 0; j < element.getTransitions(i).length(); j++) {

                    if (j+2 <= element.getTransitions(i).length()) {

                        a = jarvis.toString(element.getTransitions(i).charAt(j));
                        b = jarvis.toString(element.getTransitions(i).charAt(j+1));
                        copy += a;
                        
                        if (copy.length() > 1){
                            /**System.out.println("hey i'm the copy: " + copy);
                            System.out.println("---------------------voici le couple a : " + copy + " et b : " + b);**/
                            fusion_Etat(pointeur_Etat(copy), pointeur_Etat(b));
                        }
                        else{
                            System.out.println("---------------------voici le couple a : " + a + " et b : " + b);
                            pointeur_Etat(a).affiche_etat("all");
                            fusion_Etat(pointeur_Etat(a), pointeur_Etat(b));
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Permet de lire le plus long chemin d'une transition a partir d'un element de l'automate
     * @param element -> Etat de départ
     * @param transition -> Chemin souhaiter en string (a,b,c...)
     */
    public void lecture_automate_branche(Etat element, String transition){
 
        System.out.print(" -"+transition+"-> "+ element.nom);
        
        if (element.getTabCharTransitions().contains(transition)){
            lecture_automate_branche(pointeur_Etat(element.getTransitions(element.getTabCharTransitions().indexOf(transition))),transition);
        }

    }

    /**
     * Fonction qui test si un element existe par son nom
     * @param nom
     * @return True si existe False si non
     */
    public boolean doesEtatExist(String nom){
        if (pointeur_Etat(nom) == null) {
            return false;
        }
        return true;
    }

    /**
     * Reçois un Etat mère et construit les états filles non existantes
     * @param element Etat mère
     */
    public Etat mitose(Etat element){

        //element.affiche_etat("all");

        if (element == null) {
            return Error;
        }
        else{
            fusion_transition(element);
        }

        //element.affiche_etat("all");

        if (element.getTabCharTransitions().isEmpty()){
            //System.out.println("IS EMPTY");
            //element.affiche_etat("all");
            //System.out.println("TEEEEEEST");
            return Error;
        }
        else{
            //System.out.println("CHEECK 2");
            //element.affiche_etat("all");
            for (int i = 0; i < element.getTabTransitions().size(); i++) {
                if (!doesEtatExist(element.getTransitions(i))) {

                    if (element.getTransitions(i).length() == 2) {
                        // System.out.println("Checked 1");
                        fusion_Etat(pointeur_Etat(jarvis.toString(element.getTransitions(i).charAt(0))),
                                pointeur_Etat(jarvis.toString(element.getTransitions(i).charAt(1))));

                        mitose(pointeur_Etat(
                                jarvis.toString(element.getTransitions(i).charAt(0)) + element.getTransitions(i).charAt(1)));
                    }

                    else {
                        String linked_transitions = "";
                        // System.out.println("Checked 2");

                        for (int j = 0; j < element.getTransitions(i).length() - 1; j++) {
                            linked_transitions += jarvis.toString(element.getTransitions(i).charAt(j));

                        }
                        // System.out.println("-------------------------> " + linked_transitions);

                        fusion_Etat(pointeur_Etat(linked_transitions), pointeur_Etat(
                                jarvis.toString(element.getTransitions(i).charAt(element.getTransitions(i).length() - 1))));

                        mitose(pointeur_Etat(linked_transitions
                                + jarvis.toString(element.getTransitions(i).charAt(element.getTransitions(i).length() - 1))));
                    }
                }
            }

        }
        return Error;
    }

    /**
     * Retourne la première Entree de l'automate
     * @return Etat Entree
     */
    public Etat getEtatEntree(){

        for (int i = 0; i < getTabEtats().size(); i++) {
            if (getEtats(i).isEntree()) {
                return getEtats(i);
            }
        }

        return Error;
    }

    /**
     * Retourne la prochaine à partir de x Entree de l'automate
     * 
     * @return Etat Entree
     */
    public Etat getEtatEntree(int x) {

        for (int i = x; i < getTabEtats().size(); i++) {
            if (getEtats(i).isEntree()) {
                return getEtats(i);
            }
        }

        return Error;
    }
    

    /**
     * Retourne la première Sortie de l'automate
     * 
     * @return Etat Sortie
     */
    public Etat getEtatSortie(){

        for (int i = 0; i < getTabEtats().size(); i++) {
            if (getEtats(i).isSortie()) {
                return getEtats(i);
            }
        }

        return Error;
    }

    /**
     * Fonction de determinisation
     */
    public void determinisation(){
        
        if(getNbEntrees() <= 1 ) {
            //System.out.println("checked");
            mitose(getEtatEntree());
        }

        if (getNbEntrees() == 2) {
            String a, b = "";
            a = getEtatEntree().getNom();
            b = getEtatEntree(getEtatEntree().index + 1).getNom();

            //System.out.println("a+b "+a+b);
            //System.out.println("checked 2 ");

            fusion_Etat(pointeur_Etat(a), pointeur_Etat(b));

            mitose(pointeur_Etat(a+b));
        }
    }

    /**
     * Vérifie si un automate est capable de reconnaitre un mot
     * @param mot chaine de caractères dont il faut vérifier l'existence
     * @return boolean
     */
    public boolean contains(String mot) {
        int i, j;
        int etatIndex = 0, etatNum = 0;
        boolean exists = false;
        while(etatNum < this.nbEntrees && !exists) {
            if (etats.get(etatIndex).entree) {
                etatNum++;
                Etat tmp = etats.get(etatIndex);

                for (i = 0; i < mot.length() + 1; i++) {

                    for (j = 0; j < tmp.getTabCharTransitions().size(); j++) {

                        if (i < mot.length()) {
                            if (tmp.charTransitions.get(j).charAt(0) == mot.charAt(i)){
                                tmp = pointeur_Etat(tmp.transitions.get(j));
                                break;
                            }

                            else if (tmp.charTransitions.get(j).charAt(0) == MOT_VIDE){
                                tmp = pointeur_Etat(tmp.transitions.get(j));
                                i--;
                                break;
                            }
                        }
                        else {
                            if (tmp.charTransitions.get(j).charAt(0) == MOT_VIDE){
                                tmp = pointeur_Etat(tmp.transitions.get(j));
                                i--;
                                break;
                            }
                        }

                    }
                    if (j == tmp.getTabCharTransitions().size()) i = mot.length();

                }
                if (tmp.sortie) exists = true;
            }
            etatIndex++;
        }
        return exists;
    }
}

