package Model;


public class Trou extends Case {

    public Trou(int x, int y){
        super(x, y);
    }

    public boolean entrer(Entite e, Direction d, Case c) {
        return false;
    }
}


