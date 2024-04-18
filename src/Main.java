import Model.Case;
import Vue_controlleur.MF;

public class Main {
    public static void main(String[] args) {
        Case c = new Case(5,5);
        MF mf = new MF(c);
        c.addObserver(mf);
        mf.setVisible(true);
    }
}