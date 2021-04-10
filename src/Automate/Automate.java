package Automate;

import java.io.*;
import java.util.Scanner;

import java.util.ArrayList;


public class Automate implements Cloneable {
    public final char MOT_VIDE = '*';

    String label;
    Etat[] etats;
    int nbEtats;
    int nbEntrees;
    int nbSorties;
    int nbTransitions;
    boolean deterministe;
    boolean complet;
    boolean asynchrone;
    boolean standard;
    boolean minimale;



    public char getMOT_VIDE() {
        return this.MOT_VIDE;
    }


    public Etat getEtats(int i) {
        return this.etats[i];
    }

    public void setEtats(int i, String nom) {
        this.etats[i] = new Etat(nom);
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



    Automate(String label, Etat[] etats, int nbEntrees, int nbEtats){
        this.label = label;
        if (etats == null) this.etats = null;
        else this.etats = etats.clone();
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

    
    public String toString(int x) {
        return String.valueOf(x);
    }

    /**
     * Constructeur d'automate a partir d'un fichier txt
     * 
     * @param fichier
     */
    public Automate(FileInputStream fichier){

        Scanner lecture = new Scanner(fichier);// debut de lecture du fichier

        setLabel(lecture.nextLine());
        setNbEtats(lecture.nextInt());
        
        this.etats = new Etat[this.nbEtats];// creation du nombre d'élement dans l'automate

        for (int i = 0; i < this.nbEtats; i++) {
            setEtats(i, lecture.next());
            this.etats[i].affiche_etat();
            this.etats[i].transitions = new ArrayList<String>();
            this.etats[i].charTransitions = new ArrayList<String>();
        }

        setNbEntrees(lecture.nextInt());

        for (int i = 0; i < this.nbEntrees; i++) {
            this.etats[lecture.nextInt()].setEntree(true);
        }

        setNbSorties(lecture.nextInt());

        for (int i = 0; i < this.nbSorties; i++) {
            this.etats[lecture.nextInt()].setSortie(true);
        }

        setNbTransitions(lecture.nextInt());

        /**
         * Boucle qui permet l'enrengistrement de l'automate depuis un txt
         */
        for (int i = 0; i < this.nbTransitions; i++) {
            int x = lecture.nextInt(); // on copie le nom de l'element
            this.etats[x].charTransitions.add(lecture.next()); // on ajoute la transtion (a,b,c,d...)
            this.etats[x].transitions.add(lecture.next()); // on ajoute l'élement pointer (etat 1, etat2...)
            this.etats[x].nbTransitions++;
        }

        lecture.close();// fermeture de la lecture du txt
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    }

    /**
     * Affiche l'automate en string
     */
    public void afficherAutomate() {
        System.out.println("Voici l'automate : "+this.label+"\n");

        for (int i = 0; i < this.nbEtats; i++) {

            if (this.etats[i].entree){
                System.out.print("E-->");
            }
            if (this.etats[i].sortie) {
                System.out.print("-->S");
            }

            System.out.println("(("+this.etats[i].nom + "))");

            for (int j = 0; j < this.etats[i].charTransitions.size(); j++) {
                System.out.print("(" + this.etats[i].nom + ")");
                System.out.print("-" + this.etats[i].charTransitions.get(j) + "->");
                System.out.print("("+this.etats[i].transitions.get(j)+")");

            }

            System.out.println("\n");
        }
    }

    public void afficherAutomate(String test){
        System.out.println("Voici l'automate : " + this.label + "\n");

        for (int i = 0; i < this.nbEtats; i++) {
            
        }
    }


    /**
     * Fonction de navigation dans l'automate
     */
    public void navigation(){
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
    }

    /**
     * Recoit le tableau de transition et return true s'il ya un doublon dans les transition
     * @param tab tableau de char en dynamique
     * @return true or false
     */
    public boolean doublon(ArrayList<String> tab){
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

    public void determinisation(){
      
    }
    
}

