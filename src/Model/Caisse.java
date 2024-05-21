package Model;

import java.awt.Point;

public class Caisse extends Entite {

    public Caisse(Jeu _jeu, Case c){
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
            if(cCible.getEntite() instanceof Bloc || cCible.getEntite() instanceof Caisse){
                if(cCible.getEntite().pousser(d) == false){
                    return false;
                }
            }            
            c.quitterEntite(cCible, this);
            return true;
        }
        return false;
    }
}


