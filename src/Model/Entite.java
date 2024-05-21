package Model;

import java.util.Observable;

@SuppressWarnings("deprecation")
public class Entite extends Observable {
    Jeu jeu;
    Case c;
    public int x;
    public int y;

    public Entite(Jeu _jeu, Case _c) {
        this.jeu = _jeu;
        c = _c;
        c.setEntite(this);
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
    /**
     * Permet de se déplacer vers une case
     * @param c la case vers laquelle on veut se déplacer
     * @param d la direction dans laquelle on veut se déplacer
     * @return true si le déplacement a été effectué, false sinon
     */
    public boolean seDeplacerVers(Case c, Direction d){
        Entite e = c.getEntite();
        if(e!=null){
            e.pousser(d);
        }
        return true;
    }

    /**
     * Permet de quitter la case actuelle
     */
    public void quitterCase(){
        c=null;
    }

    public void setCase(Case _c){
        c = _c;
    }

    public Case getCase(){
        return c;
    }

    public void allerSurCase(Case _c){
        c = _c;
    }

    /**
     * Permet de pousser une entité
     * @param d la direction dans laquelle on veut pousser l'entité
     * @return true si l'entité a été poussée, false sinon
     */
    public boolean pousser(Direction d){
        return false;
    }

    public void avancer(Case c, Direction d){
        return;
    }
}
