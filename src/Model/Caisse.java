package Model;

import java.awt.Point;

public class Caisse extends Entite {

    public Caisse(Jeu _jeu, Case c){
        super(_jeu,c);
    }

    public Point getPosition() {
        return new Point(c.x, c.y);
    }
}


