package Automate;

import java.io.File;
import java.util.Scanner;

/**
 * Objet à tout faire
 */
public class Multifonctions {

    private String choix;
    private String pathfinder;
    private Scanner scanner = new Scanner(System.in);
    private File index = new File("index");
    private File[] datos = index.listFiles();

    public String getChoix() {
        return this.choix;
    }

    public File[] getDatos(){
        return this.datos;
    }
    
    public File getDatos(int x) {
        return this.datos[x];
    }


    public String getPathfinder() {
        return this.pathfinder;
    }

    public void setPathfinder(String pathfinder) {
        this.pathfinder = pathfinder;
    }


    /**
     * Une fonction to string qui convertie les int en string
     * 
     * @param x
     * @return
     */
    public String toString(int x) {
        return String.valueOf(x);
    }

    /**
     * Fonction qui convertie un Char en String
     * 
     * @param x
     * @return String
     */
    public String toString(char x) {
        String chaine;

        chaine = String.valueOf(x);

        return chaine;
    }

    /**
     * Permet une saisie utilisateur simple
     */
    public void setChoix(){
        System.out.print("Tapez votre choix : ");
        this.choix = scanner.next();
        clearConsole();
    }

    /**
     * Permet une saisie utilisateur securisé
     * 
     */
    public void setChoix(String mot) {
        boolean lecture = true;
        int taille = mot.length();

        while (lecture) {
            System.out.print("Tapez votre choix : ");
            this.choix = scanner.next();

            if (mot.length() > 9) {
                taille--;
            }

            for (int i = 0; i < taille; i++) {

                if (getChoix().equals(mot.substring(i, i + 1))){
                    lecture = false;
                }

                if (i == 10 && getChoix().equals("10")){
                    lecture = false;
                    //System.out.println("----------> saisie = "+getChoix());
                    //System.out.println("----------> dur = " + mot.substring(i, i + 2));
                }

                if (i > 10 && getChoix().equals(mot.substring(i, i + 2))) {
                    //System.out.println("----------> saisie = "+getChoix());
                    //System.out.println("----------> dur =  " + mot.substring(i, i + 2));
                    lecture = false;
                }
            }
        }
        clearConsole();
    }


    /**
     * Fonction qui clean la console
     */
    public void clearConsole(){
        System.out.print("\033[H\033[2J");// Effacement de la console
        System.out.flush();// Pour une meilleur visibilité
    }

    /**
     * Fermet la saisie utilisateur
     */
    public void closescanner(){
        scanner.close();
    }

    public void laCarte(Automate automate){
        System.out.println("\n------------------------Voici le menu : que voulez vous faire ?-----------------------------------\n\n");

        if (automate.isDeterministe()) System.out.println("---------------------------> L'automate est déterministe");
        else System.out.println("---------------------------> d : Déterminisation");

        if (automate.isStandard()) System.out.println("---------------------------> L'automate est standard");
        else System.out.println("---------------------------> s : Standardisation");

        if (automate.isComplet()) System.out.println("---------------------------> L'automate est complet");
        else System.out.println("---------------------------> c : Complétion");

        if (!automate.isAsynchrone()) System.out.println("---------------------------> L'automate est fini");
        else System.out.println("---------------------------> b : Eliminer les transitions epsilon");

        if (!automate.isDeterministe() || !automate.isComplet()) System.out.println("---------------------------> a : Déterminisation complétion synchrone/asynchrone");

        System.out.println(
                "---------------------------> m : Miniminisation \n"
                + "---------------------------> g : Changer d'automate\n"
                + "---------------------------> e : exit\n");

        /*System.out.println(
                "\n------------------------Voici le menu : que voulez vous faire ?-----------------------------------\n\n"
                        + "---------------------------> d : Déterminisation\n"
                        + "---------------------------> s : Standardisation\n"
                        + "---------------------------> a : Déterminisation complétion synchrone/asynchrone\n"
                        + "---------------------------> c : Complétion \n"
                        + "---------------------------> m : Miniminisation \n"
                        + "---------------------------> g : Changer d'automate\n"
                        + "---------------------------> e : exit\n");*/

        setChoix("dscbamge");
    }

    public void autoSelection(){
        String possi = "";
        System.out.println("\n=======> Choisissez un automate dans la liste suivante : \n");

        for (int i = 0; i < getDatos().length; i++) {
            System.out.println(" N° "+ (i+1) +" ---> "+getDatos(i).getName()+"\n");
            possi += toString(i+1);
        }

        setChoix(possi);

        for (int i = 0; i < getDatos().length; i++) {

            if (this.choix.equals(toString(i+1))) {
                //System.out.println("\n"+"Voici le fichier : " + getDatos(i).getName());
                //System.out.println("Et son adresse : " + getDatos(i).getPath());
                setPathfinder(getDatos(i).getPath());
            }
        }
    }

    public String sorted(String a, String b) {
        String nom = a + b;
        String reverse = b + a;

        int x = a.hashCode();
        int y = b.hashCode();

        if (x > y) {
            //System.out.println("-------------------------> reverse "+ nom +" = "+ reverse );
            return reverse;
        }
        return nom;
    }

    public boolean isInArray(char[] array, char val) {
        boolean found = false;
        for(char c: array){
            if (val == c){
                found = true;
                break;
            }
        }
        return found;
    }

    public boolean isInArray(String[] array, String val) {
        boolean found = false;
        for(String c: array){
            if (val.equals(c)){
                found = true;
                break;
            }
        }
        return found;
    }

    public Object resizeArray (Object oldArray, int newSize) {
        int oldSize = java.lang.reflect.Array.getLength(oldArray);
        Class<?> elementType = oldArray.getClass().getComponentType();
        Object newArray = java.lang.reflect.Array.newInstance(elementType, newSize);
        int preserveLength = Math.min(oldSize, newSize);
        if (preserveLength > 0)
            System.arraycopy(oldArray, 0, newArray, 0, preserveLength);
        return newArray;
    }

}
