package Model;

import java.awt.Point;
public class Heros extends Entite  {
    public int y;
    public int x;

    public Heros(Jeu jeu, Case c){
        super(jeu, c);
        this.x = c.x;
        this.y = c.y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public Point getPosition() {
        return new Point(x, y);
    }

    @Override
    public boolean seDeplacerVers(Case c, Direction d){
        if(c instanceof Mur){
            return false;
        }
        if(c instanceof Vide || c instanceof Arrivee){
            if(c.getEntite() instanceof Bloc || c.getEntite() instanceof Caisse){
                if(c.getEntite().pousser(d) == false){
                    return false;
                }
            }
            c.setEntite(this);
            this.setCase(c);
            this.x = c.getX();
            this.y = c.getY();
        }
        return true;
    }  
}
