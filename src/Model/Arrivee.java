package Model;

public class Arrivee extends Case {

    public Arrivee(int x, int y){
        super(x, y);
    }

    public boolean entrer(Entite e, Direction d, Case c) {
        return true;
    }
}