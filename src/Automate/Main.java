package Automate;

public class Main {

    public static void main(String[] args) {
        Etat etat3 = new Etat("3", null, null, 0, false, true);
        Etat etat2 = new Etat("2", null, null, 0, true, false);
        Etat etat1 = new Etat("1", new Etat[]{etat2, etat3}, new char[]{'a', 'b'}, 2, false, true);
        System.out.println(etat1);
    }
}
