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
        if(this.deplacerEntite(this.jeu, d)){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean deplacerEntite(Jeu jeu, Direction d){
        Case c = this.getCase();
        Case cCible = jeu.calculerPointCible(c, d);
        if (cCible != null && cCible.entrer(this, d, cCible)) {
            if(cCible instanceof Mur){
                return false;
            }
            System.out.println("Bloc déplacé");
            c.setEntite(null);
            cCible.setEntite(this);
            this.setCase(cCible);
            return true;
        }
        return false;
    }

    public void avancerDirectionChoisie(Case c,Direction d){
        return;
        
    }
}


