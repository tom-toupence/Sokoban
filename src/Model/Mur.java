package Model;

public class Mur extends Case {

    public Mur(int x, int y) {
        super(x, y);
    }

    public boolean entrer(Entite e, Direction d, Case c) {
        return false;
    }

    public boolean deplacer(Entite e){
        // TODO
        return true;
    }

}
