package Automate;

public class Main {

    public static void main(String[] args){

        Automate automate = Readwrite.readAutomateFile("src/Automate/test_automate.txt");
        assert automate != null;
        automate.afficherAutomate();
        for(Etat etat: automate.entrees)
            System.out.println(etat.label);
    }

}
