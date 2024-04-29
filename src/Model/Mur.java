package Model;

public class Mur extends Case {

    public Mur(int x, int y) {
        super(x, y);
    }

    public boolean entrer(Entite e) {
        if (e == null){
            deplacer(e);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean deplacer(Entite e){
        // TODO
        return true;
    }

}
