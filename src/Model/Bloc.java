package Model;

import java.awt.Point;

public class Bloc extends Entite {

    public Bloc(Jeu _jeu, Case c){
        super(_jeu,c);
    }

    public Point getPosition() {
        return new Point(c.x, c.y);
    }
    
}


