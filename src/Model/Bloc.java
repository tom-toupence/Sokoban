package Model;

import java.awt.Point;

public class Bloc extends Entite {

    public Bloc(Jeu _jeu, Case c){
        super(_jeu,c);
    }

    public Point getPosition() {
        return new Point(c.x, c.y);
    }


    public boolean pousser(Direction d){
        return this.deplacerEntite(this.jeu, d);
    }

    public boolean deplacerEntite(Jeu jeu, Direction d){
        Case c = this.getCase();
        Case cCible = jeu.getCible(c.getEntite(), d);
        if (cCible != null && cCible.entrer(this, d, cCible)) {
            c.setEntite(null);
            cCible.setEntite(this);
            this.setCase(cCible);
            return true;
        }
        return false;
    }
}


