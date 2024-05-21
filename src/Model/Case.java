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
     * @return true si l'entité peut entrer dans la case, false sinon
     */
    public boolean entrer(Entite e, Direction d, Case c) {
        return true;
    }

    /**
     * État de la case après que l'entité ait quitté la case
     * @param e l'entite qui veut quitter
     * @param d la direction dans laquelle l'entite veut quitter
     * @param c la case que l'entite veut quitter
     * @return la case après que l'entité ait quitté la case
     */
    public Case apresQuitter(Entite e, Direction d, Case c) {
        return this;
    }

    public Entite getEntite(){
        return entite;
    }

    public Entite setEntite(Entite e){
        entite = e;
        return entite;
    }

    public void quitterEntite(Case cCible, Direction d, Entite e){
        if(cCible.entrer(e, d , cCible)){
            if(cCible.getEntite() == null){
                this.setEntite(null);
                cCible.setEntite(e);
                e.setCase(cCible);
            }
        }
    }
    public boolean glissant(){
        return false;
    }
}