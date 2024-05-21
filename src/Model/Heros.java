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

    public void quitterCase(Case c){
        
    }

    @Override
    public boolean seDeplacerVers(Case c, Direction d){
        if(c.entrer(this, d, c)){
            if(c.getEntite() instanceof Bloc || c.getEntite() instanceof Caisse){
                if(c.getEntite().pousser(d) == false){
                    return false;
                }
            }
            // TODO: gérer quitter la case (fissure devient trou)
            this.setCase(this.getCase().apresQuitter(this, d, c));
            c.quitterEntite(c, this);
            this.avancer(c, d);
            if(c.glissant(c)){
                return this.glisser(c, d);
            }
            
            return false;
        }  
        return true;
    }

    public void avancer(Case c, Direction d){
        this.x = c.getX();
        this.y = c.getY();
    }
}