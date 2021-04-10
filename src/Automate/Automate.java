package Automate;

import java.io.*;
import java.util.Scanner;

import java.util.ArrayList;


public class Automate implements Cloneable {
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
    boolean complet;
    boolean asynchrone;
    boolean standard;
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

    public String toString(int x) {
        return String.valueOf(x);
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    }

    /**
     * Cherche l'etat dans l'automate (tableau d'objets etats) et renvoie l'etat demander par son nom lorsqu'il le trouve 
     * @param nom : etat demander
     * @return l'etat trouver par son nom
     */
    public Etat pointeur_Etat(String nom){

        for (int i = 0; i < getTabEtats().size(); i++) {
            if (getEtats(i).getNom().equals(nom)){
                return getEtats(i);
            }
        }

        System.out.println("Je n'ai pas trouver votre état je vous renvoie l'etat 0");
        return etats.get(0);
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
            getEtats(x).nbTransitions++; //on augmente le nombre de transitions
        }
        lecture.close();// fermeture de la lecture du txt
    }

    /**
     * Affiche l'automate en string
     */
    public void afficherAutomate() {
        System.out.println("Voici l'automate : "+ getLabel() +"\n");

        for (int i = 0; i < getTabEtats().size(); i++) {

            if (getEtats(i).isEntree()){
                System.out.print("E-->");
            }
            if (getEtats(i).isSortie()){
                System.out.print("-->S");
            }

            System.out.println("(("+getEtats(i).getNom() + "))");

            for (int j = 0; j < getEtats(i).getTabCharTransitions().size(); j++) {
                System.out.print("(" + getEtats(i).getNom() + ")");
                System.out.print("-" + getEtats(i).getTabCharTransitions().get(j) + "->");
                System.out.print("("+getEtats(i).getTabTransitions().get(j)+"), ");

            }

            System.out.println("\n");
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

    public void standardisation(){

        setEtats(getNbEtats(), "i");//creation de l'état initiale
        pointeur_Etat("i").setEntree(true);//on le met en entrée
        pointeur_Etat("i").setTotalTransitions("a", "e0");
     

        for (int i = 0; i < getTabEtats().size(); i++) {
            if (getEtats(i).isEntree()) {
                getEtats(i).setEntree(false);//on supprime les entrée
            }
        }
    }

    

    public void determinisation(){
      
    }
    
}

