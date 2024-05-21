package Model;

import java.util.Observable;
import java.awt.Point;

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

    public Point getPosition() {
        return new Point(c.x, c.y);
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

    public void setCase(Case _c){
        c = _c;
    }

    public Case getCase(){
        return c;
    }

    

    /**
     * Permet de pousser une entité
     * @param d la direction dans laquelle on veut pousser l'entité
     * @return true si l'entité a été poussée, false sinon
     */
    public boolean pousser(Direction d){
        return this.deplacerEntite(this.jeu, d);
    }

    public void avancer(Case c, Direction d){
        this.x = c.getX();
        this.y = c.getY();
    }

    public boolean glisser(Case c, Direction d){
        return(seDeplacerVers(jeu.getCible(c.getEntite(), d), d));
    }

    public boolean deplacerEntite(Jeu jeu, Direction d){
        Case c = this.getCase();
        Case cCible = jeu.getCible(c.getEntite(), d);
        if (cCible.entrer(this, d, cCible)) {
            c.quitterEntite(cCible, d, c.getEntite());
            if(cCible.getEntite() != null){
                return true;
            }       
        }
        return false;
    }
}
