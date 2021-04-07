package Automate;

import java.io.*;
import java.lang.management.ThreadInfo;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

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


        for (int i = 0; i < this.nbTransitions; i++) {
            int x = lecture.nextInt();                          //on copie le nom de l'element
            this.etats[x].charTransitions.add(lecture.next());  //on ajoute la transtion (a,b,c,d...)
            this.etats[x].transitions.add(lecture.nextInt());   //on ajoute l'élement pointer (etat 1, etat2...)
            this.etats[x].nbTransitions++;
        }

        lecture.close();
    }

    /**
     * Affiche l'automate en string
     */
    void afficherAutomate() {
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
}

