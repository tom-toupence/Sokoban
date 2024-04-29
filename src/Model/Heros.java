package Model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
@SuppressWarnings("deprecation")
public class Heros extends Entite  {

    public int y;
    public int x;
    private List<Observer> observers = new ArrayList<>();

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
    

    public void seDeplacer(Direction j) {
        switch (j) {
            case UP:
                if (this.y > 0) {
                    this.y--;
                    break;
                } else {
                    break;
                }
            case DOWN:
                if (this.y < 9) {
                    this.y++;
                    break;
                } else {
                    break;
                }
            case LEFT:
                if (this.x > 0) {
                    this.x--;
                    break;
                } else {
                    break;
                }
            case RIGHT:
                if (this.x < 9) {
                    this.x++;
                    break;
                } else {
                    break;
                }
        }
        setChanged();
        notifyObservers();
    }

    @Override
    public boolean seDeplacerVers(Case c, Direction d){
        if(c instanceof Mur){
            return false;
        }
        if(c instanceof Vide)
        {
            c.entrer(this, d);
            return true;    
        }
        return true;
    }
}
