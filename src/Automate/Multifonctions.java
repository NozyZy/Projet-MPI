

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
    }

    /**
     * Permet une saisie utilisateur securisé
     * 
     */
    public void setChoix(String mot) {
        boolean lecture = true;

        while (lecture) {
            System.out.print("Tapez votre choix : ");
            this.choix = scanner.next();

            for (int i = 0; i < mot.length(); i++) {
                if (getChoix().equals(mot.substring(i, i + 1))){
                    lecture = false;
                }
            }
        }
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

    public void laCarte(){
        System.out.println(
                "\n------------------------Voici le menu : que voulez vous faire ?-----------------------------------\n\n"
                        + "---------------------------> d : Déterminisation\n"
                        + "---------------------------> s : Standardisation\n"
                        + "---------------------------> c : Complétion \n"
                        + "---------------------------> m : Miniminisation \n"
                        + "---------------------------> g : Changer d'automate\n"
                        + "---------------------------> e : exit\n");

        setChoix("dscmge");
    }

    public void autoSelection(){
        int g = 0;
        String possi = "";

        for (int i = 0; i < getDatos().length; i++) {
            g = i+1;
            System.out.println("Voici le fichier numéro "+g+": "+getDatos(i).getName()+"\n");
            possi += toString(g);
            System.out.println("---------------------->" + possi);
        }

        System.out.println("Choisissez un automate dans la liste : ");
        System.out.println("---------------------->" + possi);
        setChoix(possi);
        

        for (int i = 0; i < getDatos().length; i++) {
            
            if (this.choix.equals(toString(i+1))) {
                System.out.println("\n"+"Voici le fichier : " + getDatos(i).getName());
                System.out.println("Et son adresse : " + getDatos(i).getPath());
                setPathfinder(getDatos(i).getPath());
            }
        }
    }

}
