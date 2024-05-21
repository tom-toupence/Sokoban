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

    /**
     * Permet de savoir si l'entite peut entrer dans la case
     * @param e l'entite qui veut entrer
     * @param d la direction dans laquelle l'entite veut entrer
     * @param c la case dans laquelle l'entite veut entrer
     * @return true si l'entite peut entrer dans la case, false sinon
     */
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

    public void sortirCase(Case cCible, Entite e){
        this.setEntite(null);
        cCible.setEntite(e);
        e.setCase(cCible);
    }
}