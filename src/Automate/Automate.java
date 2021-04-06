package Automate;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;


public class Automate {
    String label;
    Etat[] entrees;
    int nbEtats;
    int nbEntrees;
    int nbSorties;
    int nbTransitions;
    boolean deterministe;
    boolean complet;
    boolean asynchrone;
    boolean standard;
    boolean minimale;

    Automate(String label, Etat[] entrees, int nbEntrees, int nbEtats){
        this.label = label;
        if (entrees == null) this.entrees = null;
        else this.entrees = entrees.clone();
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
        this.entrees = new Etat[this.nbEtats];//creation du nombre d'élement dans l'automate

        for (int i = 0; i < this.nbEtats; i++) {
            this.entrees[i] = new Etat(i);
            this.entrees[i].transitions = new ArrayList<Integer>();
            this.entrees[i].charTransitions = new ArrayList<String>();
        }
        System.out.println("\n");

        
        this.nbEntrees = lecture.nextInt();
        for (int i = 0; i < this.nbEntrees; i++) {
            this.entrees[lecture.nextInt()].setEntree(true);
        }

        this.nbSorties = lecture.nextInt();
        for (int i = 0; i < this.nbSorties; i++) {
            this.entrees[lecture.nextInt()].setSortie(true);
        }

        this.nbTransitions = lecture.nextInt();


        for (int i = 0; i < this.nbTransitions; i++) {
            int x = lecture.nextInt();//on copie le nom de l'element
            this.entrees[x].charTransitions.add(lecture.next());//on ajoute la transtion (a,b,c,d...)
            this.entrees[x].transitions.add(lecture.nextInt());//on ajoute l'élement pointer (etat 1, etat2...)
            
        }

        lecture.close();
    }

    /**
     * Affiche l'automate en string
     */
    void afficherAutomate() {
        System.out.println("Voici l'automate : "+this.label);
        for (int i = 0; i < this.nbEtats; i++) {
            if (this.entrees[i].entree == true){
                System.out.print("E-->");
            }
            if (this.entrees[i].sortie == true) {
                System.out.print("-->S");
            }
            System.out.println("(("+this.entrees[i].label+"))");
            for (int j = 0; j < this.entrees[i].charTransitions.size(); j++) {
                System.out.print("(" + this.entrees[i].label + ")");
                System.out.print("-" + this.entrees[i].charTransitions.get(j) + "->");
                System.out.print("("+this.entrees[i].transitions.get(j)+")");

            }
            System.out.println("\n");
            
        }
    }
}

