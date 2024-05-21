package Model;

public class Vide extends Case {

    public Vide(int x, int y){
        super(x, y);
    } 

    public boolean entrer(Entite e, Direction d, Case c) {
        return true;
    }
}
