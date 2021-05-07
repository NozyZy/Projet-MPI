package Automate;

import java.io.*;
import java.util.Collections;
import java.util.Scanner;

import javax.lang.model.element.Element;
import javax.lang.model.util.ElementScanner6;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Tienes aue respetar las indicationes por ejemplo en la playa no botes tus vasuras
 * 
 * trata des commpartir y apprender la cultura local como
 * descourir nueva gastronomia
 */

public class Automate implements Cloneable {

    Multifonctions jarvis = new Multifonctions(); //Majordome
    private static final Etat Error = null;

    public final char MOT_VIDE = '*';

    String label;
    ArrayList<Etat> etats;
    ArrayList<Character> alphabet;
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

    Automate(String label) {
        this.label = label;
        setTabEtats();
    }

    Automate(String label, ArrayList<Etat> etats, int nbEntrees, int nbEtats){
        this.label = label;
        this.etats = etats;
        this.nbEntrees = nbEntrees;
        this.nbEtats = nbEtats;
        allVerifs(true);
    }

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

        // Boucle qui permet l'enrengistrement de l'automate depuis un txt
        for (int i = 0; i < getInitnbTransitions(); i++) {
            int x = pointeur_Etat(lecture.next()).getIndex();

            getEtats(x).setTotalTransitions(lecture.next(), lecture.next()); // on ajoute la transtion (a,b,c,d...), on ajoute l'élement pointer (etat 1, etat2...)
        }
        lecture.close();// fermeture de la lecture du txt
        nbTransitions = getInitnbTransitions();
        allVerifs(true);
    }

    public void verifAsynchrone(boolean doesPrint) {
        boolean isAsynchrone = false;
        int count = 0;
        for(int i = 0; i < nbEtats; i++){
            for(int j = 0; j < getEtats(i).nbTransitions(); j++){
                if (getEtats(i).getCharTransitions(j).charAt(0) == MOT_VIDE){
                    if (!isAsynchrone) {
                        isAsynchrone = true;
                        if (doesPrint) System.out.println("L'automate " + label + " est asynchrone, à cause des transitions suivantes : ");
                    }
                    if (doesPrint) getEtats(i).afficher_transition(j);
                    count++;
                    if (count > 0 && count%10 == 0 && doesPrint) System.out.println();
                }
            }
        }
        if (isAsynchrone && doesPrint) System.out.println();
        setAsynchrone(isAsynchrone);
    }

    public void verifStandard(boolean doesPrint) {
        if (getNbEntrees() != 1){
            if (doesPrint) System.out.println("L'automate " + label + " n'est pas standard, car il possède " + getNbEntrees() + " entrees.");
            setStandard(false);
        }
        else {
            boolean isStandard = true;
            int count = 0;
            String a = getEtatEntree().nom;
            for (int i = 0; i < getNbEtats(); i++) {
                for (int j = 0; j < getEtats(i).nbTransitions(); j++) {
                    if (getEtats(i).getTransitions(j).equals(a)) {
                        if(isStandard){
                            isStandard = false;
                            if (doesPrint) System.out.println("L'automate " + label + " n'est pas standard, à cause des transitions suivantes : ");
                        }
                        if (doesPrint) getEtats(i).afficher_transition(j);
                        count++;
                        if (count > 0 && count%10 == 0 && doesPrint) System.out.println();
                    }
                }

            }
            if (!isStandard && doesPrint) System.out.println();
            setStandard(isStandard);
        }
    }

    public void verifComplet(boolean doesPrint) {
        boolean isComplet = true;
        setAlphabet();
        int j;
        for (Character alpha: alphabet) {
            boolean thisOne = false;
            for (int i = 0; i < getNbEtats(); i++) {
                Etat a = getEtats(i);
                for (j = 0; j < a.nbTransitions(); j++) {
                    if (a.getCharTransitions(j).charAt(0) == alpha) {
                        j = a.nbTransitions() + 1;
                        break;
                    }
                }
                if (j <= a.nbTransitions()) {

                    if (isComplet) {
                        isComplet = false;
                        if (doesPrint) System.out.println("L'automate " + label + " n'est pas complet, à cause des transitions manquantes : ");
                    }
                    if (!thisOne) {
                        thisOne = true;
                        if (doesPrint) System.out.print(" " + alpha + " -> ");
                    }
                    if (doesPrint) System.out.print(" " + a.nom + alpha + ". ");
                }
            }
            if(thisOne && doesPrint) System.out.println();
        }
        if (!isComplet && doesPrint) System.out.println();
        setComplet(isComplet);
    }

    public boolean verifDeterministe(boolean doesPrint) {
        if (getNbEntrees() != 1) {
            if (doesPrint) System.out.println("L'automate " + getLabel() + " n'est pas déterministe, car il possède " + getNbEntrees() + " entrees.");
            setDeterministe(false);
            return false;
        }

        else{
            boolean isDeterministe = true;

            for(Character alpha: alphabet){
                boolean thisOne = false;

                for (int i = 0; i < getNbEtats(); i++) {
                    Etat a = getEtats(i);
                    ArrayList<Integer> theseTransitions = new ArrayList<>();

                    for (int j = 0; j < a.nbTransitions(); j++) {
                        if (a.getCharTransitions(j).charAt(0) == alpha) {
                            theseTransitions.add(j);
                        }
                    }
                    if(theseTransitions.size() > 1) {
                        if (isDeterministe) {
                            isDeterministe = false;
                            if (doesPrint) System.out.println("L'automate " + label + " n'est pas deterministe, à cause des transitions suivantes : ");
                        }
                        if (doesPrint) {
                            for (Integer index: theseTransitions) {
                                a.afficher_transition(index);
                            }
                        }
                        thisOne = true;
                    }
                }
                if (thisOne && doesPrint) System.out.println();
            }
            if (!isDeterministe && doesPrint) System.out.println();
            setDeterministe(isDeterministe);
            return true;
        }
    }

    public void allVerifs(boolean doesPrint) {
        verifStandard(doesPrint);
        verifAsynchrone(doesPrint);
        verifComplet(doesPrint);
        verifDeterministe(doesPrint);
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

    public void setAlphabet() {
        this.alphabet = new ArrayList<>();
        for (int i = 0; i < getNbEtats(); i++) {
            for (int j = 0; j < getEtats(i).nbTransitions(); j++) {
                char val = getEtats(i).getCharTransitions(j).charAt(0);
                if (!this.alphabet.contains(val) && val != MOT_VIDE) {
                    this.alphabet.add(val);
                }
            }
        }
        Collections.sort(alphabet);
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

    public ArrayList<String> getEtats() {
        ArrayList<String> names = new ArrayList<>();
        for (Etat etat: etats) {
            names.add(etat.nom);
        }
        Collections.sort(names);
        return names;
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
        getEtats(i).setGroupeMinimisation();
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
        getEtats(0).setGroupeMinimisation();
        this.nbEtats++;
    }

    public void addEtats(String nom) {
        this.etats.add(new Etat(nom, 0));
        getEtats(this.nbEtats).setTabTransitions();
        getEtats(this.nbEtats).setTabCharTransitions();
        getEtats(this.nbEtats).setGroupeMinimisation();
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

    public int findNbTransitions() {
        int nbTransitions = 0;
        for (int i = 0; i < getNbEtats(); i++) {
            nbTransitions += getEtats(i).nbTransitions();
        }
        return nbTransitions;
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

    public ArrayList<Character> getAlphabet() {
        return alphabet;
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

            for (String s : tab) {
                if (s.equals(tmp)) {
                    occ++;
                }
            }

            if (occ > 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * Affiche l'automate en string
     */
    public void afficherAutomate() {
        System.out.println("\n<=========( Voici l'automate : "+ getLabel() +" )==========>\n");
        /**System.out.println(" Standard -----------> " + isStandard());
        System.out.println(" Complet ------------> " + isComplet());
        System.out.println(" Deterministe -------> " + isDeterministe());
        System.out.println(" Asynchrone ---------> " + isAsynchrone());
        System.out.println(" Minimal ------------> " + isMinimale());
        System.out.println();**/
        System.out.println(" # d'états ----------> " + getNbEtats());
        System.out.println(" # de transitions ---> " + findNbTransitions());
        System.out.println(" Etats --------------> " + getEtats());
        System.out.println(" Alphabet -----------> " + getAlphabet());
        System.out.println("\n");

        for (int i = 0; i < getTabEtats().size(); i++) {

            if (getEtats(i).isEntree()) {
                System.out.print("E-->");
                
            }

            System.out.print(" **(" + getEtats(i).getNom() + ")**");

            if (getEtats(i).isSortie()) {
                System.out.print(" -->S ");
            }
            System.out.println();
            for (int j = 0; j < getEtats(i).getTabCharTransitions().size(); j++) {
                System.out.print("(" + getEtats(i).getNom() + ")");
                System.out.print("-" + getEtats(i).getTabCharTransitions().get(j) + "->");
                System.out.println("(" + getEtats(i).getTabTransitions().get(j)+") ");

            }
            System.out.println();

            
        }

        System.out.println("<=================================================>\n");
    }

    /**
     * Standardisation de l'automate
     */
    public void standardisation(String nom){
        if (isStandard()) {
            System.out.println("L'automate est déjà standard !");
        } else {
            setEtats(nom); //creation de l'état initiale

            for (int i = 0; i < getTabEtats().size(); i++) {
                if (getEtats(i).isEntree()) {
                    getEtats(i).setPolymerisation();
                }
            }

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
            allVerifs(false);
        }
    }

    public void completion() {
        if (isComplet()) {
            System.out.println("L'automate " + label + " est déjà complet !");
        } else {
            addEtats("P");
            int j;
            for (Character alpha: alphabet) {
                for (int i = 0; i < getNbEtats(); i++) {
                    Etat a = getEtats(i);
                    for (j = 0; j < a.nbTransitions(); j++) {
                        if (a.getCharTransitions(j).charAt(0) == alpha) {
                            j = a.nbTransitions() + 1;
                            break;
                        }
                    }
                    if (j <= a.nbTransitions()) {
                        a.setTotalTransitions(Character.toString(alpha), "P");
                    }
                }
            }
            allVerifs(false);
        }
    }

    public void determinisation_completion_synchrone() {
        if (isAsynchrone()) {
            System.out.println("L'automate " + label + " est asynchrone !");
        } else {
            determinisation();
            completion();
        }
    }

    public Automate determinisation_completion_asynchrone() {
        if (!isAsynchrone()) {
            System.out.println("L'automate " + label + " n'est pas asynchrone !");
            return this;
        } else {
            Automate newAuto = eliminationEpsilon();
            newAuto.determinisation();
            newAuto.completion();
            return newAuto;
        }
    }

    public void langage_complementaire() {
        if (!isDeterministe()) determinisation();
        if (!isComplet()) completion();
        for (int i = 0; i < getNbEtats(); i++) {
            getEtats(i).setSortie(!getEtats(i).isSortie());
        }
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

        if ((a == null || b == null) || (doesEtatExist(b.getNom()+a.getNom()))){
            //System.out.println("hey je existe déja ---------------------------------------- > : "+ a.getNom() + b.getNom());
        }
        else{
           
            setEtats(a.getNom() + b.getNom());
            pointeur_Etat(a.getNom()).setPolymerisation();
            pointeur_Etat(b.getNom()).setPolymerisation();

            /**if (getNbEntrees() > 1) {
                if ((pointeur_Etat(a.getNom()).isEntree()) && pointeur_Etat(b.getNom()).isEntree()) {
                    pointeur_Etat(a.getNom() + b.getNom()).setEntree(true);

        if (a != null && b != null){
            setEtats(a.nom + b.nom);

            if (getNbEntrees() > 1 || getNbEntrees() == 1) {
                if ((a.isEntree()) || b.isEntree()) {
                    pointeur_Etat(a.nom + b.nom).setEntree(true);
                }
            }**/

            if ((pointeur_Etat(a.getNom()).isSortie()) || pointeur_Etat(b.getNom()).isSortie()) {
                pointeur_Etat(a.getNom() + b.getNom()).setSortie(true);
            }

            for (int i = 0; i < pointeur_Etat(a.getNom()).getTabTransitions().size(); i++) {
                pointeur_Etat(a.getNom() + b.getNom()).setTotalTransitions(pointeur_Etat(a.getNom()).getCharTransitions(i),
                        pointeur_Etat(a.getNom()).getTransitions(i));
            }
 
            for (int i = 0; i < pointeur_Etat(b.getNom()).getTabTransitions().size(); i++) {
                pointeur_Etat(a.getNom() + b.getNom()).setTotalTransitions(pointeur_Etat(b.getNom()).getCharTransitions(i),
                        pointeur_Etat(b.getNom()).getTransitions(i));
            }

    
            /**if (!(pointeur_Etat(a.getNom()).getTabTransitions().contains(a.getNom()))) {
                suppression_Etat(a);
            }

            if (!(pointeur_Etat(b.getNom()).getTabTransitions().contains(b.getNom()))) {
            if (a.isSortie() || b.isSortie()) {
                pointeur_Etat(a.nom + b.nom).setSortie(true);
            }

            for (int i = 0; i < a.nbTransitions(); i++) {
                pointeur_Etat(a.nom + b.nom).setTotalTransitions(a.getCharTransitions(i),
                        a.getTransitions(i));
            }

            for (int i = 0; i < b.nbTransitions(); i++) {
                pointeur_Etat(a.nom + b.nom).setTotalTransitions(b.getCharTransitions(i),
                        b.getTransitions(i));
            }

            if (!a.getTabTransitions().contains(a.nom)) {
                suppression_Etat(a);
            }

            if (!b.getTabTransitions().contains(b.nom)) {
                suppression_Etat(b);
            }**/
   
            
        }
    }

    /**
     * Fusionne les état a et b pour cree l'état ab et supprimer a et b. ab recupere les transtisions de a + b
     * @param a
     * @param names
     */
    public void fusion_Etat_asynchrone(Etat a, String[] names, Automate n){

        if (names != null){

            for (String name: names){
                if (pointeur_Etat(name).isEntree()) {
                    n.nbEntrees++;
                    n.pointeur_Etat(a.nom).setEntree(true);
                }
                if (pointeur_Etat(name).isSortie()) {
                    n.nbSorties++;
                    n.pointeur_Etat(a.nom).setSortie(true);
                }
            }

            for(String name: names){
                Etat b = pointeur_Etat(name);
                for (int i = 0; i < b.nbTransitions(); i++) {
                    if (b.getCharTransitions(i).charAt(0) != MOT_VIDE) {
                        n.nbTransitions++;
                        n.pointeur_Etat(a.nom).setTotalTransitions(b.getCharTransitions(i),
                                b.getTransitions(i) + "'");
                    }
                }
            }

        }
    }

    public boolean isPointer(Etat element){

        for (int i = 0; i < getTabEtats().size(); i++) {
            for (int j = 0; j < getEtats(i).getTabTransitions().size(); j++) {
                if (getEtats(i).getTransitions(j).equals(element.getNom())) {
                    //getEtats(i).affiche_etat("all");
                    return true;
                }
            }
        }

        return false;
    }

  
    public boolean isPointer(Etat element, int x) {
        
        for (int i = 0; i < getTabEtats().size()-x; i++) {
            //System.out.print("v");
            //getEtats(i).affiche_etat();
            //System.out.println("Voici les etat a partir de l'entrée : " + getEtats(i).nom);
            for (int j = 0; j < getEtats(i).getTabTransitions().size(); j++) {
                //System.out.print("î");
                //getEtats(j).affiche_etat();
                if (getEtats(i).getTransitions(j).equals(element.getNom())) {
                    //System.out.println("l'état :" +getEtats(j).nom+" point vers "+getEtats(i).nom);
                    return true;
                }
            }
        }
        
        return false;
    }

    /**
     * Fusionne les transitions identique d'un element. Si element x a 2 transions de a ==> x a 1 transition a qui pointe vers un nouvel element consituer des transitions
     * @param element
     */
    public void fusion_transition(Etat element){

        String memory_element = "";
        String memory_char;
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
                            fusion_Etat(pointeur_Etat(copy), pointeur_Etat(b));
                        }
                        else{
                            System.out.println("---------------------voici le couple a : " + a + " et b : " + b);
                            pointeur_Etat(a).affiche_etat_complet();
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
        return pointeur_Etat(nom) != null;
    }

    public void nettoyage(){
        int fusion = 0;
        
        for (int i = 0; i < getTabEtats().size(); i++) {
           if (getEtats(i).getPolymerisation() > 0){
               //getEtats(i).affiche_etat();
               fusion += 1;
           }
        }
        //System.out.println("------------------> nb fusion etat 4 : " + pointeur_Etat("4").getPolymerisation());
        //System.out.println("--------------------> nb fusion "+fusion);

        for (int i = 0; i < getTabEtats().size(); i++) {
            if (getEtats(i).getPolymerisation() > 0) {
                //System.out.println("----------------------------->2");
                //getEtats(i).affiche_etat();
               if (!isPointer(getEtats(i), fusion)) {
                //System.out.println("Je supprime ----------> "+getEtats(i).nom);
                   suppression_Etat(getEtats(i));
                   i = 0;
               }
            }
        }



    }

    /**
     * Reçois un Etat mère et construit les états filles non existantes
     * @param element Etat mère
     */
    public void mitose(Etat element){

        if (element == null) {
            return;
        }
        else{
            fusion_transition(element);
        }

        if (!element.getTabCharTransitions().isEmpty()) {
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

    public Etat fusion_multiple(int x){
        ArrayList<String> entrees;

        entrees = new ArrayList<String>();
        String tmp = "";

        for (int i = 0; i < getTabEtats().size(); i++) {
            if (getEtatEntree(i) != Error) {
                entrees.add(getEtatEntree(i).nom);
            }
            
        }

        /**for (int i = 0; i < entrees.size(); i++) {
            System.out.println("voici les entrée : "+ entrees.get(i));
        }**/

        fusion_Etat(pointeur_Etat(entrees.get(0)), pointeur_Etat(entrees.get(1)));//on fusionne les deux premiere entrées
        tmp += entrees.get(0);//on set le nouveau nom de l'état initiale
        tmp += entrees.get(1);

        for (int i = 2; i < x; i++) {
            fusion_Etat(pointeur_Etat(tmp), pointeur_Etat(entrees.get(i)));
            tmp += entrees.get(i);
        }

        fusion_transition(pointeur_Etat(tmp));
        pointeur_Etat(tmp).setEntree(true);

        //afficherAutomate();

        return pointeur_Etat(tmp);
    }

    public Etat fusion_Entrees(){
        String combo = "";

        for (int i = 0; i < getTabEtats().size(); i++) {
           if (getEtats(i).isEntree()) {
               combo += getEtats(i).getNom();
           }
        }

        standardisation(combo);

        //afficherAutomate();
        return pointeur_Etat(combo);

    }

    /**
     * Fonction de determinisation
     */
    public void determinisation(){

        if (!verifDeterministe(false)) {
            if (getNbEntrees() <= 1) {
                // System.out.println("checked");
                mitose(getEtatEntree());
            }

            else {
                mitose(fusion_Entrees());
            }

            nettoyage();   
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

        while(etatNum < this.getNbEntrees() && !exists) {
            if (getEtats(etatIndex).isEntree()) {

                etatNum++;
                Etat tmp = getEtats(etatIndex);

                for (i = 0; i < mot.length() + 1; i++) {
                    int len = tmp.nbTransitions();
                    for (j = 0; j < len; j++) {

                        if (i < mot.length()) {
                            if (tmp.getCharTransitions(j).charAt(0) == mot.charAt(i)){
                                tmp = pointeur_Etat(tmp.getTransitions(j));
                                if (i == mot.length() - 1) i++;
                                break;
                            }
                        }

                        if (tmp.getCharTransitions(j).charAt(0) == MOT_VIDE){
                            tmp = pointeur_Etat(tmp.getTransitions(j));
                            i--;
                            break;
                        }

                    }
                    if (j == len) break;

                }
                if (tmp.isSortie() && i >= mot.length()) exists = true;
            }
            etatIndex++;
        }
        return exists;
    }


    public void minimisation(){
        if (!isMinimale()){
            if (!isAsynchrone()){
                if (isDeterministe()){
                    if (isComplet()){
                        //laMinimalisation();
                        System.out.printf("\n>>>    L'automate est en cours d'analyse ...");
                        minimisationAnalyse();
                        System.out.printf("\n>>>    L'automate est en cours de fusion ...");
                        minimisationFusion();
                        System.out.printf("\n>>>    L'automate MINIMAL est termine !!!");
                        afficherAutomate();
                    }
                    else{System.out.printf("\n>>>    L'automate n'est pas complet ...");}
                }
                else{System.out.printf("\n>>>    L'automate n'est pas determinisate ...");}
            }
            else{System.out.printf("\n>>>    L'automate n'est pas synchrone ...");} 
        }
        else{System.out.printf("\n>>>    L'automate est deja minimale ...");}
    }
    
    private void minimisationAnalyse() {
        boolean ok = true;
        String a = "C";
        int b = 0;
        while (ok){
            System.out.printf("\n______________________________________________\n");
            for (int i = 0; i < getTabEtats().size(); i++) {
                if (getEtats(i).isSortie() && b==0) {
                    getEtats(i).setGroupeEtatMinimisation(a);
                }
                else if (b==0){
                    getEtats(i).setGroupeEtatMinimisation(a+b);
                }

                ok = minimisationSuivant(getEtats(i), b);

                System.out.printf(">>>    %s", getEtats(i).getGroupeEtatMinimisation());
                System.out.printf("(%3s)", getEtats(i).getNom());
                System.out.printf("-%3s->", getEtats(i).getTabCharTransitions());
                System.out.printf("(%3s) :", getEtats(i).getTabTransitions());
                System.out.printf("%s", getEtats(i).getGroupeMinimisation());
                System.out.println();
            }
            b=b+1;
        }
        setMinimale(true);
    }

    private boolean minimisationSuivant(Etat monEtat, int dif) {
        String Etat = monEtat.getNom();
        String string = monEtat.getGroupeEtatMinimisation();
        
        for (int i=0; i < getTabEtats().size(); i++) {
            for (int j=0; j < getEtats(i).nbTransitions(); j++) {
                //System.out.printf("-%s- et -%s-\n", getEtats(i).getTabTransitions().get(j), Etat);
                if (Etat.intern() == getEtats(i).getTransitions(j).intern()){
                    //System.out.printf("trouver\n");
                    if (getEtats(i).getGroupeMinimisation().size() == getEtats(i).nbTransitions() ){
                        getEtats(i).setGroupeMinimisation(j, string);
                    }
                    else{ getEtats(i).setGroupeMinimisation(string);}
                }
                else {
                    if (getEtats(i).getGroupeMinimisation().size() != getEtats(i).nbTransitions()){
                        getEtats(i).setGroupeMinimisation((String) string + dif);
                    }
                }
            }
        }
        return coloration(monEtat, dif);
    }

    private boolean coloration(Etat monEtat, int dif){
        String string = monEtat.getGroupeEtatMinimisation();

        for (int i=0; i < getTabEtats().size(); i++) {
            for (int j=0; j < getTabEtats().size(); j++) {
                if (getEtats(i).getGroupeEtatMinimisation().intern() == getEtats(j).getGroupeEtatMinimisation().intern()){
                    if (getEtats(i).getGroupeMinimisation() != getEtats(j).getGroupeMinimisation()) {
                        getEtats(j).setGroupeEtatMinimisation((String) string + dif);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void minimisationFusion() {
        boolean modifier = false;
        for (int i=0; i < getTabEtats().size(); i++) {
            for (int j=0; j < getTabEtats().size(); j++) {
                if (getEtats(i).getNom().intern() !=  getEtats(j).getNom().intern()){
                    if (getEtats(i).getGroupeEtatMinimisation().intern() == getEtats(j).getGroupeEtatMinimisation().intern()){
                        fusion_Etat(getEtats(i), getEtats(j));
                        modifier = true;
                    }
                }
            }
        }
        if (!modifier){
            System.out.print("\n>>>    L'automate est inchanger ...");
        }
    }

    public Automate eliminationEpsilon() {
        if (!asynchrone) verifAsynchrone(false);

        if (asynchrone) { // a besoin de revérifier s'il est asynchrone
            String[] clotures = new String[nbEtats];

            for (int i = 0; i < nbEtats; i++) {
                clotures[i] = etats.get(i).nom + findEpsilon(etats.get(i));
            }

            Automate newAuto = new Automate(label);
            newAuto.setTabEtats();

            int i = 0;
            Stack<String> stackEtat = new Stack<>();
            stackEtat.push(clotures[0].charAt(0) + "'");

            String[] allNewStates = new String[clotures.length];

            while (!stackEtat.isEmpty()) {

                String current = stackEtat.pop();                   //0' puis 2' puis 3'5'
                newAuto.setEtats(i, current);
                allNewStates[i] = current;
                String[] stateNames = current.split("'");     // séparation des int

                for (char alpha: this.alphabet) {
                    String newTransition = "";

                    for (String stateName: stateNames){
                        String[] names = clotures[Integer.parseInt(stateName)].split("-");

                        for (String name: names) {
                            Etat a = newAuto.pointeur_Etat(current);
                            Etat b = pointeur_Etat(name);

                            for (int j = 0; j < b.nbTransitions(); j++){
                                if (b.charTransitions.get(j).charAt(0) == alpha) {
                                    newTransition += b.transitions.get(j) + "'";
                                }
                            }
                            if (b.isEntree() && !a.isEntree()) {
                                newAuto.nbEntrees++;
                                a.setEntree(true);
                            }
                            if (b.isSortie() && !a.isSortie()) {
                                newAuto.nbSorties++;
                                a.setSortie(true);
                            }
                        }
                    }
                    if (!newTransition.equals("")){
                        newAuto.pointeur_Etat(current).setTotalTransitions(String.valueOf(alpha), newTransition);

                        if (!jarvis.isInArray(allNewStates, newTransition) && !stackEtat.contains(newTransition)){
                            stackEtat.push(newTransition);
                        }
                    }

                }
                i++;

                if (i-1 > allNewStates.length) allNewStates = (String[])jarvis.resizeArray(allNewStates, i+1);
            }
            
            newAuto.determinisation();
            newAuto.allVerifs(false);
            return newAuto;
        }
        return this;
    }

    public String findEpsilon(Etat etat) {
        String epsilonLabels = "";
        for (int i = 0; i < etat.nbTransitions(); i++) {
            if (etat.charTransitions.get(i).charAt(0) == MOT_VIDE) {
                if (!etat.nom.equals(etat.transitions.get(i))) {
                    epsilonLabels += "-" + etat.transitions.get(i) + findEpsilon(pointeur_Etat(etat.transitions.get(i)));
                }
            }
        }
        return epsilonLabels;
    }
}
