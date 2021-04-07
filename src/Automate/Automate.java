package Automate;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;


public class Automate {
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

    public final char MOT_VIDE = '*';

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
    public Automate(FileInputStream fichier){

        Scanner lecture = new Scanner(fichier);//debut de lecture du fichier

        this.label = lecture.nextLine();
       

        this.nbEtats = lecture.nextInt();
        this.etats = new Etat[this.nbEtats];//creation du nombre d'élement dans l'automate

        for (int i = 0; i < this.nbEtats; i++) {
            this.etats[i] = new Etat(i);
            this.etats[i].transitions = new ArrayList<Integer>();
            this.etats[i].charTransitions = new ArrayList<String>();
        }
        System.out.println("\n");

        
        this.nbEntrees = lecture.nextInt();
        for (int i = 0; i < this.nbEntrees; i++) {
            this.etats[lecture.nextInt()].setEntree(true);
        }

        this.nbSorties = lecture.nextInt();
        for (int i = 0; i < this.nbSorties; i++) {
            this.etats[lecture.nextInt()].setSortie(true);
        }

        this.nbTransitions = lecture.nextInt();

        /**
         * Boucle qui permet l'enrengistrement de l'automate depuis un txt
         */
        for (int i = 0; i < this.nbTransitions; i++) {
            int x = lecture.nextInt();                          //on copie le nom de l'element
            this.etats[x].charTransitions.add(lecture.next());  //on ajoute la transtion (a,b,c,d...)
            this.etats[x].transitions.add(lecture.nextInt());   //on ajoute l'élement pointer (etat 1, etat2...)
            this.etats[x].nbTransitions++;
        }

        lecture.close();//fermeture de la lecture du txt
    }

    /**
     * Affiche l'automate en string
     */
    public void afficherAutomate() {
        System.out.println("Voici l'automate : "+this.label);
        for (int i = 0; i < this.nbEtats; i++) {
            if (this.etats[i].entree){
                System.out.print("E-->");
            }
            if (this.etats[i].sortie) {
                System.out.print("-->S");
            }
            System.out.println("(("+this.etats[i].label + "))");
            for (int j = 0; j < this.etats[i].charTransitions.size(); j++) {
                System.out.print("(" + this.etats[i].label + ")");
                System.out.print("-" + this.etats[i].charTransitions.get(j) + "->");
                System.out.print("("+this.etats[i].transitions.get(j)+")");

            }
            System.out.println("\n");
        }
    }

    public void navigation(){
        int nav = 1;
        int etat = 0;
        String transision;
        Scanner saisi = new Scanner(System.in);//Objet saisisseur

        System.out.println("Bienvenue dans le menu de navigation de l'automate"+"\n");

        while (nav == 1) {

            this.entrees[etat].affiche_etat();
            System.out.println("Voici les chemins possibles : ");
            
            for (int i = 0; i < this.entrees[0].charTransitions.size(); i++) {
                System.out.println("-> : " + this.entrees[0].charTransitions.get(i));
            }

            System.out.println("Choisi ton chemin");
            transision = saisi.next();

            for (int i = 0; i < this.entrees[etat].charTransitions.size(); i++) {
                if (this.entrees[etat].charTransitions.get(i).equals(transision)) {
            
                    etat = this.entrees[etat].transitions.get(i);
                    this.entrees[etat].affiche_etat();

                    System.out.println("Voulez vous choisir cet Etat ?" + "\n" + "appuyer sur o pour oui et n pour non");
                    String choix = saisi.next();
                    if (choix.equals("o")) {
                        i = 666;
                    }
                }
            }

            System.out.println("Voulez vous continuer de naviguer ?" + "\n" + 
            "appuyer sur o pour oui et n pour non");
            String choix = saisi.next();
            if (choix.equals("n")) {
               nav = 0;
               saisi.close();
            }

        }
    }
}

