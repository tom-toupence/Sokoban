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
    

    /* public void seDeplacer(Direction j) {
        switch (j) {
            case UP:
                if (x > 0) {
                    x--;
                }
                break;
            case DOWN:
                if (x < 19) {
                    x++;
                }
                break;
            case LEFT:
                if (y > 0) {
                    y--;
                }
                break;
            case RIGHT:
                if (y < 19) {
                    y++;
                }
                break;
        }
        setChanged();
        notifyObservers();
    }*/

    @Override
    public boolean seDeplacerVers(Case c, Direction d){


        if(c instanceof Mur){
            return false;
        }

        if(c instanceof Vide){
            int x = c.getX();
            int y = c.getY();
            this.x = x;
            this.y = y;
            return true;
        }
        return true;
    }



    @Override
    public void avancerDirectionChoisie(Case c, Direction d) {
        
    }   
}
