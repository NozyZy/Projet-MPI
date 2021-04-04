package Automate;

public class Main {

    static final char MOT_VIDE = '*';

    public static void main(String[] args) {

        Etat etat4 = new Etat("4", null, null, 0, false, true);
        Etat etat3 = new Etat("3", new Etat[]{etat4}, new char[]{'b'}, 1, false, false);
        Etat etat2 = new Etat("2", null, new char[]{'a', MOT_VIDE}, 2, false, false);
        Etat etat1 = new Etat("1", new Etat[]{etat2, etat3}, new char[]{'a', 'b'}, 2, true, false);
        etat2.transitions = new Etat[]{etat2, etat4};
        Automate automate = new Automate("A", new Etat[]{etat1}, 1, 4);
        automate.afficherAutomate();
        System.out.println("//");
        System.out.println(automate.contains("aaaaa")); //true
        System.out.println(automate.contains("aaaaa*")); //true, avec ou sans * ne change rien
        System.out.println(automate.contains("")); //false
        System.out.println(automate.contains("*")); //false, avec ou sans * ne change rien
        System.out.println(automate.contains("bb")); //true
        System.out.println(automate.contains("b")); //false
        System.out.println(automate.contains("bbb")); //false
        System.out.println(automate.isAsynchrone()); //true
    }
}
