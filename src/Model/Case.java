package Model;
import java.util.Observable;
@SuppressWarnings("deprecation")

public class Case extends Observable {
    public int y;
    public int x;
    Entite entite;

    public Case(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public boolean entrer(Entite e, Direction d, Case c) {
        return true;
    }

    public Entite getEntite(){
        return entite;
    }

    public Entite setEntite(Entite e){
        entite = e;
        return entite;
    }
}